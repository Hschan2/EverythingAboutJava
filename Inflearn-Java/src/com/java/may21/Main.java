package com.java.may21;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractClass obj1 = new ExtendsClass(27, "HONG");
		/*
		num : 27
		str : HONG
		*/
		AbstractClass obj2 = new ExtendsClass();
		
		obj1.Fun1();
		obj1.Fun2();
		
		obj2.Fun1();
		obj2.Fun2();
		
		/*
		AbstractClass Constructor
		ExtendsClass Constructor
		Fun1() START
		Fun2() START
		Fun1() START
		Fun2() START
		*/
		
		AbstractBank obj3 = new BankClass("HONG", "012-345-6789", 10000);
		
		obj3.deposit();
		obj3.withdraw();
		obj3.installmentSavings();
		obj3.cancellation();
		
		System.out.println();
		
		obj3.getInfo();
		
		/*
		예금 시작
		출금 시작
		저축 시작
		해약 시작
		
		name : HONG
		account : 100
		totalAmount : 100
		*/
	}

}
