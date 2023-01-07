package com.java.may22;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 람다식 => 인터페이스를 선언하고 override하고 호출하는 과정을 모두 버리고 매개변수를 주고 실행문만 작성
		// 람다식은 기본적으로 인터페이스를 이용해 함수를 만들어 사용
		LambdaInterface obj1 = (String s1, String s2, String s3) -> {
			System.out.println("s1 : " + s1 + "\ns2 : " + s2 + "\ns3 : " + s3);
		};
		obj1.method("HONG", "SEONG", "CHAN");
		
		// 매개변수가 1개이거나 데이터 타입타입이 같은 경우 데이터 타입 생략 가능
		LambdaInterface2 obj2 = (s1) -> {
			System.out.println("s1 : " + s1);
		};
		obj2.method("HONG");
		
		// 매개변수가 1개이거나 실행문이 1개일 때. ()와 {} 생략 가능
		LambdaInterface3 obj3 = s1 -> System.out.println("s1 : " + s1);
		
		obj3.method("HONG");
		
		// 매개변수가 없을 때, {}은 생략하고 ()만 적는다
		LambdaInterface4 obj4 = () -> System.out.println("No Parameter");
		
		obj4.method();
		
		// 매개변수가 없을 때, {}은 생략하고 ()만 적는다
		LambdaInterface5 obj5 = (x, y) -> {
			int result = x + y;
			return result;
		};
		System.out.println("obj5.method(10, 20) : " + obj5.method(10, 20));
	}

}
