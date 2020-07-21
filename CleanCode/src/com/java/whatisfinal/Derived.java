package com.java.whatisfinal;

public class Derived extends Base { // Base 상속
	@Override
	public void m1() { // 오버라이딩, 즉 메서드 수정이 가능한 이유는 Base의 m1이 public void이기 때문
		
	}
	
//	@Override
//	public void m2() { // 오버라이딩, 즉 메서드 수정이 불가능한 이유 m2가 public final void, final로 선언됐기 때문에
//		
//	}
}
