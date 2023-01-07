package com.java.may18;

// 부모 클래스 위에 Object 클래스가 있다. Object 클래스가 최상위 클래스
public class InheritParentClass {
	int openYear = 1995;
	// Override => 부모 클래스의 기능을 자식 클래스에서 재정의하여 사용
	public InheritParentClass() {
		System.out.println("-- Parent Constructor --");
	}

	public void makeJJajang() {
		System.out.println("-- Parent JJajang --");
	}
}
