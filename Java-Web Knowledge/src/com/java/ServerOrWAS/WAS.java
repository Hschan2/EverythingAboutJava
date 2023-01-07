package com.java.ServerOrWAS;

public class WAS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Web Application Server (WAS)
//		웹 애플리케이션: 웹 브라우저에서 이용할 수 있는 응용 소프트웨어
//		
//		"웹 애플리케이션과 서버 환경을 만들어 동작시키는 기능을 제공"하는 소프트웨어 프레임워크
//		
//		"Web Server + Web Container"
//		
//		HTML 같은 정적인 페이지에서 처리할 수 없는 비지니스 로직이나 DB 조회 같은 동적인 컨텐츠를 제공
//		
//		웹 서버 : 정적인 데이터를 넘겨준다
//		웹 컨테이너 : 동적인 데이터를 넘겨준다
//		
//		클라이언트 -> 요청 -> Web Server는 요청을 받아 Container로 전송 -> Container에서 JSP, Servlet 구동 환경 제공
//			-> Web Server에서 결과값을 받아 Client로 전송 -> 클라이언트 응답
//			
//			
//		Web Server vs WAS
//		1. 정적 데이터      vs 정적 + 동적 데이터
//		
//		Web Server와 WAS를 동시에 구동하는 이유
//		1. 기능을 분리하여 "서버 부하 방지"
//			- WAS => DB 조회 등 페이지를 만들기 위한 다양한 로직 처리. 단순 정적 데이터를 WAS에서 가져온다면 다른 작업에 사용하는 리소스들로 인해 지연 발생 가능이 있다
//					BUT Tomcat 5.5 이상부터 성능 차이가 크지 않음
//		2, 물리적으로 분리해 "보안 강화"
//			- SSL에 대한 암복호화 처리에 Web Server를 사용
//			- 공격에 대해 Web Server를 앞단에 두어 중요한 정보가 담긴 DB나 로직까지 (WAS까지) 전파되지 못하게 함
//				(Client의 요청 -> Web Server -> WAS)
//				(외부에서 공격을 해도 Client의 요청 -> Web Server 단계까지 일뿐 실제로 데이터를 처리하는 WAS에 접근하지 않아 보안 강화)
//		3. "여러 대의 WAS"를 연결 가능
//			- Load Balancing
//			- Fail Over(장애 극복), Fail Back
//				(Fail Over : 한 대의 WAS가 중단되어도 다른 WAS로 동작된다. Fail Back : 작동이 중지된 WAS를 재작동 시킨다)
//			- 대용량 웹 애플리케이션의 경우 (여러 개의 서버 사용) Web Server와 WAS를 분리하여 무중단 운영을 위한 장애 극복을 쉽게 대응할 수 있음
//		4. "다른 종류의 WAS"로 서비스 가능
//			- 하나의 서버에서 PHP와 Java를 함께 사용하는 경우
	}

}
