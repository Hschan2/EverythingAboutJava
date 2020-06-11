package com.java.StrategyPattern;

public abstract class Robot {
	private String name;
	// 여기까지는 전략 패턴을 사용하기 전
	// ------------------------
	private AttackStrategy attackStrategy; // attackStrategy 인터페이스가 공격 방식 역할
	private MovingStrategy movingStrategy; // movingStrategy 인터페이스가 이동 방식 역할
	
	public Robot(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void attact() {
		attackStrategy.attack(); // attackStrategy 인터페이스의 attack()
	}
	public void move() {
		movingStrategy.move(); // movingStrategy 인터페이스의 move()
	}
	
	public void setAttackStrategy(AttackStrategy attackStrategy) {
		this.attackStrategy = attackStrategy;
	}
	
	public void setMovingStrategy(MovingStrategy movingStrategy) {
		this.movingStrategy = movingStrategy;
	}
}
