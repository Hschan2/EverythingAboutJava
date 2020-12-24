package com.java.HTTP;

public class HTTP_Version {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		* TCP - 연결형 서비스, 가상 회선 방식, 전송 순서 보장, 신뢰성 높음, 전송 속도 느림 => 데이터가 제대로 전송되었는지 확인, 0.9 ~ 2.0 VERSION
//		* UDP - 비연결형 서비스, 데이터그램 방식, 전송 순서 보장하지 않음, 신뢰성 낮음, 전송 속도 빠름 => 데이터가 제대로 전송되었는지 잘 모르겠고 일단 보낸다
//		* HTTP/0.9 - GET 방식 하나, 원라인 프로토콜
//		* HTTP/1.0 - HEADER 추가, HTML 뿐 만 아니라 다른 타입 파일 전송도 가능, 커넥션 하나 당 요청 하나 응답 하나 처리 가능 => 성능 저하, 서버 부하 비용 증가
//
//		HTTP/1.1
//			Persistent Connection 도입
//			- 지정 시간 동안 커넥션 닫지 않음, 여러 요청을 받을 수 있음
//			Pipelining 도입
//			- 커넥션에서 응답을 기다리지 않고 순차적으로 여러 요청을 연속적으로 보내 그 순서에 맞춰 응답을 받는 방식, 지연 시간 감소
//			- Head Of Line Blocking 문제 => 첫 번째 요청에서 시간이 오래 걸리면 그 다음 요청들도 기다려야 한다, 같은 요청을 중복해서 보낸다
//			
//			
//		HTTP/2
//			기존 HTTP/1.X 버전의 성능 향상에 초점
//			표준 대체가 아닌 확장 개념
//			일반 텍스트가 아닌 바이너리 프레이밍 계층 사용 -> 파싱과 전송 속도 증가, 오류 발생 가능성 감소
//			서버 연결된 커넥션 안에 데이터 양방향 흐름(스트림)이 존재, 들어온 조각(쪼개진 것, 순서라는 것이 사라짐)을 합쳐 요청과 응답을 만듬 -> Multiplexing, Head Of Line Blocking를 해결
//			Stream Prioritization, 리소스간 우선 순위를 설정 가능
//			Server Push, 요청하지 않은 리소스를 서버에서 자동으로 Push (ex. html를 요청하면 js, css가 같이)
//			Header Compression, 헤더 크기를 줄여 페이지 로드 시간 감소, Static Dynamic Table -> 중복 제거
			
	}

}
