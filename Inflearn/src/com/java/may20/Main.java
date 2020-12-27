package com.java.may20;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterfaceClass obj1 = new InterfaceClass(); // funA(), funB() 모두 접근 가능
		
		obj1.funA();
		obj1.funB();
		
		/*
		InterfaceClass Constructor
		funA START
		funB START
		*/
		
		InterfaceA interA = new InterfaceClass(); // funA()만 접근 가능
		InterfaceB interB = new InterfaceClass(); // funB()만 접근 가능
		
		interA.funA();
		interB.funB();
		
		/*
		InterfaceClass Constructor
		InterfaceClass Constructor
		funA START
		funB START
		*/
		
		ToyInterface Toy = new ToyInterfaceClass();
		ToyInterface Airplane = new AirplaneClass();
		ToyInterface Toys[] = {Toy, Airplane};
		
		for (int i = 0; i < Toys.length; i++) {
			Toys[i].walk();
			Toys[i].run();
			Toys[i].alarm();
			Toys[i].light();
			
			System.out.println();
		}
		
		/*
		Toy can walk
		Toy can run
		Toy has alarm
		Toy has light
		
		Airplane can walk
		Airplane can run
		Airplane has alarm
		Airplane has light
		*/
	}

}
