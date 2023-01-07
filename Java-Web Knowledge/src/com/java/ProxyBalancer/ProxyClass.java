package com.java.ProxyBalancer;

public class ProxyClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Proxy
//		 => 대리. 남을 대신하여 일을 처리한다
//		
//		Proxy Server
//		 => 대신 처리하는 서버(클라이언트가 자신을 통해 다른 네트워크 서비스에 간접적으로 접속할 수 있게 해주는 시스템 혹은 응용 프로그램)
//		 => 클라이언트와 서버간의 중계 서버. 통신을 대리 수행하는 서버. (캐시/보안/트래픽 분산 등 여러가지 장점을 가짐)
//		 
//		* Proxy 종류
//		 1) Forward Proxy(일반적인 Proxy)
//			=> Proxy (IP 추적 방지) 및 Proxy 서버 설정(인터넷 속도 향상)
//			=> 클라이언트와 인터넷 사이에 위치
//			=> 특징 1. 캐싱(클라이언트가 요청한 내용) -> 클라이언트가 날씨를 물어보면 서버에서 비 온다를 전달. 이 전달을 Forward Proxy에서 저장하고 다음 클라이언트가 날씨를 물어보면 비 온다 전달
//											-> 전송 시간 절약, 불필요한 외부 전송 X, 외부 요청 감소(네트워크 병목 현상 방지)
//			=> 특징 2. 익명성(클라이언트가 보낸 요청 감춤) -> 클라이언트가 요청할 때 Forward Proxy가 받아 서버에 누가 요청했는지 모르게 한 번에 전달
//												-> Server가 받은 요청 IP = Proxy IP
//												
//		 2) Reverse Proxy
//			=> Forward Proxy와 다르게 인터넷과 서버들 사이에 위치
//			=> 특징 1. 캐싱(Forward Proxy와 같음)
//			=> 특징 2. 보안(서버 정보를 클라이언트로부터 숨김) -> 클라이언트가 서버로 직접 요청이 아닌 클라이언트의 입장에서 서버인 Reverse Proxy에 요청. Reverse Proxy가 서버에 요청
//													-> 실제 서버의 IP가 노출되지 않음
//			
//		 3) Load Balancing
//			=> 하는 경우도 있고 하지 않는 경우도 있다
//			=> 부하분산. 해야할 작업을 나누어 서버에 부하를 분산 -> 서버에 요청을 균등하게 나눠줌
//			=> 사용자가 늘면서 서버 부하를 방지하기 위해 사용
//			=> 여러 대의 서버가 분산 처리할 수 있도록 요청을 나누어주는 서비스
//			=> OSI 7 Layer 기준 L4(Transport Layer) => ex)  특정 사이트 접속 시 서버 A, 서버 B로 로드밸런싱
//			=> OSI 7 Layer 기준 L7(Application Layer) => HTTPS/HTTP/FTP. ex) 특정 사이트 접속 시 /category, /search를 담당 서버들로 로드밸런싱
	}											
}
