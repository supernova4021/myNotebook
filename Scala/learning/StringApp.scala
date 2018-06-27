package com.me

/**
  * Created by Administrator on 2018/6/27.
  */
object StringApp extends App{
  val something = "^^^^^^^^^^"
  val multiLine =
    s"""
       !!!!!!!!!!!!!
       #########
       $something
       @@@@@@@@
       %%%%%%%%
    """.stripMargin
  println(multiLine)
}
