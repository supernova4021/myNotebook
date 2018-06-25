package com.me

/**
  * Created by Administrator on 2018/6/25.
  */
object Collections extends App{


  /* Array */
  // immutable
  var animals = Array("a1","a2","a3","a4")
  animals = new Array[String](6)
  animals(2) = "hadoop"
  val nums = Array(1,213,12,41,24,12,412,5,125,124,124)
  nums.min
  nums.sum
  println(nums.mkString("<",",",">"))
  // mutable
  var ab = scala.collection.mutable.ArrayBuffer[Int]()
  ab += 1
  ab += (2,3)
  ab -= 1
  ab ++= Array(4,5,6)
  ab --= Array(5,6,123,124,11)
  ab.insert(ab.length, 7,8,9,10)
  println(ab)
  ab.remove(0,3)
  println(ab)
  ab.trimEnd(2) // remove last 'n' elements
  val a = ab.toArray

  /* List */
  Nil // Null immutable list
  // immutable
  val numList = 2::3::5::7::Nil
  println("numList:"+numList)
  // mutable
  val nilist = scala.collection.mutable.ListBuffer[Int]()
  nilist += 1
  nilist += (2,3)
  nilist -= 1
  println("nilist:"+nilist)
  //......
  def sum(nums: Int*)={
    //..
  }
  sum(nilist:_*) //# convert to Int*

  /* Set */
  //very similar...

}
