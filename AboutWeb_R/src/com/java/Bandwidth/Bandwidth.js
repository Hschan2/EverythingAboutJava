// node.js에서 대역폭 제한하기

라이브러리 설치 - npm install --save express-rate-limit
라이브러리 선언 - const rateLimit = require("express-rate-limit");
// windowMs: 15 * 60 * 1000 => 15분, max: 100 => indowMs 당 100개 요청까지 제한, message: "..." => 메세지
대역폭 제한 변수 선언 - const limiter({ windowMs: 15 * 60 * 1000, max: 100, message: "Too many account created from this IP" });
// 모든 요청에 적용
실행 - app.use(limiter);
// /api/가 시작할 때만 요청에 적용
실행 - app.use('/api/', limiter);
// 전송
app.post("/create-account", limiter, function(req, res) {

});

// 번외

// 네트워크의 데이터 베이스 트래픽 줄이기
// 1. 네트워크 패킷 스니퍼를 이용해 문제 애플리케이션 추적하기
//     - 스니퍼는 데이터 베이스 질의가 접속을 얼마나 만들었는지, 얼마나 많은 데이터가 전송되고 있는지 보여준다
// 2. SQL 문 점검하기
//     - 애플리케이션이 사용하는 것보다 더 많은 열을 돌려보낸 질의 찾기
//     - 프로그램이 필요로 하는 것보다 더 많은 행 찾기
// 3. 문제의 애플리케이션이 접속 공유 혹은 풀링을 사용하고 있는지 확인하기
//     - 실시간 혹은 데이터 확보 애플리케이션들은 데이터 베이스로의 접속을 열고 사용하고 다시 닫는 방법보다 접속 공유 방식 선택하기