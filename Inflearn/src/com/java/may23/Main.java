package com.java.may23;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 문자열을 다루는 String 클래스(객체)는 데이터가 변화하면 메모리상의 변화가 커 속도가 느리다
		// 이를 보완하기 위해 StringBuffer, StringBuilder가 있다
		StringBuffer str1 = new StringBuffer("JAVA");
		System.out.println("StringBuffer : " + str1);
		
		str1.append("_8");
		System.out.println("StringBuffer Append : " + str1);
		
		System.out.println("length : " + str1.length());
		
		str1.insert(str1.length(), "!");
		System.out.println("str1.length + insert : " + str1);
		
		str1.delete(0, 2); // 0~2 번째까지 문자 삭제 => J, A 삭제
		System.out.println("str1.delete(0,2) : " + str1);
		
		// StringBulider
		StringBuilder strB = new StringBuilder("JAVA");
		System.out.println("StringBuilder : " + strB);
		
		// 클래스 이용해서
		StringClass str2 = new StringClass();
		str2.StringBuffer("StringClass String : " + "HONG");
		
		str2.getInfo();
		
		// 기본
		String str3 = new String("Java");
		System.out.println("String : " + str3);
		
		str3 = str3 + "_8";
		System.out.println("str3 + _8 : " + str3);
	}

}
