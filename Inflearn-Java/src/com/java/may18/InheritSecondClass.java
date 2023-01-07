package com.java.may18;

public class InheritSecondClass extends InheritParentClass {
	// Override => 부모 클래스의 기능을 자식 클래스에서 재정의하여 사용
	public InheritSecondClass() {
		System.out.println("-- Second Constructor --");
	}
	
	@Override
	public void makeJJajang() {
		System.out.println("-- Second JJajang --");
	}
}
