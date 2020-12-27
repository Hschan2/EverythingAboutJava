package com.java.cleancode;

public class Positive { // 0보다 큰 값을 판단하기 위해, 조금 더 객체지향적으로
	private int number;
	
	public Positive(String value) { // 문자열을 숫자로 변환
		this(Integer.parseInt(value));
	}
	
	public Positive(int number) { // 양수 혹은 음수 판단
		if(number < 0) throw new RuntimeException();
		this.number = number;
	}
	
	public int getNumber() { // 값 넘기기
		return number;
	}
	
	public Positive add(Positive other) { // 덧셈
		return new Positive(this.number + other.getNumber());
	}
}
