# TCP/IP

## 들어가기 전
* 인터넷: 전 세계에 걸쳐 파일 전송 등의 데이터 통신 서비스를 받을 수 있는 컴퓨터 네트워크 시스템   

## TCP/IP
이는 ```인터넷에서 컴퓨터들이 서로 정보를 주고 받는데 쓰이는 프로토콜의 집합```을 말한다. TCP/IP의 계층은 다음과 같다.   

* Application Layer: 특정 서비스를 제공하기 위해 애플리케이션끼리 정보를 주고 받는다. (FTP, HTTP, SSH, Telnet, DNS, SMTP 사용)
* Transport Layer: 송신된 데이터를 수신 측 애플리케이션에 확실하게 전달한다. (TCP, UDP, RTP, RTCP 사용)
* Internet Layer: 수신 측까지 데이터를 전달하기 위해 사용한다. (IP, ARP, ICMP, RARP, OSPF 사용)
* Network Access Layer: 네트워크에 직접 연결된 기기 간 전송을 할 수 있도록 한다. (Ethernet, PPP, Token Ring 사용)   

## TCP/IP 흐름
웹 프라우저에 ```www.google.com```을 입력하면 무슨 일이 일어날까?   

* (HTTP)www.google.com(:80) HTTP로 작성된 해당 요청 처리를 한다. 인터넷을 통해 요청을 구글 서버로 전달하기 위해서 패킷을 사용해야 한다. (패킷에는 각 계층에 필요한 정보가 담겨져 있다.)
    * Application Layer에서 HTTP를 사용하며 Http Request의 정보가 담긴다.
    * Transport Layer에서 TCP를 사용하며 SP(시작 포트번호), DP(목적지 포트번호)가 담긴다.
    * Internet Layer에서 IP를 사용하며 IP Header에는 SA(IP 시작 주소), DA(IP 목적지 주소)가 담긴다.
        * 컴퓨터에는 도메인에 대한 IP 주소가 등록되어 있으며 DNS는 HTTP와 같은 애플리케이션 계층 프로토콜이다. 그리고 HTTP Request와 비슷하게 도메인이 담긴 쿼리를 도메인 서버로 보내고 서버에서 IP 주소를 응답한다. 그리고 Transport Layer에서 UDP라는 프로토콜을 사용하며 이는 비연결지향형 프로토콜이다. (TCP는 연결지향형 프로토콜)
    * Network Access Layer에서 Ethernet을 사용하며 도메인으로 요청하여 응답을 받는다.
        * 게이트웨이의 IP주소로 MAC 주소를 알기 위해서 ARP 프로토콜을 사용하여 MAC 주소를 알아낸다. (ARP: IP 주소를 MAC 주소로 바꾸어주는 주소 해석 프로토콜)   
   
* 3-Way-Handshaking: 데이터를 전송하기 전 송신 측과 수신 측이 서로 연결되는 작업이며 TCP Header에 표시한 플래그들이 사용된다. (이러한 플래그를 컨트롤 비트라고 부른다.)
    * 클라이언트가 서버에 접속을 요청하는 SYN 패킷과 ACK 패킷이 사용된다. (중간에 클라이언트에 요청을 수락한다는 ACK과 SYN 플래그가 설정된 패킷을 보낸다.)   
   
* NAT(Network Address Translation): Private IP인 개인 IP를 공유기를 통해서 Public IP로 변환하는 과정   
   
* 라우터: 네트워크와 네트워크를 연결해주는 역할 (위의 예시로 구글 서버에 도착하기 위해서는 여러 라우터를 거쳐야 한다.)이며 라우터가 목적지 경로를 찾아가는 과정을 <b>라우팅</b>이라고 한다.   
   
* 구글 서버에 도착하여 MAC 주소를 알기 위한 과정에서 ARP 프로토콜을 사용한다. 이 과정에서 ARP은 라우터가 연결된 네트워크에 <b>브로드캐스팅</b>이 된다.   
   
* 4-Way-Handshaking: HTTP의 요청과 응답과정이 끝나면 연결을 종료하는 과정으로 TCP 컨트롤 비트가 사용되며 ACK와 FIN 플래그가 사용된다. 클라이언트는 서버로부터 종료 요청(FIN)을 받더라도 일정 시간동안 소켓을 닫지 않고 도착하지 않은 잉여 패킷을 기다리며 이러한 상태를 <b>TIME_WAT</b>이라고 한다.   
   
## 마무리
TCP는 연결지향형 프로토콜이며 신뢰할 수 있는 프로토콜이다. 수 많은 패킷을 전달하는 과정에서 하나의 패킷을 쪼개어 보낸다. 쪼개어서 보낼 때, 순서대로 안전하게 보낼 수 있도록 하는 것이 TCP 프로토콜의 역할이다. TCP는 <b>흐름제어</b>, <b>오류제어</b>, <b>혼잡제어</b>를 통해서 신뢰성있는 데이터 전송을 보장할 수 있게 한다.   

[TCP/IP](https://www.youtube.com/watch?v=BEK354TRgZ8)