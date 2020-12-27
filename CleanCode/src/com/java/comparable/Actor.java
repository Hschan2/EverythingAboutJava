package com.java.comparable;

public class Actor implements Comparable<Object>{
	String name;
	int age;

	public Actor(String name, int age) {
		this.name = name;
		this.age = age;
	}
	/*
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	*/
	public String toString() {
		return name + " " + age + "년생";
	}
	
	// Comparable 인터페이스의 comparaTo를 오버라이딩
	public int compareTo(Object o) {
		return name.compareTo(((Actor) o).name);
	}
	
}
