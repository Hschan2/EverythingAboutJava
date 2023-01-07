package com.java.may26;

public class ExceptionClass {
	// throws 예외 처리 상황
	// 에러 처리를 직접 하겠다 => try catch,
	// 에러 처리를 넘기겠다(돌려서 주겠다) => throws 
	public ExceptionClass() { 
		System.out.println("ExceptionClass Constructor");
	}
	
	public void firstMethod() throws Exception {
		secondMethod();
	}
	
	public void secondMethod() throws Exception {
		thirdMethod();
	}
	
	public void thirdMethod() throws Exception {
		System.out.println("10 / 0 = " + (10 / 0));
	}
}
