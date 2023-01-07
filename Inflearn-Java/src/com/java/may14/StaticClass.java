package com.java.may14;

public class StaticClass {
	String name;
	static int amount = 0; // 여러 개의 객체 생성 시 static 변수를 같이 쓰자 
						// Main 클래스에서 여러 객체를 생성 시 static의 amount는 공유 개념
						// 만약 a 객체의 amount에 100 넣었고
						// b 객체가 amount를 100을 뺐다면 총 amount의 수는 0
	
	public StaticClass(String name) {
		this.name = name;
	}
	
	public void saveMoney(int money) {
		amount += money;
		System.out.println("amount = " + amount);
	}
	
	public void spnedMoney(int money) {
		amount -= money;
		System.out.println("amount = " + amount);
	}
	
	public void getBankInfo() {
		System.out.println("Employee name = " + name);
		System.out.println("amount = " + amount);
	}
}
