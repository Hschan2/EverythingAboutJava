package com.java.may23;
// 문자열을 다루는 String 클래스(객체)는 데이터가 변화하면 메모리상의 변화가 커 속도가 느리다
// 이를 보완하기 위해 StringBuffer, StringBuilder가 있다
public class StringClass {
	String str;
	
	public StringClass() {
		System.out.println("StringClass Constructor");
	}
	
	public void StringBuffer(String string) {
		this.str = string;
	}
	
	public void getInfo() {
		System.out.println(str);
	}

}
