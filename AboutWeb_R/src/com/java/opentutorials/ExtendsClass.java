package com.java.opentutorials;

public class ExtendsClass {
	public int sum(int a, int b) {
		return a+b; // this.sum(a,b) => this는 자기 자신
	}
	// Overloading
	public int sum(int a, int b, int c) {
		return a+b+c;
	}
}

class cal extends ExtendsClass {
	public int minus(int a, int b) {
		return a-b;
	}
	// overriding -> 재정의
	public int sum(int a, int b) {
		System.out.println("cal sum overriding");
		return a+b;
	}
	public int sum(int a, int b, int c) {
		System.out.println("cal sum overloaing");
		return super.sum(a, b, c); // super => 부모 클래스로 값을 보내다
	}
}