package com.java.WebSocket;

public class WebSocket_Class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Web Socket (웹 소켓)
//			Client(Web Socket) <--- Message ---> Server(Web Socket)
//			두 프로그램 간의 메시지를 교환하기 위한 통신 방법
//			W3C와 IETF에 의해 자리 잡은 표준 프로토콜
//			현재 인터넷 환경(HTML5)에서 많이 사용
//			웹 소켓을 지원하는 브라우저의 경우 웹 소켓 프로토콜 사용
//			
//		웹 소켓 특징
//			양방향 통신 (Full-Duplex)
//				- 데이터 송수신을 동시에 처리 가능한 통신 방법
//				- 클라이언트와 서버가 서로에게 원할 때 데이터를 주고 받을 수 있음
//				- 통상ㅇ적인 Http 통신은 Client가 요청을 보내는 경우에만 Server가 응답하는 단방향 통신
//			
//			실시간 네트워킹 (Real Time-Networking)
//				- 웹 환경에서 연속된 데이터를 빠르게 노출
//				- 채팅, 주식, 비디오 데이터 등
//				- 여러 단말기에 빠르게 데이터를 교환
//				
//		웹 소켓 이전 비슷한 기술
//			Polling
//				서버로 일정 주기 요청 송신
//				Real-Time 통신에서는 언제 통신이 발생할 지 예측이 불가능하여 불필요한 요청(Request)과 연결(Connetion)을 생성
//				
//				Real-Time 통신이라고 부르기 애매할 정도의 실시간성
//			
//			Long Polling
//				서버에 요청을 보내고 이벤트가 생겨 응답을 받을 때까지 연결 종료를 하지 않고 응답 받으면 끊고 다시 재요청
//				많은 양의 메시지가 요청이 올 경우 Polling과 같음
//				
//			Streaming
//				서버에 요청을 보내고 끊기지 않은 연결 상태에서 끊임없이 데이터 수신
//				클라이언트에서 서버로의 데이터 송신이 어려움
//				
//			=> 결과적으로 이 모든 방법이 Http를 통해 통신하기 때문에 Request, Response 둘 다 Header가 불필요하게 크다
//			
//		웹 소켓 동작 방법
//			핸드 쉐이킹
//				Client에서 http(예시 80) 또는 https(예시 443)을 이용하여 통신
//				예. Client, http(80) or https(433) --- http ---> Server, http(80) or https(433)
//				반드시 GET 메서드를 사용!
//				Upgrade 헤더 필드가 명시되었을 경우, 송신자는 반드시 Upgrade 옵션을 지정한 Connection 헤더 필드도 전송!
//				101 Switching Protocols가 Response로 오면 웹 소켓이 연결
//				클라이언트로부터 받은 Sec-WebSocket-Key를 사용하여 계산된 값 = Sec-WebSocket-Accept => 클라이언트에서 계산한 값과 일치하지 않을 경우 연결하지 않음
//				
//			데이터 전송
//				핸드 쉐이크가 완료된 후
//				Client, ws(80) or wss(433) --- Web Socket ---> Server, ws(80) or wss(433)
//				Client, ws(80) or wss(433) <--- Web Socket --- Server, ws(80) or wss(433)
//				프로토콜이 ws로 변경, 데이터 보안을 위해서 SSL을 적용한 프로토콜
//				Message : 여러 Frame이 모여서 성하는 하나의 논리적 메시지 단위
//				Frame : Communication에서 가장 작은 단위의 데이터, 작은 헤더 + Payload로 구성
//				웹 소켓 통신에 사용되는 데이터 = UTF8 인코딩
//			
//				프레임이란? 
//						프레임 헤더 구조
//							END = 프레임 전체 메시지의 끝을 나타내는 플래그
//							RSV1
//							RSV2
//							RSV3
//							Opcode = Continue (0x0, 전체 메시지 일부), Text (0x1, 포함된 데이터가 UTF-8 텍스트), Binary (0x2, 포함된 데이터가 이진 데이터), Close (0x8, Close 핸드쉐이크를 시작) 
//							MASK
//							Length = 프레임에 포함된 데이터의 총 길이를 나타내는 단위
//							
//				데이터 양방향 전송 완료 후 Close Frame을 주고 받으며 연결 종료
//				
//			간단 정리
//				브라우저										웹 서버
//				WebSocket을 사용할 수 있음?
//				여기 WebSocket을 위한 Magic Key가 있음 ->
//													  	  <- HTTP
//				WebSocket이 준비
//				WebSocket을 연결 요구
//				
//				WebSocket(0x00 <UTF8 Payload> 0xff) ->
//													  	 WebSocekt
//													  	 0x00 <UTF8 Payload> 0xff
//				
//										데이터 교환
//				WebSocket (0x00 0xff, 연결을 종료 요구)->
//				
//			
//		웹 소켓 프로토콜 특징
//			최초 접속에서만 http 프로토콜 위에서 Handshaking을 하기 때문에 http header를 사용
//			웹 소켓을 위한 별도의 포트는 없고 기존 포트(http-80, https-443)을 사용
//			프레임으로 구성된 메시지라는 논리적 단위를 송수신
//			메시지에 포함될 수 있는 교환 가능한 메시지는 텍스트와 바이너리
//			
//		웹 소켓의 한계
//			Socket.io,
//			SockJS
//				HTML5 이전 기술로 구현된 서비스에서 웹 소켓처럼 사용할 수 있도록 도와주는 기술
//				자바스크립트를 이용해 브라우저 종류와 상관없이 실시간 웹을 구현
//				WebSocket, FlashSocket, AJAX Long Polling, AJAX Multi part Streaming, IFrame, JSONP Polling을 하나의 API로 추상화
//				브라우저와 웹 서버 종류와 버전을 파악하여 가장 적합한 기술을 선택해 사용하는 방식
//			
//			STOMP (Simple Text Oriented Message Protocol)
//				WebSocket은 문자열들을 주고 받을 수 있게 해줄 뿐이며 그 이상은 하지 않음
//				주고 받은 문자열의 해독은 온전히 애플리케이션에 맡김
//				HTTP는 형식을 정해두었기 때문에 모두가 약속을 따르기만 하면 해석 가능
//				WebSocket은 형식이 정해져 있기 않기 때문에 애플리케이션에서 쉽게 해석이 어려움
//				WebSocket 방식은 Sub-protocols을 사용하여 주고 받는 메시지의 형태를 약속하는 경우가 많음
//				
//				채팅 통신을 하기 위한 형식을 정의
//				HTTP와 유사하게 간단히 정의되어 해석하기 편한 프로토콜
//				일반적으로 웹 소켓 위에서 사용
//				
//				프레임 구조
//					프레임 기반의 프로토콜, 프레임은 명령, 헤더, 바디로 구성
//					자주 사용되는 명령 - Connect, Send, Subscribe, Disconnect 등
//					헤더와 바디는 빈 라인으로 구분하여 바디의 끝은 Null 문자로 설정
			
	}

}
