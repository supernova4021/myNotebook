package com.me

import scala.util.Random

/**
  * Created by Administrator on 2018/6/26.
  */
object MatchingApp extends App{

  /* Basic */
  val techers = Array("Akiho", "Yuri", "Sola")
  val techer = techers(Random.nextInt(techers.length))
  //once match, auto break
  techer match{
    case "Akiho" => println("It's ji techer!")
    case "Yuri" => println("It's yuri techer!")
    case _ if(techers.length < 4) => println("Those thing is rare!")
    case _ => println("what do you say?")
  }

  /* Array */
  val arr = Array("bacon", "crap", "sweet", "grape")
  arr match {
    case Array("bacon", "crap") => println("match the array which only have 'bacon' and 'crap'")
    case Array(e1,e2,e3) => println("match any three parameters")
    case Array("bacon", _*) => println("the first element is bacon, don't care the others")
    case _ => println(".........")
  }

  /* List */
  val list = List("horror","silent hill", "dark signal", "dark signal")
  list match {
    case "silent hill"::"sada"::Nil => println("match the list which is exactly 'silent hill' and 'horror'")
    case a::b::c::Nil => println("match the list which has three elements:a,b,c="+a+b+c)
    case "horror"::aTail => println("the first element is 'horror', don't care the others")
    case _ => println("...............")
  }

  /* type */
  def matchType(obj: Any): Unit = {
    obj match {
      case x:Int => println("int x")
      case x:Map[_,_] => x.foreach(println)
      case _ => println("whatever..")
    }
  }

  /* exception */
  try{
    1/0
  }catch {
    case e:ArithmeticException => e.printStackTrace()
    case e:Exception => println("an exception happended!")
  }

  /* case class */
  class Person
  case class Student(name:String, age:Int) extends Person
  case class Teacher(name:String, age:Int, sal:Int) extends Person
  case class Creature(name:String) extends Person

  val stu:Person = Student("bacon", 22)
  stu match {
    case Student(name, age) => println(s"this is a $name, he is a student")
    case Teacher(name, age, sal) => println(s"this is a $name, he is a teacher. His salary is $sal")
    case _ => println("....")
  }

}
