package com.java.may15;

public class GetterSetterClass {
	private String name;
	private int score;
	
	public GetterSetterClass(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public void getInfo() {
		System.out.println("-- getInfo() --");
		
		System.out.println("name : " + name);
		System.out.println("score : " + score);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		if(score > 50)
			this.score = score;
	}
	
	
}
