package com.java.web;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// WEB 요쳥과 응답
		
		/*
		
		● TCP (예. 전화기) vs UDP (예. 라디오)
		일대일 연결 / 일대다 연결
		손실되면 재전송 요청하므로 신뢰도 높음 / 정보 확인 없이 일방적으로 전송
		데이터 순서와 무결성 보장 / 데이터 순서와 무결성을 보장하지 않음
		속도가 느림 / 속도가 빠름
		높은 신뢰도 서비스에 적합 / IPTV, 스트리밍에 적합
		
		● TCP HandShake
		3-way(연겷할 때) / 4-way(연결을 끊을 때)
	
		● HTTP
		사용자가  브라우저에서 URL을 입력 -> 브라우저가 요청 메세지 전송 -> 서버가 URL을 프로그램 혹은 정적 파일로 연결 -> 서버가 응답 메세지 반환 -> 브라우저가 응답 형식에 맞춰 사용자 화면에 표시
		
		단반향성
		클라이언트가 서버로 요청을 보내고 이에 대한 응답을 받는 단방향적 통신(서버가 클라이언트에 먼저 전송 불가)
		
		비연결성
		연결이 계속 유지되지 않고 요청에 대한 응답이 끝나면 연결을 끊음 (소켓 통신과 반대)
		
		무상태성
		클라이언트가 서버와 연결된 상태가 아니기 때문에 기본적으로 상태를 가지지 않음. 이를 보완하기 위해 쿠키, 세션, 토큰등을 사용
		
		● HTTP 응답 코드
		2XX -> 요청 성공, 3XX -> 요청을 완료하기 위해 다른 주소로 이동이 필요, 4XX -> 클라이언트 오류이며 올바르지 않는 요청, 5XX -> 서버 오류이며 올바른 요청에 대해 서버의 문제로 응답 불가
		
		● API
		해당 프로글매의 구현을 알지 않아도 프로그램이 제공하는 기능을 쓸 수 있도록 한 인터페이스
		프론트엔드와 백엔드를 분리하기 쉽게 만들어준다.
		
		● API가 없다면?
		1. 프론트엔드와 백엔드를 분리하기 어려움
		2. 프론트엔드만 수정하면 되는 것을 백엔드도 수정해야
		3. 웹 이외의 앱 등 다른 프론트엔드를 추가할 경우 백엔드도 통합하기 어려움
		4. 3rd Party에 애플맄에ㅣ션 기능을 제공해야 할 경우 소스 코드와 DB 접근 권한을 노출해야 함
		
		● REST
		HTTP의 디자인 패턴이라고 생각하면 된다.
		
		● REST의 6가지 제약 조건
		1. 클라이언트와 서버 기능이 완벽하게 분리되어야 한다.
		2. 무상태 -> 요청을 통해 클라이언트의 '상태'를 서버에 저장해서는 안 된다. 대신 캐시나 JWT(Json Web Token) 등을 이용
		3. 캐시 처리 가능 -> 클라이언트는 응답을 캐싱할 수 있어야 한다. 클라이언트가 같은 자료를 중복 요청하는 것을 막아 서버의 부담을 줄일 수 있다.
		4. 서버를 여러개로 계층화 하여 클라이언트가 어느 서버에 연결되었는지 알 필요 없이 똑같은 기능을 효율적으로 제공
		5. Code on demand -> 서버는 클라이언트가 직접 실행시킬 수 있는 로직을 전송 가능 (Flash, 브라우저 내 게임 등)
		6. 인터페이스 일관성 -> 각각의 요청은 URL 등으로 식별된다. 서버가 가지고 있는 리소스는 클라이언트의 응답과는 구별
						-> 클라이언트는 서버에서 전송받아 가지고 있는 정보만으로 리소스를 변경하거나 삭제 가능
						-> 각각의 요청은 처리 방법에 대한 충분한 정보를 담음. 'GET/board/1'의 요청만으로 게시판의 1번 게시글을 읽는다
						-> HATEOAS, 해당 리소스에 대해 할 수 있는 모든 동작에 대한 URL를 제공. 예) 로그인을 통해 할 수 있는 모든 기능 제공(글쓰기, 회원정보 수정 등의 동작)
		
		● HTTP 캐시
		응답의 헤더를 통해 콘텐츠의 길이, 유효 시간, Etag를 전송
		캐시의 유효 시간이 지나면 서버로부터 다시 읽고 서버의 응답과 캐시가 가지고 있떤 컨텐츠의 Etag가 같다면 업데이트를 안한다.
		
		● REST API
		'/'로 계층 관계를 나냄 (예. h2f.kr/blog/article/1)
		URL를 이루는 리소스명은 동사보다 명사로! (REST는 상태나 행위를 가지거나 표현하지 않음)
		_, 대문자, 파일 확장자는 RUL에 포함시키지 않아야 한다. (최대한 간결하고 혼동을 피하기 위해)
		리소스에 대한 행위는 HTTP 메소드를 이용
		
		● HTTP 메소드
		CREATE -> 응답에 body가 있음. 서버에서 리소스 저장을 요청
		READ -> 요청에 body가 없다(주소(리소스)에 위치가 드러나기 때문). 응답에 body가 있음. 서버로부터 리소스를 요청
		UPDATE -> 응답에 body가 있음. 서버에게 리소스 업데이트 요청
		DELETE -> 요청에 body가 없다(주소(리소스)에 위치가 드러나기 때문). 응답에 body가 있음. 서버에게 리소스 삭제 요청
		
		*/
	}
}
