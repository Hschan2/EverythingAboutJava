package com.java.comparable;

import java.util.Arrays;

public class ComparableClass {
	// 기본적으로 정렬을 수행. 숫자 => 오름차순, 문자 => 알파벳 순
	// primitive 타입, integer, string, list 등 정렬이 필요한 데이터는 모두 comparable 인터페이스를 implement
	
	// 1. Arrays.sort() => 기본 타입 배열 (byte[], char[], double[], int[] ...)
	String[] s = {"b","A","D","C","a","F"};
	
	public void Arrays() {
		Arrays.sort(s);
		System.out.println(Arrays.toString(s)); // [A, C, D, F, a, b]
	}
	
	public void Arrays2() {
		Arrays.sort(s, String.CASE_INSENSITIVE_ORDER); // 대소문자 구분 없이 정렬. String.CASE_INSENSITIVE_ORDER => 대소문자 구분 없이 정렬하기 위해
		System.out.println(Arrays.toString(s)); // [A, C, D, F, a, b]
	}
	
	public void ArraysReverse() {
		for(int i = 0; i<s.length/2; i++) {
			String tmp = s[i];
			s[i] = s[(s.length - 1) - i]; // 맨 마지막부터 순서대로 대입
			s[(s.length - 1) - i] = tmp;
		}
		System.out.println(Arrays.toString(s));
	}
	
	// 객체 타입을 정렬하기 위해서
	Actor[] arr = new Actor[] {
			new Actor("박보검", 1993),
			new Actor("유승호", 1993),
			new Actor("차은우", 1996),
			new Actor("서강준", 1994)
	};
	
	public void ArraysMethod() { // 이는 오류가 난다. 객체를 Comparable 적용할 수 없다는 이유로
		Arrays.sort(arr);
		for(Actor result : arr) {
			System.out.println(result);
		}
	}
	
	// 그럼 어떻게 해야 할까? => Actor 클래스로 가보자
}
