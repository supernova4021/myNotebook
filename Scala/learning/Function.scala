package com.me

/**
  * Created by Administrator on 2018/6/25.
  */
object Function {

  def hit(names: String*) = {
    for(name <- names){
      println(s"${name} has been beaten up!")
    }
  }

  def main(args: Array[String]): Unit = {

    //anonymous function
    val square = (x:Int) => x*x
    println(square(55))

    //curry function
    def sum(a:Int)(b:Int):Int = a+b
    val incr = sum(1)_
    println(incr(5))

    //high order function
    val list = List(1,321,24,12,412,41)
    val l2 = list.map((x) => x+1)
    val l3 = list.map(_+1).filter(_<100)
    val v1 = list.reduce(_+_*2)     // (A,A) => A   //A is list element type
    //val v2 = list.reduceLeft((x1,x) => x1+""+x) // (B,A) => B //from left to right//B is another type
    list.foldLeft("")(_+_+"")
    list.fold(100)(_+_)  //just like reduce, but it has a initial parameter
    val mlist = List(List(1,2322,2),List(12,231,2),List(2,23,2))
    mlist.flatten  //flat to one list
    mlist.flatMap(_.map(_*2)) // map and then flatten



    hit("bacon", "john", "siners")
    //range
    Range(1,10)
    Range(1,10,2)
    1 to 10 //1 until 11
    1 until 10 //Range(1,10)

    //loop
    for(i <- 1 to 22 if i%3 == 0 && i%2 == 0){
      println(i)
    }

    val fruits = Array("banana", "apple", "grapes", "others")
    fruits.foreach(f => print(f+","))
  }

}
