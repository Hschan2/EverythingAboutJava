package com.java.Security;

public class SecurityClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Spring Security
//		인증과 인가를 제공하고 이에 초점을 맞춘 프레임워크
//		
//		Spring Security 특징
//		서블릿 API 통합
//		
//		Spring Web MVC와의 선택적 통합
//		
//		인증과 권한 부여를 모두 포괄적으로 확장 가능한 지원
//		
//		세션 고정, Clickjacking, 사이트 간 요청 위조 등과 같은 공격에서 보호
//		세션 고정 - 사용자 로그인 시 항상 일정하게 고정된 세션 ID 값을 사용하는 취약점
//		Clickjacking - 사용자가 클릭하고 있다고 인지하는 것과 다르게 어떤 것을 클릭하게 속이는 악의적 기법
//		사이트 간 요청 위조 - csrf라고 불리우며 사용자가 자신의 의지와는 무관하게 공격자가 의도한 행위를특정 웹 사이트에 요청하게 하는 공격 기법
//		
//		Spring Security 요청 처리
//		서블릿 필터 체인을 자동으로 구성하고 여러 개의 필터를 걸쳐서 처리
//		사용하고자 하는 필터 만을 그 안에서 사용한다.
//		
//		EX. 로그인 인증 처리 구조
//		유저 로그인 HTTP 요청 
//		-> AuthenticationFilter가 HTTP 서블릿 리퀘스트에서 사용자가 보낸 정보를 인터셉트
//		-> 이를 인증할 AuthenticationManager 인터페이스한테 인증용 객체 Username과 UserPassword Authentication 토큰을 만들어서 위임
//		-> 실제로 인증할 Authentication Provider한테 Authentication 객체 전달
//		-> UserDetailsService에서 해당 Authentication을 가지고 DB에 포함되어 있는 유저임을 확인
//		-> 확인 완료가 되었다면 UserDetails 객체로 꺼내서 유저 세션 생성
//		-> 나온 UserDetails를 Spring Security의 인 메모리 저장소인 SecurityContext 폴더에 저장
//		-> 유저의 세션 ID와 함께 응답
//		-> 요청 쿠키에서 JSESSIONID를 검증하고 유효하면 인증
//		
//		Security Filter (시큐리티 필터) 주요 필터들
//		SecurityContextPersistenceFilter
//		SecurityContextRepository를 통해 SecurityContext를 Load/Save 처리한다
//		
//		LogoutFilter
//		로그아웃 URL(기본값 : /logout)로의 요청을 감시하여 해당 사용자 로그아웃
//		
//		UsernamePasswordAuthenticationFilter
//		ID / 비밀번호 기반 Form 인증 요청 URL(기본값 : /login)을 감시하여 사용자 인증
//		
//		ExceptionTransiationFilter
//		요청 처리하는 중 발생할 수 있는 예외를 위임 혹은 전달
//		
//		FilterSecurityInterceptor
//		접근 권한 확인을 위해 요청을 AccessDecisionManager로 위임
//		이 필터 실행 시점은 사용자 인증 완료 판단
//		
//		Spring Security 사용법
//		XML 파일에 Spring Security 추가
//		3.2 버전 이후에는 xml 지원하지 않고 자바 기반으로 설정
//		@EnableWebSecurity
//		@EnableGlobalMethodSecurity => 어떠한 어노테이션을 컨트롤러에서 사용할 수 있도록 인증을 설정해주는 것
//		
//		Configure 메서드에서 어떤 유저에 DTO Service를 사용할 지 패스워드 인코더를 사용할 지 명시하고
//		authenticationManagerBean 메소드에서 어떤 Authentication 매니저를 사용할 지 명시
//		요청 받는 Configure 메서드에서 CORS등 선언
		
	}

}
