# CORS 이겨내기
<b>CORS(Cross Origin Resource Sharing)</b>은 교차 출처 리소스 공유이다. 그리고 동일한 출처에서만 HTTP 요청이 가능하도록 하는 정책인 <b>Same Origin Policy</b>를 알고 있어야 한다.   

간단하게, 인가받지 않은 클라이언트에서 서버로 HTTP 통신을 요청하는 것을 방지하기 위한 정책이다. 예를 들어, 한 홈페이지에 방문하였을 때 나의 웹페이지가 가진 쿠키를 통해 나의 개인정보를 네이버에 요청하면 내 개인정보가 해커에 노출될 우려가 있다. 이를 방지하기 위해 네이버 서버에서는 허용된 웹페이지 URL이 아닐 경우, CORS를 발생시켜서 정보를 제공하지 않는 정책을 수립하고 있다.

![CORS Error](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOaZOO%2FbtrhSCXez7c%2FIw2Xiv3NDLxmfK0afTun9K%2Fimg.png)

## 해결 방법
* Server to Server 통신 (Proxy Server)
<b>CORS</b>는 클라이언트에서 발생하는 현상으로 서버끼리 통신하면 발생하지 않는다. 그렇기 때문에 서비스 개발시에 프론트 서버를 하나 더 두는 방식으로 CORS를 예방할 수 있다. 서버끼리 통신을 한다면 보안성도 높아지기 때문에 활용도가 높다.

* White-List에 추가
이 방법은 요청하는 IP를 백엔드 쪽에서 White-List에 추가하고 해당 IP에서 오는 통신은 CORS를 허용하도록 처리하는 방식이다. 이 방법은 가장 많이 사용하는 <b>Access-Control-Allow-Origin</b>를 세팅하는 방법이다.

## 동작방식

일반적인 예를 들어, <b>Access-Control-Allow-Origin</b> 에 허용된 클라이언트라고 가정을 한다면   

HTTP 프로토콜을 사용하여 요청을 보낼 때, 브라우저는 요청 헤더에 Origin이라는 필드에 요청을 보내는 출처를 함께 담아보낸다. 이후 서버가 이 요청에 대한 응답을 할때 <b>Access-Control-Allow-Origin</b>이라는 값에 이 리소스를 접근하는 것이 허용된 출처를 내려준고, 이후 응답을 받은 브라우저는 자신의 요청에 Origin과 서버가 보내준 응답의 <b>Access-Control-Allow-Origin</b>을 비교해본 후 이 응답이 유효한 응답인지 아닌지를 결정한다.

## 에러 해결
### Preflight Request
가장 많이 발생하는 시나리오다. 이 경우, 브라우저는 요청을 한 번에 보내지 않고 예비 요청과 실제 요청을 나누어서 서버로 전송한다. 이 때, 예비 요청을 <b>Preflight</b>이라고 부른다. 이 요청은 HTTP 메소드 중 ```Options``` 메소드가 사용된다.   

이 역할은 본 요청을 보내기 전, 브라우저가 스스로 해당 요청을 보내는 것이 안전한가 판단하고 확인하는 것이다.   

자바스크립트로 API를 불러올 때, fetch API를 사용하여 브라우저에 리소스를 받아오라는 요청을 내리면 브라우저는 서버에 예비 요청을 먼저 보낸다. 그리고 서버는 예비 요청에 대한 응답으로 현재 허용하고 있는 것과 금지하고 있는 것에 대한 정보를 응답 헤더에 담아 브라우저에 응답한다.   

그리고 브라우저는 예비 요청과 서버 응답을 비교하고 나서 안전하다고 판단이 되면 다시 실제 요청을 보낸다. 실제 요청에 대한 응답이 올 경우, 최종적으로 응답 데이터를 자바스크립트에 넘긴다. 여기서 Origin값이 다르다면 에러가 발생한다.   

가장 중요한 것은 예비 요청에 대해 정상적인 ```200``` 코드가 떨어질 수 있지만, 실제 요청에서 빨간색 에러가 발생할 수 있다. CORS의 정책 위반은 예비 요청의 성공 여부와 관계가 없는 것을 알고 있어야 한다.   

대부분의 개발자들은 이 방식을 사용하지만, 모든 상황에서 이처럼 두 번씩 요청을 보낼 수 없다. 까다롭지만 실제 요청만 CORS를 검사하는 방법은 ```Simple Request```가 있다.   

## Simple Request
실제 요청만 통해 <b>CORS</b>를 검사하는 방법이다. 한 번만 보내는 만큼 조건이 까다롭다.   

* 조건 1 - 요청 메소드가 GET, HEAD, POST 중 하나
* 조건 2 - Accept, Accept-Language, Content-Language, Content-Type, DPR, Downlink, Save-Data, Viewport-Width, Width를 제외한 헤더를 사용 불가
* 조건 3 - 만약, Content-Type을 사용하는 경우 Application/x-www-form-urlencoded, multipart/form-data, text/plain만 허용   

조건 2와 조건 3은 까다롭다. 사용자 인증하는 ```Authorization 헤더```조차 사용하지 못하기 때문이다. 조건 3은 대부분의 HTTP API는 ```text/xml```, ```application/json```이 많이 사용되므로 조건이 어렵다.

## Credentialed Request
인증된 요청을 사용하는 방법이다. 보안을 강화하고 싶을 때 사용한다. 기본적으로 브라우저가 제공하는 리소스 요청 API로 XMLHttpRequest 객체나 fetch API는 별도의 옵션없이 브라우저의 쿠키 정보나 인증과 관련된 헤더를 함부로 요청에 담지 않는다. 이 때, 요청에 인증과 관련된 정보를 담을 수 있게 하는 옵션이 ```Credentials``` 옵션이다. 해당 옵션은 총 3가지의 값을 사용할 수 있다.   

* same-origin
* include
* omit   

![Credentialed Request](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FSxCYi%2FbtrhTMxMs2F%2F51VqXKmc0fcBP5cb0Xkxk0%2Fimg.png)