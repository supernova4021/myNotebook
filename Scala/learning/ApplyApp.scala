package com.me

/**
  * 伴生类和伴生对象（单例对象object与类class同名）
  * Class()===>object.apply()
  * Object()===>class.apply()
  */
object ApplyApp {
  def main(args: Array[String]): Unit = {

    val applyTest = ApplyTest() //Class()===>object.apply()
    val applyTest2 = applyTest()  //Object()===>class.apply

  }
}

class ApplyTest{
  def apply() = {
    new ApplyTest()
  }
}

object ApplyTest{
  def apply() = {
    new ApplyTest()
  }
}
