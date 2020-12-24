package com.java.opentutorials;

public class ExtentsMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Java의 상속(extents)를 생활 코딩에서 복습하자
		
		ExtendsClass ec = new ExtendsClass();
		int r = ec.sum(1, 2);
		int r2 = ec.sum(1, 2, 3);
		
		System.out.println(r);
		System.out.println(r2);
		
		cal c = new cal();
		System.out.println(c.sum(2, 3));
		System.out.println(c.minus(1, 2)); // cal 클래스는 ExtendsClass을 상속받기 때문에 sum도 사용할 수 있고 minus는 당연히 사용할 수 있다
		System.out.println(c.sum(3, 3));
		System.out.println(c.sum(1, 2, 3));
		
		ExtendsCalClass ecc = new ExtendsCalClass(1, 2);
		int eccR = ecc.sum();
		
		System.out.println(eccR);
		
		ExtendsCalClassChild eccc = new ExtendsCalClassChild(1, 2);
		int ecccR = eccc.sum(); // ExtendsCalClassChild에서 부모 클래스 ExtendsCalClass로 값을 올려서 출력
		
		System.out.println(ecccR); // ExtendsCalClassChild에서 부모 클래스 ExtendsCalClass로 값을 올려서 출력
		
		int m = eccc.minus(); // ExtendsCalClassChild에서 바로 호출
		
		System.out.println(m); // ExtendsCalClassChild에서 바로 호출
		
		// 더 이상 클래스를 상속받지 못하게 하고 싶다? 메소드를 오버라이딩 하지 못하게 하고 싶다? 변수를 마음대로 수정하지 못하게 하고 싶다? => final
		// 클래스를 상속받아 사용하고 싶은 사용자에게 특정 메소드는 꼭 구현해서 사용해라 => abstract
	}

}
