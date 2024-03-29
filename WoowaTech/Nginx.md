# Nginx

## Nginx이란 

## 잠깐!
이전에는 많은 사람들이 아파치 웹 서버를 사용하였다. 그러나 아파치를 사용함에 있어서 문제가 있었다. 바로 <b>C10K(Connection 10000 Problem)</b>이다. 서버에 동시에 연결된 커넥션이 많아졌을 때 더 이상 커넥션을 형성하지 못하는 문제가 발생한 것이다.   

여기서 동시에 연결된 커넥션 수와 초당 요청 처리 수는 다른 것이다. <b>초당 요청 처리 수</b>는 서버가 얼마나 빠르게 요청을 처리할 수 있는지 나타내는 지표이다. 그리고 <b>동시에 연결된 커넥션 수</b> 요청을 처리하기 위해 서버가 한 시점에 얼마나 많은 클라이언트와 커넥션을 형성하고 있는지 나타내는 지표이다.   

클라이언트가 서버에 연결되는 동시 커넥션 수가 10000단위가 넘어가는 순간 서버는 더 이상 커넥션을 형성하지 못하는 상황이 생긴다.   

아차피 서버 구조 상 커넥션이 형성될 때마다 프로세스를 할당하는데 동시에 처리하고 있는 커넥션이 많아지면 형성된 프로세스가 많아져 메모리가 부족해진다. 그리고 CPU 코어는 계속 프로세스를 바꿔가며 작업을 하는데 즉, 컨텍스트 스위칭이 많아져 CPU 부하가 증가한다. 즉, 동시 커넥션에 대해서는 아파치 서버는 부적합한 것이다.   

## Nginx을 사용하는 이유
아파치 서버의 단점을 보완하기 위한 소프트웨어가 나오는데 바로 <b>Nginx</b>이다. 아차피 웹 서버와 함께 사용하기 위한 목적으로 시작했었다. 웹 서버로서 Nginx의 역할은 ```클라이언트로부터 동적 파일 요청을 받았을 때만 뒤에 있는 서버와 커넥션을 형성```한다. 아파치 서버의 리소스를 커넥션 유지에 사용하지 않고 개발자가 필요할 때(원하는 로직 처리) 사용하도록 도와준다.   

## Nginx의 구조
Nginx의 구조는 <b>마스터 프로세스</b>가 존재한다. 그리고 이는 <b>워커 프로세스</b>를 생성한다. 워커 프로세스는 ```실제로 일을 하는 프로세스```이다. 워커 프로세스가 생성이 되면 각자 지정된 <b>Listen 소켓</b>을 배정받는다. 그리고 그 소켓에 새로운 클라이언트로부터 요청이 들어오면 커넥션을 형성하고 요청을 처리하고 정해진 <b>Keep alive</b> 시간만큼 유지된다.   

Nginx는 들어오는 요청이 없을 경우, 새로운 커넥션을 형성하거나 다른 커넥션으로부터 들어온 요청을 처리한다. 그리고 커넥션 형성과 삭제, 새로운 요청을 처리하는 것을 <b>이벤트</b>라고 한다. [자세한 구조와 처리 과정 내용 알기](https://youtu.be/6FAwAXXj5N0?t=484)   

## Nginx의 장점
아파치 웹 서버의 요청한 자원이 없으면 방치되던 프로세스보다 서버 자원을 효율적으로 쓴다는 장점이 있다. 그리고 시간이 오래 걸리는 작업은 따로 수행하도록 하는 <b>Thread Pool</b>을 만든다. 그리고 워커 프로세스는 CPU의 코어 수 만큼 생성하며 코어가 담당하는 프로세스를 바꾸는 횟수를 크게 줄일 수 있다. 그렇기 때문에 CPU가 굳이 부가적인 일을 하지 않아도 되며 CPU 부하가 증가하는 일을 보완할 수 있게 된다. 다시 말해서 CPU의 컨텍스트 스위치 사용을 줄이는 것이다.   

동시 커넥션 양이 최소 10배가 증가한다. (일반적으로 100 ~ 1000배 증가한다.) 그리고 동일한 커넥션 수일 때 속도가 2배 향상된다. 또한, Nginx의 설정을 동적으로 바꾸는 것이 가능하다. 즉, Nginx가 로드 밸런서 역할(요청을 여러 서버로 분산하는 작업)을 할 때, 동시 커넥션을 유지한 채 기존 요청을 계속 처리하면서 서버를 추가할 수 있게 된다.   

## Nginx의 단점
그러나 개발자가 기능 추가를 시도하였다가 돌아가고 있는 워커 프로세스를 종료하게 되는 상황이 발생할 수 있다. 그러며 커넥션과 관련된 요청을 처리할 수 없는 문제가 발생한다. 그러나 단점보다 장점이 명확하기 때문에 자주 사용한다.   

## Nginx의 상승
그러나 모바일 시대가 들어오면서 동시 커넥션을 훨씬 더 많이 생성하게 되면서 Nginx를 더 많이 사용하게 되었다. 그러나 오랫동안 업데이트를 지속적으로 해온 아파치 웹 서버는 Nginx보다 안정적이기 때문에 아직도 경쟁을 이어가고 있다. 그리고 모듈을 추가하여 그 기능을 확장하기 쉽다는 장점 또한 아파치 웹 서버를 사용하는 이유이기도 하다. Nginx는 아직 안정적이지 않다. 특히 윈도우 환경에서 말이다.   

## Nginx와 아파치 웹 서버
Nginx의 장점은 웹 서버 보완이다. 특히, C10K 동시 커넥션 문제를 보완한 것이 큰 장점이다.   

그리고 아파치 웹 서버는 호환성과 확장성이다. 이는 Nginx와 비교해서 큰 장점이 된다.   

그렇기 때문에 상황에 맞는 서버를 사용하는 것이 옳은 선택이다.   

## 현재의 Nginx
Nginx는 로드 밸런서 역할을 한다는 것을 알고 있다. 그리고 동시 커넥션을 여러 개 유지할 수 있고 그 자체가 가볍기 때문에 다양한 방법으로 <b>웹 서버 가속기</b> 역할을 하고 있다.   

* SSL 터미네이션 수행: 클라이언트와 HTTPS 통신을 하고 서버와는 HTTP 통신을 하는 것
    * 서버가 복호화 과정을 감당하지 않도록 한다.
    * 비즈니스 로직 처리에 리소스를 사용할 수 있도록 부하를 줄인다.
    * 대부분 같은 네트워크를 사용하기 때문에 HTTP 통신을 해도 보안적인 위험 요소가 적다.   
* HTTP 프로토콜을 사용하여 전달하는 콘텐츠를 캐싱이 가능한 것
    * SSL 터미네이션과 반대로 클라이언트 쪽에 가깝게 배치하여 서버로부터 받은 응답을 스스로 보관하고 클라이언트에 전달한다.
* 그 외 서버를 지원하는 방식
    * HSTS (HTTP Strict Transport Security)
    * CORS 처리
    * TCP/UDP 커넥션 부하 분산
    * HTTP/2 지원   

## 끝내기 전
[많은 개발자들로부터의 Nginx 템플릿](https://github.com/h5bp/server-configs-nginx)   

제대로 된 아키텍처를 구축한다면 위의 템플릿을 직접 튜닝하여 사용해야 한다. 그래도 템플릿을 사용하면 쉽고 빠르게 간단하게 Nginx를 사용할 수 있다.   

## 끝
영상의 모든 내용을 작성하지 않았기 때문에 영상을 시청하고 내용을 학습하자!   

[Nginx](https://www.youtube.com/watch?v=6FAwAXXj5N0)