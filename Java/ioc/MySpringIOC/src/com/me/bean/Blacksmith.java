package com.me.bean;

public class Blacksmith implements Speakable {

	@Override
	public void speak(String msg) {
		System.out.println(msg);
	}

}
