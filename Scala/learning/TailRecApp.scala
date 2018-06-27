package com.me

/**
  * Created by Administrator on 2018/6/27.
  */
object TailRecApp extends App{

  @annotation.tailrec
  def factorial_1(product:Long)(n:Int):Long = {
    if(n <= 1){
      product
    }else{
      factorial_1(product*n)(n-1)
    }
  }

  val factorial = factorial_1(1)_
  val f10 = factorial(10)
  println(s"factorial 10 is $f10")

}
