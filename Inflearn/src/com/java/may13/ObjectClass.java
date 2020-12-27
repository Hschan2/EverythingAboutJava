package com.java.may13;

public class ObjectClass {
	
	public int x, y;
	
	public ObjectClass(String n, int[] arr) {
		System.out.println("-- ObjectClass --");
		System.out.println("n : " + n + "\narr : " + arr);
	}
	
	public ObjectClass(int x, int y) {
		System.out.println("-- ObjectClass --");
		
		this.x = x;
		this.y = y;
	}
	
	public void getInfo() {
		System.out.println("x : " + x + "\ny : " + y);
	}
}
