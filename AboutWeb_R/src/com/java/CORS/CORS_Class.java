package com.java.CORS;

public class CORS_Class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		CORS (Cross-Origin Resource Sharing)
//		교차 출처 자원 공유
//		
//		출처(Origin)이란?
//		예. https://github.com:1/study/jwp-was/issues?q=test&sort=oldest#foo
//		https:// = Protocol
//		github.com = Host
//		:1 = Port
//		/study/jwp-was/issues = Path
//		q=test&sort=oldest = Query String
//		#foo = Fragment
//		
//		Protocol, Host, Port가 출처를 판단
//		
//		SOP (Same-Origin Policy)
//		동일 출처 정책
//		CORS 정책을 지킨 요청
//		실행 가능한 스크립트
//		렌더될 이미지
//		스타일 시트
//		그 외
//		
//		같은 출처? 다른 출처?
//		출처 - https://taggle.kr
//		https://taggle.kr/bookmark => 프로토콜과 호스트가 같기 때문에 같은 출처
//		http://taggle.kr => 프로토콜이 다르기 때문에 다른 출처
//		https://taggle.kr:433/bookmark?page=1 => 같은 출처
//		https://api.taggle.kr => 다른 호스트를 갖고 있기 때문에 다른 출처
//		https://taggle.kr:8080 => 인터넷 정책 상 같은 출처이긴 하지만 인터넷 익스플로어에서는 아님
//		
//		CORS와 SOP가 나온 이유
//		CSRF, XSS처럼 보안 취약점 발생 때문
//		
//		CORS 시나리오
//		Origin == Access-Control-Allow-Origin => 둘이 같으면 같은 출처라고 인식
//		1. Preflight Request => 예비 요청, ULR 요청 시 Options라는 메소드를 통해 예비 요청 - Header에 Origin 출처를 동시 전송, 브라우저에서 판단
//			1) OPTIONS 메소드로 예비 요청을 보내고 본 요청을 보냄
//			2) Origin에 대한 정보 뿐 만 아니라 자신이 예비 요청 이 후 보낼 본 요청에 대한 다른 정보들도 같이 포함 (Access-Control-Request-Headers, Access-Control-Request-Method)
//			3) 요청에 Origin과 응답이 Access-Control-Allow-Origin을 브라우저가 비교하여 출처를 판단하고 다르면 에러, 접근 간으한 출처라면 본 요청을 보내 요청 처리
//			4) 서버 사이드 영역이 아닌 브라우저 영역으로 서버는 200대의 성공 코드를 반환
//		
//		2. Simple Request => 예비 요청이 없고 본 요청에서 예비 요청이 했던 행동을 함
//			1) 요청의 메소드는 GET, POST, HEAD 중 하나여야 함
//			2) Accept, Accept-Language, Content-Language, Content-Type, DPR, Downlink, Save-Data, Viewport-Width, Width를 제외한 헤더를 사용하면 안됨
//			3) Content-Type을 사용하는 경우 application/x-www-form-urlencoded,  multipart/form-data, text/plain만 허용
//		
//		3. Credentialed Request (보안 강화)
//		보통 피치, 비공개 API는 쿠키를 담고 보내지 않는다. 쿠키를 담기 위해 옵션 값 넣어야 함
//		옵션 값 					설명
//		same-origin(기본값)		같은 출처 간 요청에만 인증 정보 담음
//		include					모든 요청에 인증 정보를 담음
//		Omit					모든 요청에 인증 정보를 담지 않음
//		서버에서 인증된 사용자인지 확인 (한 번 더 검증 거치기)
//		Access-Control-Allow-Origind에는 *를 사용할 수 없고 명시적인 URL이어야 함
//		응답 헤더에는 반드 시 Access-Control-Allow-Credentials: true가 존재해야 함
//		
//		결론
//		원하는 출처 자원만 받는 정책
//		Server Side - 프론트 개발자를 위해 응답 헤더에 옳바른 Access-Controll-Allow-Origin이 내려올 수 있도록 세팅해야 함
//		Client Side - Webpack Dev Server로 리버스 프록싱하여 우회 가능하게 하지만 Dev Server인 것 처럼 로컬 환경에서만 가능 => 가장 좋은 방법은 서버 개발자에 도움 요청
	}
}
