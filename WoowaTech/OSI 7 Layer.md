# OSI 7 Layer
네트워크 환경이 달라서 통신이 어려웠던 오랜 과거는 OSI 7 계층이 나오고 나서 많은 변화를 가져왔다.   

## OSI 7 Layer 종류
* Application Layer: FTP, HTTP, SMTP, Telnet
* Presentation Layer: Data (변환 -> 압축 -> 암호화)
* Session Layer: Session (세션 전송, 세션 복구 등)
* Transport Layer: 서로 다른 두 네트워크 간 전송 담당 (Segmentation, 흐름제어, 오류제어)
* Network Layer: IP, Router(최적의 경로) 등
* Data Link Layer: 동일한 네트워크 내 전송 담당, 흐름제어, 오류제어
* Physical Layer: 비트 단위의 전기 신호를 변환 및 전송   

현재 우리가 사용하고 있는 네트워크 체계는 TCP/IP이다. OSI 7 Layer는 네트워크를 묘사하기 위한 모델이다.   

## TCP/IP Model
* Application Layer
* Transport Layer
* Network Layer
* Data Link Layer
* Physical Layer   

다른 컴퓨터끼리 통신을 할 때, <b>Application Layer</b>에서 데이터를 캡슐화하여 보내고 다른 컴퓨터에서 디캡슐화해서 받는다.   

<b>Transport Layer</b>에서는 데이터를 전송할 때, TCP와 UDP 중 하나를 선택해야 하는데 TCP는 신뢰성을 가지고 있고, UDP는 비신뢰성이며 속도가 빠르고 연속적인 특징을 가지고 있다.   

<b>Network Layer</b>에서는 출발지와 도착지에 대한 IP 정보를 헤더에 담는다.   

<b>Data Link Layer</b>에서는 맥 주소와 가장 가까운 라이터의 맥 주소를 넣는다. 도착지의 맥 주소를 담지 않는 이유는 처음에는 도착지의 맥 주소를 알지 못하기 때문이다. (DHCP, ARP 이용) 그리고 오류 제어용으로 트레일러 정보 또한 담아 보낸다. 이렇게 Application Layer 단계부터 캡슐화한 것을 "<b>Frame(프레임)</b>"이라고 부른다.   

<b>Physical Layer</b>에서는 캡슐화한 데이터를 전기신호로 바꿔 전송한다.   

[OSI 7 Layer](https://www.youtube.com/watch?v=Fl_PSiIwtEo)