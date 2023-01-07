package com.java.may25;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Collections => 자료형
		// 배열과 같이 자료(데이터)를 효율적으로 관리하기 위한 방법
		// List => 인터페이스, 인덱스를 이용해 데이터 관리
		
		ArrayList<String> list1 = new ArrayList<String>(); // ArrayList<자료형>
	
		list1.add("HONG"); // 추가
		list1.add("CHAN");
		
		list1.set(1, "SEONG"); // 수정
		
		String listValue1 = list1.get(1); // 인덱스의 데이터 추출
		
		//listValue1 = list1.remove(1); // 데이터 삭제
		//list1.clear(); // 전체 삭제
		
		boolean listCheck =  list1.isEmpty(); // 데이터가 비어있는지 아닌지 유무 확인
		
		System.out.println(listValue1);
		System.out.println(listCheck);
		
		/*
		SEONG
		false
		*/
		
		System.out.println();
		
		// Map => 인터페이스, Key를 이용해 데이터를 관리
		// 각 데이터마다 Key 값이 있으며 중복 불가, 데이터 값은 중복 가능
		HashMap<Integer, String> map1 = new HashMap<Integer, String>(); // HashMap<Key값 자료형, 데이터값 자료형>
		
		map1.put(1, "HONG");
		System.out.println(map1);
		System.out.println(map1.size()); // Map의 총 크기
		
		map1.put(1, "HONGSEONGCHAN"); // 데이터 교체
		
		String mapValue1 = map1.get(1); // 데이터 추출
		System.out.println(mapValue1);
		
		// map1.remove(1); // 데이터 삭제
		
		boolean mapCheck1 = map1.containsKey(1); // Key값으로 특정 제이터 포함 유무, Key값 1이 있는가
		System.out.println(mapCheck1);
		
		boolean mapCheck2 = map1.containsValue("HONG"); // Map 전체 데이터 중에서 특정 데이터 값 포함 유무, HONG이라는 데이터를 가진 Map이 있는가
		System.out.println(mapCheck2);
		
		map1.clear(); // 데이터 전체 제거
		
		boolean mapCheck3 = map1.isEmpty(); // 데이터 유무
		System.out.println(mapCheck3);
		
	}

}
