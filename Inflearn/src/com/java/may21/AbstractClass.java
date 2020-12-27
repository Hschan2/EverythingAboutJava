package com.java.may21;
// 추상클래스 => 인터페이스와 비슷하게 구체화(구현)되지 않는 멤버를 이용해 클래스를 만듬
// 기존 클래스 기능 + 인터페이스와 같은 선언만 하는 기능
// 이는 extends(상속)을 받은 클래스에서 구현해 사용
public abstract class AbstractClass {
	int num;
	String str;
	
	// 클래스의 기본 기능
	public AbstractClass() {
		System.out.println("AbstractClass Constructor");
	}
	
	public AbstractClass(int num, String str) {
		this.num = num;
		this.str = str;
		
		System.out.println("num : " + num);
		System.out.println("str : " + str);
	}
	
	public void Fun1() {
		System.out.println("Fun1() START");
	}
	
	// 추상 클래스가 가지고 있는 추상 메서드
	public abstract void Fun2();
	
	/*
	인터페이스와 추상 메서드의 차이점
	  인터페이스
	  - 상수, 추상 메서드만 가짐
	  - 추상 메서드를 구현만 하도록
	  - 다형성 지원
	  - 다양한 형태의 프로그래밍을 할 경우 추천
	  추상 메서드
	  - 클래스가 가지는 모든 속성과 기능 가짐
	  - 추상 메서드 구현 및 상속의 기능 가짐
	  - 단일 상속만 지원(extends를 이용해서 엄연한 클래스이기 때문)
	*/
}
