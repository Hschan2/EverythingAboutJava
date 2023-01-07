package com.java.may14;

// may11 패키지 추가
import com.java.may11.GetSetClass;
import com.java.may12.GetClass;
import com.java.may13.ObjectClass;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Package & Import
		GetSetClass may11 = new GetSetClass(); // may11 클래스 사용
		GetClass may12 = new GetClass();
		ObjectClass may13 = new ObjectClass(10, 20);
		
		may11.setInfo("홍성찬", 27);
		may11.getInfo();
		
		may12.getInfo();
		
		may13.getInfo();
		
		// Static
		StaticClass Park = new StaticClass("박");
		StaticClass Lee = new StaticClass("이");
		
		Park.saveMoney(100);
		Lee.saveMoney(300);
		
		Lee.getBankInfo();
		
		Park.spnedMoney(400);
		
		Lee.getBankInfo();
	}

}
