package com.java.may18;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InheritChildClass obj1 = new InheritChildClass(); // Study0518Child이 데이터 타입
		
		obj1.makeJJajang();
		
		/*
		-- Parent Constructor --
		-- Child Constructor --
		-- Child More JJajang --
		*/
		
		InheritParentClass objArr[] = new InheritParentClass[2]; // 2개 만들겠다
		InheritParentClass first = new InheritFirstClass(); // Study0518First는 Parent 클래스를 상속 받아서 Study0518Parent 데이터 타입 사용 가능
		InheritParentClass second = new InheritSecondClass(); // Study0518Second는 Parent 클래스를 상속 받아서 Study0518Parent 데이터 타입 사용 가능
		
		objArr[0] = first;
		objArr[1] = second;
		
		for (int i = 0; i < objArr.length; i++) {
			objArr[i].makeJJajang();
		}
		
		/*
		-- Parent Constructor --
		-- Child Constructor --
		-- Child More JJajang --
		-- Parent Constructor --
		-- First Constructor --
		-- Parent Constructor --
		-- Second Constructor --
		-- First JJajang --
		-- Second JJajang --
		*/
		
		InheritChildClass obj2 = new InheritChildClass();
		
		obj2.getOpenYear();
		
		/*
		-- Parent Constructor --
		-- Child Constructor --
		Child's openYear : 2000
		Parent's openYear : 1995
		*/
	}

}
