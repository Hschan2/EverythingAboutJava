package com.java.StrategyPattern;

public class WalkingStrategy implements MovingStrategy {
	
	@Override
	public void move() {
		System.out.println("걸어서 이동");
	}
}
