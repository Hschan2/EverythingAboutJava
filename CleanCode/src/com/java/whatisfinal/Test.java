package com.java.whatisfinal;

public class Test {
	// blank final 변수
	// blank final 변수에서는 변수마다 인스턴스마다 다른 값을 갖는다.
	// final 변수이어도 초기화가 다르게 된다면 static을 사용하지 않게 된다.
	
	private final int value; // 먼저 변수 선언(인스턴스)
	
	public Test(int value) { // 각각 다른 값을 갖도록 초기화 가능, 한 번 값을 할당하면 다음부터는 수정 불가
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
