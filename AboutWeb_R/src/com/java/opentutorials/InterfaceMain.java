package com.java.opentutorials;

public class InterfaceMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Java의 Interface를 생활 코딩에서 복습하자
		sum s = new sum(); // InterfacePublic itfp = new sum();도 가능 -> 인터페이스를 직접 호출
		int r = s.sum(1, 2);
		s.print();
		System.out.println(r);
		System.out.println(s.PI);
	}

}

class sum implements InterfacePublic { // 인터페이스를 구현한 클래스
	
	@Override
	public int sum(int a, int b) {
		return a+b;
	}
	@Override
	public void print() {
		System.out.println("출력!!!");
	}
}