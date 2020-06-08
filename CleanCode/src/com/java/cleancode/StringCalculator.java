package com.java.cleancode;

public class StringCalculator {
	// 클린코드가 적용되지 않았을 때
	// 해당 메소드는 너무 많은 역할을 하고 있다.
	public static int add(String text) {
		int result = 0;
		if(text == null || text.isEmpty()) { // 역할 1, if에서 바로 return하는 것이 좋다. else가 오는 것은 비효율
			result = 0;
		} else { // else는 되도록 쓰지 않는 것이 좋다.
			String[] values = text.split(",|:"); // 역할 2
			for(String value : values) {
				result += Integer.parseInt(value); // 역할 3
			}
		}
		return result;
	}
	
	// 클린코드를 적용했을 때
	// 하나의 메소드는 하나의 역할을 하는 것이 좋다.
	public static int goodAdd(String text) {
		if(isBlank(text)) {
			return 0;
		}
		return sum(toInts(split(text)));
	}
	
	private static boolean isBlank(String text) { // 값이 있는지 없는지 판단 역할
		return text == null || text.isEmpty();
	}
	
	private static String[] split(String text) { // , 혹은 :를 기준으로 숫자 나누기
		return text.split(",|:");
	}
	
	private static int[] toInts(String[] values) { // 문자열들을 숫자로 변환 역할
		int[] numbers = new int[values.length];
		for(int i=0; i<values.length; i++) numbers[i] = toInt(values[i]);
		return numbers;
	}
	
	private static int toInt(String value) { // 문자열을 숫자로 변환 및 양수 판단 역할
		int number = Integer.parseInt(value);
		if(number < 0) throw new RuntimeException(); // 음수면 런타임 에러를 던져라
		return number;
	}
	
	private static int sum(int[] numbers) { // 합계 역할
		int result = 0;
		for(int number : numbers) result += number;
		return result;
	}
}
