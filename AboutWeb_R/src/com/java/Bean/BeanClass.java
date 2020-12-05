package com.java.Bean;

public class BeanClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Bean
//		스프링 IoC 컨테이너가 관리하는 객체
//		
//		Bean과 일반적인 객체의 차이
//		
//		예.
//		일반적인 객체
//			OwnerController ownerController = new OwnerController();
//		
//		Bean
//			OwnerController bean = applicationContext.getBean(OwnerController.class); applicationContext가 담고 있는 객체, Bean을 가져오는 것
//			
//		Bean 등록하는 방법
//		1. Component Scanning
//			@Component
//				- @Repository
//				- @Service
//				- @Controller
//			
//			* StringBoot에서 @Componentscan은 ~지점부터 @Component 혹은 @Controller가 있는지 찾는 것
//				
//		2 직접 일일히 XML 혹은 자바 설정 파일에 등록
//		자바 설정 파일을 더 자주 사용
//		@Configuration 애노테이션을 사용한 클래스에 @Bean을 정의한 메서드를 생성. 해당 메서드에서 return하는 객체는 자동으로 Bean으로 등록
//		
//		어떻게 사용하는가?
//		1. @Autowired => IoC 컨테이너 안에 주입된 Bean을 가져와서 사용할 수 있다
//		2. @Inject
		
//		의존성 주입 (DI)
//		
//		필요한 의존성을 어떻게 받아올 것인가?
//		
//			@Autowired, @Inject를 어디에 붙이나?
//					- 생성자
//					- 필드
//					- Setter
//					
//			스프링 버전 4.3 부터 @Autowired 생략 가능
//			
//			Setter를 만들어서 @Autowired를 붙이는 방법도 있음
//
//		권장하는 방법
//		생성자 => 필수적으로 사용해야 하는 레퍼런스 옵션은 인스턴스를 만들지 못하도록 강제할 수 있다. 즉, OwnerRepository가 없으면 제대로 동작할 수 없음
//		
//		예. 
//		private final OwnerRepository owners;
//		
//		public OwnerController(OwnerRepository clinicService) {
//			this.owners = clinicService;
//		}
						
	}

}
