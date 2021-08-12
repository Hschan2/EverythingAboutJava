# Logging (로깅)
<B>로깅</B>은 프로그램 동작 시 발생하는 모든 일을 기록하는 행위를 말한다. 즉, 모든 일. 최소한의 목적을 의미한다.   

* 로깅
    * 서비스 동작 상태
        * 시스템 로딩
        * HTTP 통신
        * 트랜잭션
        * DB 요청
        * 의도를 가진 Exception
    * 장애 (Exception, Error)
        * I/O Exception
        * NullPointException
        * 의도하지 않은 Exception   

## 로깅은 언제 하는가?
* 프로젝트의 성격에 맞게 하기
* 팀에 맞게 하기   

즉, 때에 따라 요구사항에 맞게 하면 된다.   

### 로깅 - 기록
* 로깅을 기록하기 위한 방법
    * ```System.out.println("로깅로깅")```
    * ```System.err.println("에러로깅로깅")```
    * 로깅 프레임 워크
        * SLF4J
        * Logback
        * Lof4j
        * nlog   

## 로깅 vs System.out.println()
* 출력 형식을 지정할 수 있다.
* 로그 레벨에 따라 남기고 싶은 로그를 별도로 지정할 수 있다.
* 콘솔뿐만 아니라 파일이나 네트워크 등 로그를 별도에 위치에 남길 수 있다.   

### 로그레벨 [Link](https://youtu.be/1MD5xbwznlI?t=324)

## 로깅 vs 디버깅
* 프로그래밍의 절반은 디버깅
* 디버깅을 할 수 없는 상황에서는 로깅이 최선의 선택 (예. 실 서버 구동 중)
* 디버깅을 사용할 수 있다면 디버깅 최대한 활용   

## SLF4J (Simple Logging Facade for Java)
<b>SLF4J</b>는 다양한 로깅 프레임워크에 대한 추상화(인터페이스) 역할을 말한다.   

* 단독으로 사용 불가능
* 최종 사용자가 배포 시 원하는 구현체를 선택 가능

### SLF4J 동작 과정
* 개발일 때
    * SLF4J API를 이용하여 로깅 코드를 작성
* 배포할 때
    * 바인딩된 Logging Framework가 실제 로깅 코드를 수행하는 과정을 거침   

이러한 과정은 <b>Bridge</b>, <b>API</b>, <b>Binding</b> 모듈을 통해 수행될 수 있다.   

* Bridge
    * 다른 로깅 API로의 Logger 호출을 SLF4J API로 연결
    * 이전의 레거시 로깅 프레임워크를 위한 라이브러리로 여러 개 사용이 가능
    * Binding 모듈에서 사용될 프레임워크와 달라야 함

* API
    * 로깅에 대한 추상 레이어(인터페이스) 제공
    * 하나의 API 모듈에 하나의 Binding 모듈

* Binding
    * SLF4J API를 로깅 구현체(Logging Framework)와 연결 (어댑터 역할)
    * 하나의 API 모듈에 하나의 Binding 모듈   

### 실습 [Link](https://youtu.be/1MD5xbwznlI?t=558)

## Logback
* SLF4J의 구현체
* Log4J를 기반으로 만들어진 프레임워크   

### Logback - 구조
* 다른 두 모듈을 위한 기반 역할을 하는 모듈
* Appender와 Layout 인터페이스가 해당 모듈에 속함
    * => Logback-Core, Interface: Appender and Layout

* Logback-Classic, Class: Logger =>
    * Logback-Core를 가지며 SLF4J API를 구현
    * Logger 클래스가 해당 모듈에 속함

* Logback-Access =>
    * Servlet Container와 통합되어 HTTP 엑세스에 대한 로깅 기능을 제공
    * 웹 애플리케이션 레벨이 아닌 컨테이너 레벨에서 설치   

### Logback - 설정 요소
* Logger: 어떻게 기록하는가
    * 실제 로깅을 수행하는 구성 요소
    * 출력 레벨 조정: TRACE < DEBUG < INFO < WARN < ERROR
* Appender: 어디에다 기록하는가
    * 로그 메세지가 출력할 대상 결정
    * ConsoleAppender
    * FileAppender
    * RollingFileAppender
* Layout: 어떻게 출력하는가
    * Encoder (Layout): 로그 이벤트를 바이트 배열로 변환하고 해당 바이트 배열을 OutputStream에 쓰는 작업
    * Appender에 포함
    * 사용자가 지정한 형식으로 표현될 로그메세지를 변환하는 역할

### 실습 - 요구 사항
* 테스트, 개발 환경에서 Info 레벨 로그를 <b>Console</b>로 출력
* 프로덕션 환경에서는 Info, Warn, Error 레벨 별 로그를 <b>파일</b>로 남기기   

[실습](https://youtu.be/JqZzy7RyudI?t=186)   

* 추가적으로 알아볼 것
    * 로깅 이벤트 처리 (로깅을 통해 카프카를 이용하여 다른 서비스에 이벤트 처리)
    * 로깅의 깊은 곳

[Logging](https://www.youtube.com/watch?v=1MD5xbwznlI)