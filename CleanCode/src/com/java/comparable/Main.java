package com.java.comparable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// comparable => 데이터를 순서대로 정렬할 일이 생길 때 자주 사용
		
		ComparableClass comparable1 = new ComparableClass();
		comparable1.Arrays();
		comparable1.Arrays2();
		comparable1.ArraysReverse();
		
		// List를 이용한 정렬
		List<String> list = new ArrayList<>();
		
		list.add("z");
		list.add("a");
		list.add("A");
		list.add("c");
		
		Collections.sort(list); // list에서 대소문자 구분해서 정렬
		System.out.println(list);
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER); // 대소문자 구분 없이 정렬
		System.out.println(list);
		Collections.sort(list, Collections.reverseOrder()); // Collections.reverseOrder() => 대소문자 구분해서 역순으로 정렬

	}
}
