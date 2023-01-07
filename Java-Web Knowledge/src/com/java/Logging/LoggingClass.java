package com.java.Logging;

public class LoggingClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		로깅
//		운영 체제 혹은 소프트웨어가 실행 중에 발생하는 이벤트를 기록하는 행위
//		
//		로깅 활용 예상
//		유저 경로 분석, 유저 타겟팅, 이슈 대응, 사용성 진단, 버그 트래킹, 시나리오 성과 측정 및 개선, 사업 성과 진단
//		
//		1. 서비스 동작 상태 파악, 장애 파악 및 알람
//		
//		로깅 하는 방법
//			1) System API call - syslog() (API 사용)
//			2) 라이브러리 사용 - JCL, slf4j(SpringBoot), log4j, logback...
//		
//		slf4j (로깅 추상화 라이브러리)
//			- API - 로깅 인터페이스
//			- Bridge - 레거시를 위함, 로거 호출을 slf4j 인터페이스로 연결, 로거 호출을 받아 slf4j API 호출, 여러개 넣어도 상관 X, Bridge와 Binder를 같은 종류로 사용 X
//			- Binding - 여러 Logger 연결, 연결된 Logger의 API를 호출, Binding은 한 개만 추가!, logback (실제 호출, 기본 내장, 기본 설정)
//			
//			jcl(혹은 jul)의 브릿지를 호출하면 API가 자동 호출하고 API는 Binding의 실제 로그를 호출하여 jcl이 log4j2로 바뀌게 된다
//			
//			* jcl을 slf4j로 바뀔 때 문제가 발생할 경우 다시 바인딩하여 jcl로 바꾸라는 명령을 내린다
//			
//			private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class); 로 설정 가능
//			@Slf4j의 Lombok을 활용해 의존성 추가하여 로거 설정 가능
//			log.error => 로깅 레벨
//			
//			로깅 레벨
//			1. FATAL - 매우 심각한 에러가 발생할 경우 프로그램 종료되는 경우 증가 => 사용 권장 X
//			2. ERROR - 에러가 발생하지만 프로그램 종료 X
//			3. WARN - 에러가 날 잠재적 가능성이 존재할 경우 알람 설정
//			4. INFO - APP의 상태를 간결하게 보여줌
//			5. DEBUG - INFO보다 더 자세한 상태 정보가 필요한 경우. 권한이 없어 디버깅이 불가능한 경우 유용
//			6. TRACE - DEBUG보다 더 자세한 정보가 필요한 경우. 개발환경에서 버그를 해결하기 위해 사용. 최종 프로덕션 혹은 커밋에 포함하면 X
//			
//		로깅 메시지
//			* 로그 메시지 독자
//			1) Machine - 구조화된 대량의 데이터를 해석하는데 능함
//			2) Human - 대량의 데이터를 다루기 어려움. 구조화 되지 않은 데이터를 해석하는데 능함
//		1. 첫번째 독자 고려! - 한글과 영어 - 영어 추천
//		2. 독자를 고려
//		3. 로그 메시지에 컨텍스트를 담기 - 에러가 발생 시 로깅 메시지만으로 상황을 파악할 수 있도록 작성. 가능하면 해결 방법도!
//		
//		로깅 시 주의할 점
//		1. 로그 파일/DB 생명 주기 및 저장소 용량
//		2. 개인정보 - 보안을 위해
//		3. 시스템 주요 정보 (시스템 보안, 계정 정보) - 보안을 위해
//			
//		로그 활용
//		1. Sentry
//		2. ElasticSearch + Kibana
//		3. Splunk
	}

}
