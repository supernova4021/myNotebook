package com.me.bean;

public class A {
	private String name;
	private int age;
	
	public A(){
		System.out.println("一个A对象已创建");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "A [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
