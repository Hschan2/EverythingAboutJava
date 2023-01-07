package com.java.may19;
// 내부 클래스 => 클래스 안에 또 다른 클래스 선언 -> 두 클래스의 멤버에 쉽게 접근 가능
// 실무에서는 많이 사용하지 않음 -> 꼬일 수가 있다
public class InternalClass {
	int num = 10;
	String str1 = "java";
	static String str2 = "world";
	
	public InternalClass() {
		System.out.println("InternalClass Constructor");
	}
	
	class InnerClass {
		int num = 100;
		String str3 = str1;
		
		public InnerClass() {
			System.out.println("InnerClass Constructor");
		}
	}
	
	static class SInnerClass {
		int num = 1000;
		String str3 = str2; // str1 은 static 변수가 아니여서 불가
		
		public SInnerClass() {
			System.out.println("SInnerClass Constructor");
		}
	}
}
