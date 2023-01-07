package com.java.may11;

public class GetSetClass {
	String name;
	int age;
	
	public void setInfo(String name, int age) {
		System.out.println("-- setInfo() --");
		
		this.name = name;
		this.age = age;
	}

	
	public void getInfo() {
		System.out.println("-- getInfo() --");
		
		System.out.println("name : " + name);
		System.out.println("age : " + age);
	}
}