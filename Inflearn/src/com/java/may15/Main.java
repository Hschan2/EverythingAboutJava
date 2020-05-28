package com.java.may15;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetterSetterClass obj1 = new GetterSetterClass("홍성찬", 85);
		
		obj1.getInfo();
		
		obj1.setScore(100);
		obj1.getInfo();
		
		obj1.setScore(45); // class 파일에 score가 50 미만이면 변경 X
		obj1.getInfo();
	}

}
