package com.me

/**
  * Created by Administrator on 2018/6/25.
  */
class Life {
  var meaning: String = _ //placeholder, the data type's default value
  var success: Boolean = _
  val maxAge = 100
  // private[this] means it is the private of 'this' object, private[spark], private[person]
  private[this] var secret: String = _
  private var secret2: String = _

  def live(): Unit = {

  }

  def die(): Unit = {

  }

}
