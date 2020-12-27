package com.java.aboutSpring;

public class SpringBoot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// String Boot는 단독 실행되는, 사용화 가능한 수준의 스프링 기반 앱을 쉽게 만들 수 있다.
		// 최소 설정으로 스프링 플랫폼과 서드파티 라이브러리를 사용할 수 있도록 한다
		// => 스프링을 더 편하게
		
		/*
			스프링 부트가 해주는 것
				1) Auto Configutation
					=> 스프링 기능을 위한 자동 설정 지원, starter 의존성을 통해 간단히 설정 가능
					* 어노테이션을 통해 쉽게 작업할 수 있다. (ex. EnableConfiguration -> 사용하고자 하는 빈을 자동으로 생성 -> springfactories 에서 확인 가능)
				
				2) 쉬운 의존성 관리
					=> spring-boot-starter-*. 즉, 웹(앱)을 구축하기 위해 필요한 의존성들을 알아서 버전에 맞게 등록해주는 모듈(spring web, spring MVC, spring boot starter, jaon...)
					=> io.spring.dependency-management. 스프링 부트의 의존성을 관리해주는 플러그인, 프로젝트를 알아서 버전 관리를 해주며 직접 버전을 명시한 경우 해당 버전으로 오버라이딩 해준다. -> 버전 충돌을 해소시켜줌
				
				3) 내장 서버 
					=> 스프링을 더 사용하기 쉽게
					=> 애플리케이션 배포 시 복잡함을 피할 수 있게 만들어줌 -> 원래 스프링은 war파일로 저장 및 배포(war 파일 -> was 톰캣에 올림 -> 배포 및 실행) -> 스프링부트는 톰캣과 같은 서버가 존재해서 jar 파일로 배포 가능
					
				* 2018년도 부터 스프링 부트의 사용 빈도가 많아짐
				 
		*/
		
	}

}
