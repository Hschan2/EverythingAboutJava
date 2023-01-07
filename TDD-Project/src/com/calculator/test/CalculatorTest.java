package com.calculator.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.calculator.Calculator;

class CalculatorTest {
	// com.calculator.test -> 오른쪽 마우스 -> new -> JUnit Test Case -> name과 Class under test 설정 
	
	@Test // 아래에 있는 것은 테스트 코드다 라는 의미의 어노테이션
	void testSum() {
		Calculator calculator = new Calculator(); // 사용해야할 클래스를 가져온다.
		assertEquals(30, calculator.sum(10, 20)); // assertEquals(a,b) => 객체 a와 b의 값이 같은지 확인해달라! -> JUnit에서 가장 많이 활용하는 메소드
		// 즉, calculator 객체에 있는 sum 함수에 10과 20의 값을 넘겼을 때 값이 30이 되는지 확인해달라!
		
		// 실행했을 때 JUnit의 Bar가 초록색이 된다면 성공했다. 빨간색이 된다면 실패했다.
	}

}
