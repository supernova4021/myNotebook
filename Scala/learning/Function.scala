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
