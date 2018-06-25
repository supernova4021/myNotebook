package com.me

/**
  * Created by Administrator on 2018/6/25.
  */
object ClassDemo2 {
  def main(args: Array[String]): Unit = {
    val person = new Person()
    println(person.name + " " + person.age)
    //person.birthday // person.birthday is not the property of the person
  }
}

//the main contructor
//用'val'或'var'修饰的会被作为类的属性
class Person(val name: String, var age: Int, birthday: String){ //the 'birthday' is just a parameter!!
  println("a person is being given birth!")

  private var gender: String = _
  //other contructor
  def this() = {
    this("#", 0, "#")
  }

  def this(gender: String) = {
    this()
    this.gender = gender
  }

  override def toString: String = "Person: !!!!!!!"

  println("a person has been given birth!")
}

class Student(name: String, age: Int) extends Person(name, age, ""){
}
