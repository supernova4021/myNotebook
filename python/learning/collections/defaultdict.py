from collections import defaultdict


meats = ["bacon","bacon","lamb","chicken","lamb"]

meat_dict = {}

for m in meats:
     meat_dict.setdefault(m,0) # if key not exists, set dict[key] = 0
     meat_dict[m] += 1

print(meat_dict)



def genet_dict():
     return {"name":"bbb","age":222}

mdict = defaultdict(int)  # callable thing, like function..f()  int() = 0
                          # the return value is the default value

for m in meats:
     mdict[m] += 1

print(mdict)
