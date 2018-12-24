package com.me

/**
  * Created by Administrator on 2018/6/25.
  */
class Life {
  var meaning: String = _ //placeholder, the data type's default value
  var success: Boolean = _
  val maxAge = 100
  // private[this] means it is the private of 'this' object, private[spark], private[person]
  // e.g. 对于对象life1和life2，如果只是private的话，life1里面是可以访问到life2的secret
  private[this] var secret: String = _
  private var secret2: String = _

  def test(life: Life): Unit = {
     //print(life.secret) //compile error, because of private[this]
  }
  
  def live(): Unit = {
  }

  def die(): Unit = {

  }

}
