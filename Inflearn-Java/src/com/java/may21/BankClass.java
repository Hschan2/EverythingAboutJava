package com.java.may21;

public class BankClass extends AbstractBank {
	public BankClass(String name, String account, int totalAmount) {
		super(name, account, totalAmount);
	}

	@Override
	public void installmentSavings() {
		// TODO Auto-generated method stub
		System.out.println("저축 시작");
	}

	@Override
	public void cancellation() {
		// TODO Auto-generated method stub
		System.out.println("해약 시작");
	}

}
