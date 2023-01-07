package com.java.may21;

public class ExtendsClass extends AbstractClass { // AbstractClass가 상위(부모)이며 상속 받음
	public ExtendsClass() {
		System.out.println("ExtendsClass Constructor");
	}
	
	public ExtendsClass(int i, String s) {
		super(i, s); // super는 상위 클래스로
	}
	
	@Override
	public void Fun2() {
		// TODO Auto-generated method stub
		System.out.println("Fun2() START");
	}
	
}
