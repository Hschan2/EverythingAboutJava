package com.java.may16;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 상속
		inheritChildClass obj1 = new inheritChildClass();
		
		obj1.ParentClass();
		obj1.ChildClass();
		
		/*
		-- Study0516Parent Constructor --
		-- Study0516Child Constructor --
		-- Parent Class --
		-- Child Class --
		*/
		
		// Parent 클래스의 private 접근자는 Child 클래스에서 사용할 수 없다.
		// obj1.ParentPrivateClass();
		// The method ParentPrivateClass() from the type Study0516Parent is not visible
	}

}
