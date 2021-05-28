# Web Server vs WAS

## Web Server
<b>웹 브라우저(클라이언트)</b>로부터 <b>HTTP 요청</b>을 받아서 HTML 문서와 같은 <b>정적 컨텐츠</b>를 제공하는 프로그램을 말한다.   

클라이언트가 특정 파일을 요청하면 웹 서버는 해당 파일을 찾아서 클라이언트에게 응답하게 된다.   

* 정적 컨텐츠
    * 요청 인자 값에 상관없이 달라지지 않는 컨텐츠 (HTML, CSS, IMAGE 등)
    * 어느 사용자 요청이든 항상 동일한 컨텐츠   

* 동적 컨텐츠
    * 요청 인자에 따라 바뀔 수 있는 컨텐츠 (유저의 이름이나 이메일 등)   

### Web Server의 기능
* 클라이언트로부터 HTTP 요청을 받을 수 있다.
* 정적 컨텐츠 요청 시: 정적 컨텐츠(HTML, IMAGE, CSS 등)을 제공할 수 있다.
* 동적 컨텐츠 요청 시: Web Application Server(WAS)로 전달하여 WAS가 처리한 결과를 클라이언트에 전달할 수 있다.   

## WAS (Web Application Server)
<b>DB 조회</b>나 다양한 로직 처리를 요구하는 <b>동적인 컨텐츠를 제공</b>하기 위해 만들어진 프로그램을 말한다.   

웹 서버와 다르게 특정 인자를 받으면 데이터 베이스에서 해당 인자와 관련된 데이터를 찾거나 데이터를 로직 처리를 해서 반환한다.   

## Web Server가 왜 필요할까?
### Web Server를 같이 사용함에 있어서 장점
* 책임 분할을 통한 서버 부하 방지
    * 정적 컨텐츠는 Web Server, 동적 컨텐츠는 WAS가 담당한다.
    * Root는 파일 시스템 디렉토리의 root
    * "/images/1.jpg"로 요청이 오면 "/www/data/images/1.jpg" 파일을 찾아 제공   

```
server {
    root /www/data;

    location / {
    }

    location /images/ {
    }
}
```

* 여러 대의 WAS 로드밸런싱
    * WAS가 처리해야 하는 요청을 여러 WAS가 나누어서 처리할 수 있도록 설정한다.

```
http {
    upstream backend {
        server backend1.example.com weight=5;
        server backend2.example.com;
        server 192.0.0.1 backup;
    }
}
```

```
server {
    location / {
        proxy_pass http://backend;
    }
}
```

* 여러 대의 WAS Health Check
* Health Check란?
    * 서버에 주기적으로 HTTP 요청을 보내어 서버의 상태를 확인 (Ex. 특정 URL 요청에 200 응답이 오는가?)
    * Interval: Health Check를 통해 서버 상태를 확잉ㄴ하는 요청을 날리는 주기 (Default: 5초)
    * Fails: 아래의 경우 3회 연속 실패하면 서버가 비정상이라고 인지 (Default: 1회)
    * Passes: 서버가 다시 복구되어 요청이 2번 연속 성공하면 서버가 정상으로 인지 (Default: 1회)

```
location / {
    proxy_pass http://backend;
    health check interval=10 fails=3 passes=2;
}
```

* 보안
    * 리버스 프록시를 통해 실제 서버를 외부에 노출하지 않을 수 있다.

### 상황으로 이해하기
* 서버 구성 (WAS 1대, DB Server 1대)
    * 서비스 사용자가 증가할 경우, WAS 1대로 역부족인 상황 발생
* 서버 구성 (WAS 2대, DB Server 1대)
    * 서버 부하 방지를 위해 일부 사용자에게 다른 IP를 가진 서버로 접속 유도
    * 그러나 사용자는 서버 부하에 관심이 없다 => 다른 서버 접속 관심 없음
* 서버 구성 (Web Server 1대, WAS 1대, DB Server 1대)
    * 책임 분할을 통한 부하 분산
    * 정적 컨텐츠는 Web Server가 담당
    * 동적 컨텐츠는 WAS가 담당
    * 그러나 사용자가 전보다 더 늘어났을 경우?
* 서버 구성 (Web Server 1대, WAS 2대, DB Server 1대)
    * 로드밸런싱을 통해 2대의 WAS를 동시에 운영
    * 동적 요청에 대해 WAS에서 분리해서 전달할 수 있도록 설정
    * 만약 WAS 1대에 문제가 생겼을 경우? 부분적 서비스 오류 발생
    * Health Check를 통해 서버에 이상이 생기면, WAS에 대한 연결을 자동으로 차단하도록 설정 (Health Check의 Fails 설정만큼 정상 응답이 오지 않으면 비정상 서버로 간주)
    * Health Check passes 설정만큼 정상 응답이 온다면, 해당 서버로 요청을 다시 전달

## 결론
WAS 만으로도 서비스는 가능하다는 것이다. 그러나 확장성, 안정성을 고려한다면 Web Server를 앞에 두는 것이 좋은 결과를 가져올 수 있을 것이다.

[출처](https://www.youtube.com/watch?v=mcnJcjbfjrs)