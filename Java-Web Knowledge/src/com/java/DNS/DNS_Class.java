package com.java.DNS;

public class DNS_Class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		DNS (Domain Name System)
//		도메인 네임을 아이피 주소로 해석하는 것
//		
//		탄생 배경
//		1. IP - 아이피를 기억하기 쉽지 않기 때문에 이를 해결하기 위해
//		2. Hosts - host 파일에 아이피와 도메인 네임을 적으면 해당 도메인 네임을 입력 시 해당 아이피로 접속 가능
//		3. SRI (SRI-NIC) - 전 세계의 아이피와 해당 도메인 네임을 수집하고 유저는 이것들이 포함한 호스트 파일을 다운로드하고 쉽게 접근 가능
//		4. DNS - 전보다 많아진 도메인 갯수로 인해 트래픽이 증가하였고 이를 해결하기 위해
//		
//		특징
//		1. 간단한 DNS 통신 구조
//		유저가 호스트 파일에 도메인 접속 요청 -> DNS이 해석해서 아이피를 유저에 보내고 -> 유저는 해당 아이피를 가진 도메인 네임에 접속
//		
//		어떻게 이용하는가?
//		DNS를 가진 수 많은 통신사가 있고 이를 관리
//		
//		2. DNS 통신 구성 요소
//		유저의 요청 -> Public DNS Resolver에서 다시 요청 -> Root Name Server 응답 -> Public DNS Resolver에서 다시 요청 -> Top Level Domain Name Server 응답 -> Public DNS Resolver에서 다시 요청 -> Authoritative Name Server 응답
//		
//		DNS에서 질의와 응답
//		DNS 질의 (예. dev.taggle.kr A IN)
//			- dev.taggle.kr(Query Name String) = 요청하고자 하는 도메인 네임, A(Type) = 질의하고 싶은 대상의 데이터 타입 , IN(Class) = DNS 통신중인 네트워크
//		DNS 응답
//			- 질의한 도메인 네임의 Address (IP 주소)를 응답
//		
//		DNS 리소스 레코드
//		DNS 서버가 가지고 있는 리소스 모음
//		Name	Type	Class	Resource Data
//		.kr 	A 		IN 		1.2.3.4
//		.com 	NS 		IN 		some.dns.com
//		
//		DNS 서버의 종류
//		권한 - DNS 서버 자체가 가지고 있는 데이터를 유저에게 응답해줄 수 있는가 없는가 기준
//		
//		1. 권한이 없는 네임 서버 (Non-Authoritative Name Server)
//		Public DNS - 클라이언트와 직접적으로 연결이 된 서버이며 통신사 들이 자체적으로 제공하는 서버, 자체적으로 가지고 있는 레코드가 존재하지 않음
//					요청이 오면 응답을 해야 하나 스스로 하지 못해서 다른 레코드를 가지고 있는 객체에게 요청
//					
//			권한이 없는 네임 서버 - 클라이언트와 가장 최전방에 있어서 요청을 받고 응답을 전달할 수 있는 서버가 있고 권한이 있는 다른 서버에 요청을 하고 최종적으로 아이피를 찾아 다시 응답 (통신사)
//							쉽게 아이피를 찾아나설 수 있는 것이 루트 네임 서버 -> 탑 레벨 네임 서버 -> 기타 네임 서버로 아이피 주소 찾기
//				
//					
//			* 도메인 네임
//				서브 도메인 - 예. dev.taggle.kr 있을 때 taggle은 .kr에 하위에 위치하여 있으니 서브 도메인이라고도 가능
//				
//		2. 권한이 있는 네임 서버 (Authoritative Name Server)
//		계층형 Name Server
//			Root Name Server
//			TLD Name Server
//			Other Authoritative Name Server
//		
//			도메인을 기준으로 독자적인 도메인 서버가 이루어져 있으며 자신의 리소스 레코드를 가지고 있음
//			하나의 도메인 만으로 운영하기에는 많은 요청을 받기 힘들 수 있기 때문에 나누어서 운영
//			권한이 없는 서버에서 요청이 들어와서 다시 권한이 있는 서버에 요청을 전달할 때 루트 네임 서버에서 받고 밑에 층의 네임 서버를 살피면서 요청의 아이피를 가진 도메인을 응답
		
	}

}
