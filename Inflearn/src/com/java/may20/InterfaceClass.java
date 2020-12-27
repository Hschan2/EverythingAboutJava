package com.java.may20;
// 상위(부모) 클래스를 상속받을 때는 extends, 인터페이스를 구현할 때는 implements
public class InterfaceClass implements InterfaceA, InterfaceB { // 인터페이스를 여러 개 받을 수 있는 것을 다형성
	public InterfaceClass() {
		System.out.println("InterfaceClass Constructor");
	}

	@Override // override는 재정의한다는 표시
	public void funB() {
		// TODO Auto-generated method stub
		System.out.println("funB START");
	}

	@Override // override는 재정의한다는 표시
	public void funA() {
		// TODO Auto-generated method stub
		System.out.println("funA START");
	}
	
	
}
