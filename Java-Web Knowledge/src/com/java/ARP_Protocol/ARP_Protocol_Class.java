package com.java.ARP_Protocol;

public class ARP_Protocol_Class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		ARP (Address Resolution Protocol)
//		네트워크 상 IP 주소를 물리적 네트워크 주소로 대응시키기 위해 사용하는 프로토콜
//		
//		물리적 네트워크 주소
//		이더넷의 48bit 네트워크 카드(NIC)의 주소
//		다른 말로 MAC 주소, 데이터 링크 계층(두 번째 계층)에서 사용하는 네트워크 인터페이스 카드(NIC). 즉, 하드웨어에 할당된 고유 식별 주소
//		
//		IP 주소 (Internet Protocal Address)
//		인터넷에 연결되어 있는 모든 호스트 혹은 라우터 장비 인터페이스에 할당된 논리적인 주소
//		
//		Public IP									Private IP
//		전 세계에서 유일하게 공인된 주소						하나의 네트워크 안에서 유일하고 인터넷 상에서 확인할 수 없음
//		한국인터넷진흥원(KISA)에서 국내에서 사용할 주소를 관리	네트워크 안에서 사용되는 주소
//													다른 네트워크 IP와 중복될 수 있음
//		
//		Private IP는 외부 통신을 할 때 공유기와 같은 기기에서 Public IP로 변경
//		IP주소를 이용해 알 수 있는 것은 목적지 컴퓨터 주소가 아니라 Internet Protocol로서 컴퓨터가 위치한 인터넷 네트워크를 찾을 수 있음
//		
//		(예. 한 사용자가 메시지 보냄 주소: 루터회관, 수신자: 일번 => IP주소: 루터회관, MAC주소: 일번)
//		
//		정리
//		목적지 컴퓨터가 위치한 네트워크를 찾기 위해 IP주소를 사용
//		해당 네트워크에서 실제 목적지 컴퓨터를 찾기위해 MAC 주소 사용
//		계층마다 사용되는 프토콜이 다르며, IP는 네트워크 계층이라는 3계층에서 사용되고 MAC 주소는 데이터 링크 계층이라는 2계층에서 사용되기 때문에 ARP 프로토콜을 사용해서 MAC 주소를 알아와야 한다.
//		
//		ARP Process
//		Case 1
//			같은 네트워크 안에서 Host 끼리 통신
//		Case 2
//			같은 네트워크 안에서 Router와 Host의 통신
//		Case 3
//			다른 네트워크 안에서 Router와 Router의 통신
//		Case 4
//			다른 네트워크 안에서 Host와 Router의 통신
//			
//		같은 네트워크 안에서 Host 끼리 통신
//		ARP Header
//		Sender Hardware Adderss(6bytes)
//					Sender IP Address(4bytes)
//					Target Hardware Address(6bytes)
//		Target IP Adderss(4bytes)
//		과정 - System A에서 브로드 캐스트로 메시지 전송(받는 사람의 주소를 모르기 때문에) -> 패킷을 받는 호스트는 데이터 안에 IP 주소를 확인하고 다른 IP 주소를 가진 호스트들은 파기
//		-> System B는 같은 IP를 가진 자신한테 온 패킷라는 것을 알 수 있음 -> 영어로 채워진 Target MAC 주소에 자신의 주소를 적음
//		-> 요청은 브로드 캐스트로 하였지만 응답을 했을 때 1:1 통신이 가능한 유니캐스트로 응답
//		-> System A는 System B의 MAC 주소를 알게 되고 서로 통신이 가능
			
	}

}
