# HTTPS

## HTTP vs HTTPS
### HTTP
<b>HTTP (Hypertext Transfer Protocol)</b>은 서로 다른 시스템들 사이에서 통신을 주고 받게 하는 가장 기본적인 프로토콜이다. 서버에서 브라우저로 데이터를 전송하는 용도로 가장 많이 사용한다.   

그러나 HTTP는 서버에서 브라우저로 전송되는 정보가 암호화되지 않는 문제점을 가지고 있다.   

### HTTPS
<b>HTTPS (Hypertext Transfer Protocol Secure)</b>은 HTTP의 보안 문제를 보완한 것으로 SSL (보안 소켓 계층)을 사용한다.   

SSL은 서버와 브라우저 사이에 안전하게 암호화된 연결을 만들 수 있게 도와주며 서버와 브라우저가 민감한 정보를 주고 받을 때 해당 정보가 도난당하는 것을 막아준다.   

HTTPS는 HTTP를 사용하여 운반하는 내용인 HTTP Message Body를 암호화한다. (HTTP Header는 암호화하지 않는다.)   

## HTTPS를 사용해야 하는 이유
### 보안성
HTTP를 사용하여 데이터를 전송한다면 네트워크로 전달되는 원본 그 자체가 전송된다. 이는 해커가 중간에 해킹할 수 있는 가능성이 존재하기 때문에 위험하다.   

그러나 HTTPS를 사용한다면 데이터를 전송할 때 이를 암호화하여 전송한다. 그래서 중간에 해당 데이터를 해커가 알기 어렵다.

### 검색 엔진 최적화 (SEO)
구글은 HTTPS를 사용하는 웹 사이트에 가산점을 준다. 즉, 본인의 웹 사이트가 검색 엔진에 더욱 많은 노출되기를 원한다면 HTTPS는 필수이다.   

그리고 가속화된 모바일 페이지, AMP를 제작할 때는 HTTPS를 꼭 사용해야 한다. 이는 구글이 제작한 것으로 모바일 기기에서 컨텐츠를 훨씬 빠르게 로딩할 수 있는 방법이다.   

## SSL / TLS
### SSL
<b>SSL (Secure Sockets Layer)</b>는 Netscape Communications Corporation에서 웹 서버와 웹 브라우저간 보안을 위해서 만든 프로토콜이다.   

공개키 / 개인기. 즉, 공개키 방식과 대칭키 방식을 혼합한 기반으로 사용한다.   

<b>대칭키 방식</b>는 동일한 키로 암호화와 복호화를 수행하는 방법이다. 그리고 암호화와 복호화가 쉽다는 장점이 있다. 그러나 키를 전달할 때 문제가 된다는 단점을 가지고 있다.   

<b>공개키 방식</b>은 서로 다른 키로 암호화와 복호화를 수행하는 방법이다. 데이터 암호화 시 공개키를 사용하고 데이터 복호화 시 개인키를 사용한다. 암호화 시 사용하는 공개키가 해커에 손에 들어가도 상관이 없다. 복호화를 하기 위해서는 개인키가 필요하기 때문이다. 해당 방법은 공개키를 무한대로 나누어도 상관없기 때문에 키 전달에 문제가 없다. 그러나 대칭키 방식보다 암호화 연산 시간이 더 소요되어 비용이 크다.   

## SSL을 사용하는 이유
<b>SSL</b>을 사용하는 이유는 앞서 말한 것과 같이 서버와 브라우저 간 전송되는 데이터를 외부의 해커로부터 보호하기 위해 필요하다. 즉, ```보안```이 키워드이다.   

## SSL 통신 과정
SSL은 공개키 방식으로 대칭키를 전달한다. 그리고 해당 대칭키를 활용하여 암호화와 복호화를 하고 서버와 브라우저 간 통신을 진행한다.   

[HTTPS](https://www.youtube.com/watch?v=wPdH7lJ8jf0)