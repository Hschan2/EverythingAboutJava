# CORS (Cross-Origin Resource Sharing)

## SOP (Same Origin Policy)
다른 출처의 리소스를 사용하는 것에 제한하는 보안 방식이다.   

* 출처 (Origin)   
```Protocol + Host + Port + Path + Query String + Fragment```로 구성되었다. URL의 Protocol, Host, Port를 통해 같은 출처인지 다른 출처인지 판단할 수 있다.   

### SOP를 사용하는 이유
특정 사이트에 접속할 때, 해당 사이트는 요청의 출처(Origin)을 확인한다. 해당 사이트와 같은 출처를 갖고 있는지 다른 출처를 가지고 있는지 확인 후, 같은 경우 접속을 허용하고 아닐 경우 접속을 허용하지 않는다.   

### 다른 출처의 리소스가 필요하다면?
CORS를 사용한다. CORS는 Cross-Origin Resource Sharing이며, 다른 출처의 자원을 공유하는 것을 말한다.   

* CORS   
교차 출처 리소스 공유는 추가 HTTP 헤더를 사용하여 한 출처에서 실행중인 웹 애플리케이션이 ```다른 출처의 선택한 자원에 접근할 수 있는 권한```을 부여하도록 ```브라우저에 알려주는 체제```이다. (출처 - 모질라)   

### CORS 접근제어 시나리오
* 단순 요청 (Simple Request)
* 프리플라이트 요청 (Preflight Request)
* 인증정보 포함 요청 (Credentialed Request)   

#### 프리플라이트 요청 (Preflight Request)
Options 메서드를 통해 다른 도메인의 리소스에 요청이 가능한지 확인하는 작업이며, 요청이 가능하다면 실제 요청(Actual Request)을 보낸다. 즉, 본 요청을 보내기 전에 서버에 물어보는 것이다. 그리고 서버의 응답을 Options 메서드를 통해 알 수 있다.   

```
Preflight

PREFLIGHT REQUEST
    - Origin: 요청 출처
    - Access-Control-Request-Method: 실제 요청의 메서드
    - Access-Control-Request-Headers: 실제 요청의 추가 헤더
```

```
Preflight

PREFLIGHT RESPONSE
    - Access-Control-Allow-Origin: 서버 측 허가 출처
    - Access-Control-Allow-Method: 서버 측 허가 메서드
    - Access-Control-Request-Headers: 서버 측 허가 헤더
    - Access-Control-Max-Age: Preflight 응답 캐시 시간
```

```
Preflight

PREFLIGHT RESPONSE가 가져야 하는 특징
    - 응답 코드는 200대여야 한다.
    - 응답 바디는 비어있는 것이 좋다.
```

### 단순 요청 (Simple Request)
Preflight와 다르게 본 요청을 보내면서 즉시 Cross Origin인지 확인을 하는 절차이다. Preflight 요청 없이 바로 요청을 날린다. 다음 조건을 모두 만족해야 한다.   
* GET, POST, HEAD 메서드   

* Content-Type
    * application/x-www-form-urlencoded
    * multipart/form-data
    * text/plain

* 헤더는 Accept, Accept-Language, Content-Language, Content-Type만 허용된다.   

### Preflight가 필요한 이유
CORS를 모르는 서버를 위해서 필요하다. CORS가 모르는 서버에 클라이언트에서 Actual Request. 즉, Preflight없이 바로 본 요청을 보낼 때 어떤 오류가 생기는지 확인을 한다.   

클라이언트가 브라우저를 통해 서버에 요청을 하고 서버에서는 요청 온 것의 출처를 확인한다. 확인 후, ALLOW-ORIGIN이 없다고 판단이 되면 CORS 에러를 보낸다. Preflight를 사용하게 되면 이 같은 경우에 NO ACTUAL REQUEST를 보내어 다시 요청하는 일이 없게 만들어 서버를 지켜내게 된다.   

즉, CORS를 모르는 서버를 위해 정말 필요한 작업이다라고 이해하면 된다.   

## 인증정보 포함 요청 (Credentialed Request)
인증 관련 헤더를 포함할 때 사용하는 요청이다. 쿠키나 jwt 토큰을 사용하여 클라이언트에서 자동으로 담아서 보내고 싶을 때, 나의 Credentials를 Include하게 되면 서버 측까지 전달된다. 중요한 것은 서버 측에서도 설정을 해야 한다.   

* 클라이언트 측
    * Credentials: include

* 서버 측
    * Access-Control-Allow-Credentials: true
    * Access-Control-Allow-Origin: * 은 불가 (특정 사이트만 허용되게 해야 한다.)   

## CORS 해결하기
1. 프론트 프록시 서버 설정 (개발 환경)   
2. 직접 헤더에 설정하기   
3. 스프링 부트를 이용   

[출처](https://www.youtube.com/watch?v=-2TgkKYmJt4)