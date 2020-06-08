package com.java.cleancode;

public class CleanCodeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int addResult = 0, goodAdd = 0;
		
		StringCalculator obj1 = new StringCalculator();
		
		addResult = obj1.add("10,20");
		
		System.out.println(addResult);
		
		goodAdd = obj1.goodAdd("10,20");
		
		System.out.println(goodAdd);
	}

}
