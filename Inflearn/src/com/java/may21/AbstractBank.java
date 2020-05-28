package com.java.may21;

public abstract class AbstractBank {
	String name;
	String account;
	int totalAmount;
	
	public AbstractBank() {
		System.out.println("AbstractBank Constructor");
	}
	
	public AbstractBank(String name, String account, int totalAmount) {
		this.name = name;
		this.account = account;
		this.totalAmount = totalAmount;
	}
	
	// 예금
	public void deposit() {
		System.out.println("예금 시작");
	}
	
	// 출금
	public void withdraw() {
		System.out.println("출금 시작");
	}
	
	// 적금
	public abstract void installmentSavings();
	
	// 해약
	public abstract void cancellation();
	
	public void getInfo() {
		System.out.printf("name : %s\n", name);
		System.out.printf("account : %s\n", account);
		System.out.printf("totalAmount : %d\n", totalAmount);
	}
}
