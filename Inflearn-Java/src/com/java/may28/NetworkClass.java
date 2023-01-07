package com.java.may28;

import java.net.Socket;

public class NetworkClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket = null;
		
		// 네트워크와 관련된 코드는 예외 처리 필수
		try {
			socket = new Socket("localhost", 9000); // localhost의 포트 번호 9000번으로 연결하라, 127.0.0.1
			System.out.println("Server Connect");
			System.out.println("socket : " + socket);
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
