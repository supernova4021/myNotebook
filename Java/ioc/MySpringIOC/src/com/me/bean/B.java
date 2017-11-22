package com.me.bean;

public class B {
	private A a;
	
	public B() {
		System.out.println("一个B对象已创建");
	}
	
	public A getA() {
		return a;
	}
	public void setA(A a) {
		this.a = a;
	}

	@Override
	public String toString() {
		return "B [a=" + a + "]";
	}
	
	
	
}
