package com.java.may18;

public class InheritChildClass extends InheritParentClass {
	int openYear = 2000;
	
	// Override => 부모 클래스의 기능을 자식 클래스에서 재정의하여 사용
	public InheritChildClass() {
		System.out.println("-- Child Constructor --");
	}
	
	@Override
	public void makeJJajang() {
		System.out.println("-- Child More JJajang --");
	}
	
	public void getOpenYear() {
		System.out.println("Child's openYear : " + this.openYear); // this는 현재 클래스 안에 있는 openYear를 가져온다
		System.out.println("Parent's openYear : " + super.openYear); // super는 상위 클래스(부모 클래스)에 openYear의 값을 가져온다.
	}
}
