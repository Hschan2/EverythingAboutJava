package com.java.may19;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 내부 클래스
		InternalClass obj1 = new InternalClass();
		System.out.println("obj1.num : " + obj1.num);
		System.out.println("obj1.str1 : " + obj1.str1);
		System.out.println();
		
		/*
		obj1.num : 10
		obj1.str1 : java 
		*/
		
		InternalClass.InnerClass Inobj1 = obj1.new InnerClass();
		System.out.println("Inobj1.num : " + Inobj1.num);
		System.out.println("Inobj1.str1 : " + Inobj1.str3);
		System.out.println();
		
		/*
		InnerClass constructor
		Inobj1.num : 100
		Inobj1.str1 : java
		*/
		
		InternalClass.SInnerClass Sobj1 = new InternalClass.SInnerClass();
		System.out.println("Sobj1.num : " + Sobj1.num);
		System.out.println("Sobj1.str1 : " + Sobj1.str3);
		System.out.println();
		
		/*
		InnerClass constructor
		Sobj1.num : 1000
		Sobj1.str1 : world
		*/
		
		// 익명 클래스
		new AnonymousClass() { // 이름이 없다 -> 딱 한 번만 쓰고 버릴 클래스
			// Override => 부모 클래스의 기능을 자식 클래스에서 재정의하여 사용
			@Override
			public void method() { // AnonymousClass의 method 객체를 재정의
				System.out.println("AnonymousClass.method START From Main"); // AnonymousClass의 method의 내부를 재정의
			}
		}.method(); // 필요한 메서드를 사용하기 위해 .method(); 호출
	}

	/*
	AnonymousClass Constructor
	AnonymousClass.method START From Main
	-> AnonymousClass 안에 있는 method를 호출하는 것이 아닌 @Override된 Main의 재정의된 method 객체를 호출
	*/
}
