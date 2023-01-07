package com.java.may27;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class InputOutClass {
	public InputOutClass() {
		System.out.println("InputOutClass Constructor");
	}
	
	/*
	public void DataIO() {
		String str = "Hello Java";
		OutputStream outStream = null;
		DataOutputStream dataOutputstream = null; // outputStream을 확장한 기능
		try {
			outStream = new FileOutputStream("D:\\\\내 폴더\\\\bouncing+text.txt");
			dataOutputstream = new DataOutputStream(outStream);
			
			dataOutputstream.writeUTF(str);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (dataOutputstream != null) {
					dataOutputstream.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			try {
				if (outStream != null) {
					outStream.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	*/
	
	public void dataIO2() {
		InputStream inputStream = null;
		OutputStream outStream = null;
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputstream = null; // outputStream을 확장한 기능
		try {
			// byte의 입출력을 개선하여 문자열로 편리하게 사용하기 위해 dataInput, dataOutput 사용
			// 복사 붙여넣기
			inputStream = new FileInputStream("D:\\\\내 폴더\\\\bouncing+text.txt");
			dataInputStream = new DataInputStream(inputStream);
			
			String str = dataInputStream.readUTF();
			
			outStream = new FileOutputStream("Test");
			dataOutputstream = new DataOutputStream(outStream);
			
			dataOutputstream.writeUTF(str);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (dataOutputstream != null) {
					dataOutputstream.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			try {
				if (outStream != null) {
					outStream.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
