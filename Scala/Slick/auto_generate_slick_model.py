import sys

# usage
# python2 auto_generate_slick_model.py < createTable.sql

def scan_ddl():
    class D:
        def __init__(self, bs, fs, asst, collect):
            self.bs = bs
            self.fs = fs
            self.asst = asst
            self.collect = collect

    def fmt(col):
        return col[1:-1] if col[0] == "`" else col

    dags = (
        D("BEFORE_TABLE",  "TABLE_NAME",    "table", None),
        D("TABLE_NAME",    "BEFORE_IN_COL", True,    lambda c, x: [fmt(x)]),
        D("BEFORE_IN_COL", "BEFORE_COL",    "(",     None),
        D("BEFORE_COL",    "END",           lambda w: w.startswith(")"),  None),
        D("BEFORE_COL",    "END",           "primary",  None),
        D("BEFORE_COL",    "END",           "unique",  None),
        D("BEFORE_COL",    "END",           "key",  None),
        D("BEFORE_COL",    "BEFORE_TYPE",   True,    lambda c, x: c + [fmt(x)]),
        D("BEFORE_TYPE",   "TYPED",         True,    lambda c, x: c[:-1] + [[c[-1], x]]),
        D("TYPED",         "BEFORE_COL",    lambda w: w.endswith(","),    None)
    )

    s = ""
    for line in sys.stdin:
        s = s + line
    words = s.split()

    collect = []
    status = "BEFORE_TABLE"
    for word in words:
        word = word.strip().lower()
        for dag in dags:
            if status == dag.bs:
                if dag.asst == True or (isinstance(dag.asst, str) and dag.asst == word) or (hasattr(dag.asst, "__call__") and dag.asst(word)):
                    if dag.collect is not None:
                        collect = dag.collect(collect, word)
                    status = dag.fs
                    break

        if status == "END":
            break
    return collect

def camel(underline, first_upper):
    words = underline.split("_")
    return ((words[0][0].upper() + words[0][1:]) if first_upper else words[0]) + "".join(map(lambda w: w[0].upper() + w[1:], words[1:]))

def trans_to_scala(collect):
    def lang_type(ddl_type):
        if ddl_type.startswith("int") or ddl_type.startswith("bit"):
            return "Int"
        if ddl_type.startswith("bigint"):
            return "Long"
        if ddl_type.startswith("decimal") or ddl_type.startswith("float"):
            return "Double"
        if ddl_type.startswith("bit"):
            return "Boolean"
        return "String"

    tbl = collect[0]
    clz = camel(tbl, True)
    cols = collect[1:]
    fields = map(lambda x: (x[0], camel(x[0], False), lang_type(x[1])), cols)

    print "import slick.driver.MySQLDriver.api._"
    print
    print "case class %s(%s)" % (clz, ", ".join(map(lambda (i, f, t): f + ": " + t, fields)))
    print
    print "object %s {" % clz
    print "    class T(tag: Tag) extends Table[%s](tag, \"%s\") {" % (clz, tbl)
    for name, field, t in fields:
        print "        def %s = column[%s](\"%s\")" % (field, t, name)
    print "        def * = (%s) <> ((%s.apply _).tupled, %s.unapply)" % (", ".join(map(lambda x: x[1], fields)), clz, clz)
    print "    }"
    print
    print "    val q = TableQuery[T]"
    print "}"

collect = scan_ddl()
trans_to_scala(collect)
