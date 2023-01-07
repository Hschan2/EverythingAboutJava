package com.java.may26;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 예외처리 (소프트웨어 실행 중 오류 발생 시 시스템에 동작에 문제가 없도록 예방)
		int i = 10, j = 0, r = 0;
		
		System.out.println("Excetion Before");
		
		try {
			r = i / j;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			String msg = e.getMessage();
			System.out.println("예외처리 : " + msg);
		}
		
		System.out.println("Excetion After"); // 예외 처리를 하지 않으면 실행 안함
		
		System.out.println();
		
		Scanner scanner = new Scanner(System.in);
		int[] iArr = {0,1,2,3,4,5};
		ArrayList<String> list = null;
		
		// 다양한 예외 처리 상황
		try {
			System.out.println("input i : ");
			i = scanner.nextInt();
			System.out.println("input j : ");
			j = scanner.nextInt();
			System.err.println("i / j : " + (i / j));
			
			for (int k = 0; k < 6; k++) {
				System.out.println("iArr[" + k + "] : " + iArr[k]);
			}
			
			System.out.println("list.size() : " + list.size());
		} catch (InputMismatchException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		
		System.out.println();
		
		// Finally 추가된 예외 처리 상황
		try {
			System.out.println("input i : ");
			i = scanner.nextInt();
			System.out.println("input j : ");
			j = scanner.nextInt();
			System.err.println("i / j : " + (i / j));
			
			for (int k = 0; k < 6; k++) {
				System.out.println("iArr[" + k + "] : " + iArr[k]);
			}
			
			System.out.println("list.size() : " + list.size());
		} catch (InputMismatchException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally { // 에러와 상관 없이 무조건 출력되게 한다
			System.out.println("에러와 상관없이 무조건 실행됩니다.");
		}
		
		System.out.println();
		
		ExceptionClass obj1 = new ExceptionClass();
		
		try {
			obj1.firstMethod();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
