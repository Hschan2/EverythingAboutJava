package com.java.may28;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket ServerSocket = null;
		Socket socket = null;
		
		// 입출력 받기
		InputStream inputStream = null;
		DataInputStream dataInputStream = null;
		
		OutputStream outputStream = null;
		DataOutputStream dataOutputStream = null;
		
		// 네트워크와 관련된 코드는 예외 처리 필수
		try {
			// 포트 번호 9000번을 이용해 서버 생성
			ServerSocket = new ServerSocket(9000); // 서버 소켓 생성, 포트 번호 9000
			System.out.println("Server Socket Ready");
			
			// 서버에 ServerSocket를 준비하고 클라이언트에서 Socket를 이용해서 접속
			socket = ServerSocket.accept(); // 서버 소켓을 소켓이 받아라, 서버소켓 반환
			// ClientClass에서 메세지를 보내면 이를 accept해 받아옴
			System.out.println("Client Connect");
			System.out.println("socket : " + socket);
			
			// 양방향 통신을 위해
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			
			while(true) {
				String clientMessage = dataInputStream.readUTF(); // 클라이언트가 서버쪽으로 보낸 것을 받음
				// readUTF()은 문자열을 출력하기 위해 사용
				System.out.println("client Message : " + clientMessage);
				
				dataOutputStream.writeUTF("메세지 전송 완료"); // 서버에 메세지가 정상적으로 왔을 시 클라이언트에게 보낼 메세지
				dataOutputStream.flush(); // 데이터 값 비우기
				
				if (clientMessage.equals("STOP")) break; // STOP이라는 메세지를 받으면 종료
			}
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
