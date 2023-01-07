package com.java.may27;

import java.io.BufferedReader;
import java.io.FileReader;

public class BufferRWClass {
	public BufferRWClass() {
		System.out.println("BufferRWClass Constructor");
	}
	
	public void BufferIO() {
		String fileName = "D:\\\\\\\\내 폴더\\\\\\\\bouncing+text.txt";
		
		BufferedReader br = null;
		FileReader fr = null;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr); // Buffer에 FileReader를 넣어 둔다
			
			String strLine;
			
			while((strLine = br.readLine()) != null) { // 문자를 읽는 것이기 때문에 readLine(한 라인씩 읽는다) 사용
				System.out.println(strLine);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
				if (fr != null) fr.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
