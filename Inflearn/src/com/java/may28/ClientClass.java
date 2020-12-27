package com.java.may28;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket = null;
		
		// 입출력 받기
		InputStream inputStream = null;
		DataInputStream dataInputStream = null;
		
		OutputStream outputStream = null;
		DataOutputStream dataOutputStream = null;
		
		// 메세지 입력을 위한 스캐너
		Scanner scanner = null;
		
		// 네트워크와 관련된 코드는 예외 처리 필수
		try {
			socket = new Socket("localhost", 9000); // localhost의 포트 번호 9000번으로 연결하라, 127.0.0.1
			System.out.println("Server Connect");
			System.out.println("socket : " + socket);
			
			// 양방향 통신을 위해
			outputStream = socket.getOutputStream(); // 이것을 확장하기 위해서는
			dataOutputStream = new DataOutputStream(outputStream); // 이게 필요하다
			
			inputStream = socket.getInputStream(); // 이것을 확장하기 위해서는
			dataInputStream = new DataInputStream(inputStream); // 이게 필요하다
			
			scanner = new Scanner(System.in);
			
			while(true) {
				System.out.println("메세지 입력 중");
				String outMessage = scanner.nextLine();
				dataOutputStream.writeUTF(outMessage);
				// writeUTF()은 문자열을 출력하기 위해 사용
				dataOutputStream.flush(); // 데이터 비어주기
				
				String inMessage = dataInputStream.readUTF();
				System.out.println("inMessage : " + inMessage);
				
				if (outMessage.equals("STOP")) break; // STOP을 메세지로 보내면 종료
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally { // 연결이 되었는지 확인하기 위해 마지막으로 체크
			try {
				if (socket != null) socket.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

}
