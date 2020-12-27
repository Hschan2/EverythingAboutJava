package com.java.may16;

// 상속 => extends
public class inheritChildClass extends inheritParentClass {
	// 상속
	public inheritChildClass() {
		System.out.println("-- Study Child Constructor --");
	}
	
	public void ChildClass() {
		System.out.println("-- Child Class --");
	}
	
	// Parent 클래스의 private 접근자는 Child 클래스에서 사용할 수 없다.
}
