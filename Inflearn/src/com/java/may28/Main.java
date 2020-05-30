package com.java.may28;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) { // ServerClass
		// TODO Auto-generated method stub
		ServerSocket ServerSocket = null;
		Socket socket = null;
		
		// 네트워크와 관련된 코드는 예외 처리 필수
		try {
			// 포트 번호 9000번을 이용해 서버 생성
			ServerSocket = new ServerSocket(9000); // 서버 소켓 생성, 포트 번호 9000
			System.out.println("Server Socket Ready");
			
			// 서버에 ServerSocket를 준비하고 클라이언트에서 Socket를 이용해서 접속
			socket = ServerSocket.accept(); // 서버 소켓을 소켓이 받아라, 서버소켓 반환
			System.out.println("Client Connect");
			System.out.println("socket : " + socket);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally { // 연결이 되었는지 확인하기 위해 마지막으로 체크
			try {
				if (socket != null) socket.close();
				if (ServerSocket != null) ServerSocket.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}

}
