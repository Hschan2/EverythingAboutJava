package com.java.StrategyPattern;

public class PunchStrategy implements AttackStrategy {
	
	@Override
	public void attack() {
		System.out.println("펀치 공격");
	}
}
