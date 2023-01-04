# JWT (Json Web Token)

## Session vs JWT
* Session 과정
    * 로그인을 하면 입장권 번호를 이용자에 전달
    * 페이지 이용 시마다 입장권 번호만 확인
    * 동시 접속 시 데이터 파일을 확인해야 하는 문제
* JWT 과정
    * 로그인을 하면 입장권에 다양한 정보(회원명, 이메일, 발급일, 유효기간 등)와 함께 이용자에 전달
    * 페이지 이용 시마다 유효기간 등 정보 확인
    * 장점: Stateless (데이터 확인 필요 없이 입장권만 확인)   

## JWT 보안 이슈
* 알고리즘 부분을 ```none```으로 채우면 <b>None 공격 대상</b>의 가능성이 높음
    * 간혹, 특정 서버가 <b>None 공격</b>을 허용
    * 최신 라이브러리 활용할 것
* 변환이 쉬운 문제
    * 디코딩이 쉬운 이유로 민감한 개인정보는 제외할 것
* Secret Key 문제
    * Secret Key를 쉽게 작성 시, 유추하여 해킹이 쉬워지는 문제
    * Secret Key를 길게 적고 공유하지 말 것
    * 생성용키/검증용키 2개 사용해 문제를 예방할 것 (Private Key + Public Key)
    * JWT 라이브러리 확인 후 활용할 것
* JWT 탈취
    * JWT 입장권 탈취 시, 사용자의 책임으로 문제가 발생
    * 사용 정지로 문제 해결이 가능하나 JWT 방식은 구조 상 어려움 (Stateless JWT는 해당 사항이 아님)
    * ```HttpOnly Cookie``` 등으로 입장권 탈취 방지할 것
    * 입장권 블랙리스트 생성해 관리할 것 (그러나 JWT의 장점이 사라진 Session 방식처럼 이용하게 되는 문제)
    * 유효기간을 짧게 두어 예방할 것 (예. 5분)
    * 유효기간을 짧게 둘 시, 유효기간이 긴 ```Refresh Token```이라는 다른 입장권 재발급을 위한 새로운 입장권을 발급받도록 개발 필요
    * ```Refresh Token``` 탈취 예방을 위해 ```Refresh Token Rotation``` 방법을 활용해 안정성을 높일 것 (Refresh Token는 1회 허용)   

## 주의
```
const JWTStrategy = require("passport-jwt").Strategy
const ExtractJWT = require("passport-jwt").ExtractJWT
```

위의 라이브러리는 토큰 생성 검증 라이브러리로 보안 기능이 없다. 그래서 추가 기능을 개발해서 사용해야 한다.   

[JWT 제대로 사용하기](https://www.youtube.com/watch?v=XXseiON9CV0&t=153s)