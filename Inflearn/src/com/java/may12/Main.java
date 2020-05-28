package com.java.may12;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetClass obj1 = new GetClass();
		GetClass obj2 = new GetClass();
		
		//System.out.println(obj1);
		obj1.getInfo();
		obj1 = null;
		//obj1.getInfo();
		
		obj2.getInfo();
	}

}
