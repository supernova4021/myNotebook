package com.me.test;

import java.util.Map;

import org.junit.Before;

import com.me.bean.A;
import com.me.bean.B;
import com.me.bean.Speakable;
import com.me.config.Bean;
import com.me.config.parse.XMLSourceReader;
import com.me.core.BeanFactory;
import com.me.core.ClassPathXmlApplicationContext;

public class Test {
	
	BeanFactory applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");;
	
	@org.junit.Test
	public void testBLSM(){
		Speakable blacksmith = (Speakable)applicationContext.getBean("blacksmith");
		blacksmith.speak("Hey!!!!!!");
		System.out.println("--------------------------------------testBlacksmith------------------------------------");
	}
	
	@org.junit.Test
	public void testA(){
		A a =(A)applicationContext.getBean("a");
		A a2 =(A)applicationContext.getBean("a");
		A a3 =(A)applicationContext.getBean("a");
		System.out.println("--------------------------------------testA------------------------------------");
	}
	
	@org.junit.Test
	public void testB(){
		B b =(B)applicationContext.getBean("b");
		B b2 =(B)applicationContext.getBean("b");
		B b3 =(B)applicationContext.getBean("b");
		System.out.println("--------------------------------------testB------------------------------------");
	}
	
}
