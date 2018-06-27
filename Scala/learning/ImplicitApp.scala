package com.me

/**
  * implicit def xxxToXxxxx(xxx:Xxx): Xxxxx = ....
  * 在调用某个方法，发现参数不匹配时，scala会做最后的努力：
  * 在一定范围内寻找可用的隐式转换，将参数转换为方法期望的参数类型
  * 这些隐式转换方法需要import进去才能使用; 在伴生对象/类定义过的隐式转换不需要import
  * 通常也会到伴生对象中寻找隐式值(默认值，默认配置，自动依赖注入)
  */
object ImplicitApp extends App{
  class Person(val name:String)
  class Engineer(val name:String, val sal:Double){
    def coding()= println(s"$name is coding!!")
  }

  def toCode(e: Engineer)={
    e.coding()
  }
  /* implicit method */
  implicit def personToEngineer(p: Person): Engineer = {
    new Engineer(p.name, 999999)
  }

  val p = new Person("Bacon")
  toCode(p) // without the implicit defined above, it would report error here

  /* implicit value */
  def toWorker(name: String)(implicit level:Int)={
    println(s"name:$name.. level:$level")
  }
  implicit val level = 7 //implicit value, which will be automatically injected to the method above
  toWorker("bbacon")

}
