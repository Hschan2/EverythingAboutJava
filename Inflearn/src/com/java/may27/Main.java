package com.java.may27;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// input과 관련된 것은 예외 처리(try catch) 꼭 필요
		
		/*
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("D:\\내 폴더\\bouncing+text.txt");
			int data = 0;
			
			while(true) {
				try {
					data = inputStream.read();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if (data == -1) { // 더 이상 읽을 데이터가 없을 때
					break;
				}
				System.out.println("data : " + data); // 아스키 코드로 출력
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		System.out.println();
		
		InputStream inputStream2 = null;
		try {
			inputStream2 = new FileInputStream("D:\\내 폴더\\bouncing+text.txt");
			int data2 = 0;
			byte[] bs = new byte[3]; // 3개씩 불러온다
			
			while(true) {
				try {
					data2 = inputStream2.read(bs);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if (data2 == -1) { // 더 이상 읽을 데이터가 없을 때
					break;
				}
				System.out.println("data : " + data2); // 아스키 코드로 출력
				for (int i = 0; i < bs.length; i++) {
					System.out.println("bs[" + i + "] : " + bs[i]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (inputStream2 != null) {
					inputStream2.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		System.out.println();
		*/
		
		/*
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream("D:\\내 폴더\\Test.txt");
			String data = "Test";
			byte[] arr = data.getBytes();
			
			try {
				outStream.write(arr);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (outStream != null) {
					outStream.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		*/
		
		/*
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream("D:\\내 폴더\\Test.txt");
			String data = "Test";
			byte[] arr = data.getBytes();
			
			try {
				outStream.write(arr, 0, 3); // 0부터 3번까지, 즉 3개만 입력된다.(스페이스까지 포함)
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (outStream != null) {
					outStream.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		*/
		
		// 복사 붙여넣기
		InputStream inputStream = null;
		OutputStream outStream = null;
		try {
			inputStream = new FileInputStream("D:\\\\내 폴더\\\\bouncing+text.txt");
			outStream = new FileOutputStream("D:\\내 폴더\\Test.txt");
			
			byte[] arr = new byte[3];
			
			while(true) { 
				int len = inputStream.read(arr); // 파일 불러오기
				if (len == -1) {
					break;
				}
				outStream.write(arr, 0, len); // 복사한다.
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
