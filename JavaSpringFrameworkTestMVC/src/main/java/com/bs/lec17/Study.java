package com.bs.lec17;

public class Study {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		세션과 쿠키
//		
//		Connectionless Protocol
//			웹 서비스는 HTTP 프로토콜 기반. HTTP 프로토콜은 클라이언트와 서버의 관계를 유지하지 않는 특성이 있음
//			클라이언트 요청 -> 서버 연결 -> 서버에서 클러이언트로 응답 -> 서버 연결 해제
//			
//			서버의 부하를 줄일 수 있는 장점. 클라이언트 요청 시마다 서버와 매번 새로운 연결이 생성되어 일반적인 로그인 상태 유지, 장바구니 등의 기능 구현이 어려운 단점
//			=> 이러한 단점을 보완하기 위해 세션과 쿠키 사용 -> 세션과 쿠키는 클라이언트와 서버의 연결을 유지시키는 역할
//			=> 세션은 서버에서 관리. 쿠키는 클라이언트에서 관리
//			
//		1. HttpServletRequest를 이용한 세션 사용
//			컨트롤러의 메소드에서 파라미터로 HttpServletRequest를 받으면 된다.
		
//		2. HttpSession을 이용한 세션 사용
//			getSession()을 할 필요가 없다. => 바로 setAttribute()
		
//		쿠키(로컬 컴퓨터에 저장으로 해킹 위험)보다 세션을 자주 사용
//		중요하지 않는, 서버 부화를 줄이기 위해 가끔 쿠키 사용
		
//		리다이렉트(Redirect)
//			현재 페이지에서 특정 페이지로 전환하는 기능
		
//		인터셉터(Interceptor)
//			리다이렉트를 사용해야 하는 경우가 많은 경우 HandlerInterceptor를 이용해 해결
	}

}
