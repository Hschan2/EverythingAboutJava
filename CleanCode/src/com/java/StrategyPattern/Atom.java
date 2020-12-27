package com.java.StrategyPattern;

public class Atom extends Robot {

	public Atom(String name) {
		super(name);
	}
	
	// 아래 코드는 전략 패턴으로써 인터페이스를 이용해 구현했으므로 사용X
	/*
	@Override
	public void attact() {
		// TODO Auto-generated method stub
		System.out.println("펀치 공격");
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("아톰 이동");
	}
	*/
}
