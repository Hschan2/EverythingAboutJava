package com.java.StrategyPattern;

public class FlyingStrategy implements MovingStrategy {
	
	@Override
	public void move() {
		System.out.println("날아서 이동");
	}
}
