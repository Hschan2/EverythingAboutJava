package com.java.AOP;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

public class AOPClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		AOP는?
//		Aspect Oriented Programming (관점 지향 프로그래밍)
//		흩어진 코드를 한 곳으로 모은다
//		어떠한 로직을 기준으로 핵심적인 관점 그리고 부가적인 관점으로 나누어 보고 그 관점을 기준으로 각각 모듈화(어떤 공통된 로직 혹은 기능을 하나의 단위로 묶는 것)한다
//		
//		인프라 로직은
//		애플리케이션의 전 영역에서 나타날 수 있다
//		중복 코드를 만들어 낼 가능성으로 유지 보수가 힘들어질 수 있다
//		비즈니스 로직을 이해하기가 어렵게 만든다.
//				
//		AOP의 부가 기능
//		Target
//		어떤 대상에 부가 기능을 부여할 것인가?
//		
//		Advice
//		어떤 부가 기능을 사용할 것인가? (Before, AfterReturning, AfterThrowing, After, Around)
//		Before - 메서드 호출 전
//		AfterReturning - 메서드 실행 후 제대로 된 결과값을 Returning 했을 때
//		AfterThrowing - 실행 시점에서 무엇인가 Exception이 발생했을 때
//		After - 위 두 개와 상관없이 메서드가 끝났을 때
//		Around - 메서드 실행 전 후로 무엇인가를 할 수 있다
//		
//		Join point
//		어디에 적용할 것인가?
//		**메서드, 필드, 객체, 생성자...
//		
//		Point cut
//		실제 advice가 적용될 지점, Spring AOP에서는 advice가 적용될 메서드를 선정한다.
//		
//		EX
//		UselessAdvice라는 Advice를 만든다
//		@Aspect // Aspect를 사용하겠다
//		@Component
//		public class UselessAdvisor
//		
//		@Around("execution(*...)") // @Around라는 Advice를 한다. execution => point cut. 이는 어떤 패키지 혹은 구조에서 어떠한 클래스의 어떤 메서드에 적용할 것이다를 의미
//		간단하게 @Around("@annotation(...)") 으로 사용할 수 있다. => 이 어노테이션을 단 메서드는 해당하는 AOP가 적용될 것
//		
//		AOP 구현 방법
//		컴파일
//		J.java -> J.class하는 과정에서 해당하는 aspect를 끼워 넣어 준다.
//		
//		클래스 로드시
//		J.class를 클래스 로더가 메모리 상에 올릴 때 AOP를 적용한다.
//		
//		프록시 패턴
//		어떠한 타켓 클래스를 프록시로 감싸 부가 기능을 제공하는 프록시로 감싸서 실행
//		IoC/DI를 기반으로 가능
//		
//		그 외
//		@Transactional
//		서비스 로직을 하나의 트랜잭션으로 만들 때 로직의 시작점과 끝점에 Transaction을 열어주고 커밋해줘야 했던 것을 신경쓰지 않도록 만들어 준다.
//		
//		Interceptor
//		Filter
//		
//					Spring AOP 								vs  AspectJ
//		목표			간단한 AOP 기능 제공								완벽한 AOP 기능 제공
//		Join point	메서드 레벨만 지원								생성자, 필드, 메서드 등 다양하게 지원
//		Weaving		런타임 시에만 가능								런타임은 제공X, Compile-time, Post-compile, Load-time 제공
//		대상			Spring Container가 관리하는 Bean에서만 가능		모든 java Object에서 가능
	
//		예시
//		
//		흩어진 AAAA와 BBBB
//		class A {
//			method a() {
//				AAAA -> AAA로 바꿔야 할 시 모든 AAAA를 찾아서 바꿔야 함(다른 클래스에 있는 것도) => 문제 발생
//				안녕하세요
//				BBBB
//			}
//			
//			method b() {
//				AAAA
//				반갑습니다
//				BBBB
//			}
//		}
//		
//		class B {
//			method c() {
//				AAAA
//				겨울입니다
//				BBBB
//			}
//		}
//		
//		모아 놓은 AAAA와 BBBB
//		class A {
//			method a() {
//				안녕하세요
//			}
//			
//			method b() {
//				반갑습니다
//			}
//		}
//		
//		class B {
//			method c() {
//				겨울입니다
//			}
//		}
//		
//		흩어진 AAAA와 BBBB에서 발생한 문제를 해결하기 위해 공통적으로 사용하는 AAAA와 BBBB를 하나로 모아서 사용하는 방법 => AOP
//		class AAAABBBB {
//			method aaaabbbb(JoinPoint point) {
//				AAAA
//				point.execute()
//				BBBB
//			}
//		}
//		
//		AOP 구현 방법
//		1. 컴파일 (A.java ---(AOP)---> A.class)
//		2. 바이트코드 조작 (A.java -> A.class => class를 클래스 로더가 읽어올 때 메모리 상에 필요한 기능 넣어서 조작 (Aspect J가 제공))
//		3. 프록시 패턴 (Spring AOP가 사용하는 방법)
		
//		프록시 패턴
//		기존 코드를 건드리지 않고 새 기능 추가하는 것이 프록시 패턴이다.
//		자동으로 이루어진다고 생각하면 좋다.
//		Class 내에 @Transactional 어노테이션이 붙으면 프록시가 새로 만들어진다.
		
//		AOP 활용 방법
//			Annotation을 사용해서 AOP 활용하기
//				@LogExecutionTime이라는 Annotation은 어디에 적용할지 표시 해두는 용도
//					- @interface를 생성하여 Annotation을 만들겠다.
//						=> @Target(ElementType.METHOD)를 써서 메서드에 사용하겠다
//						=> Retention(RetentionPolicy.RUNTIME)를 써서 Annotation 정보를 런타임 때까지 유지하겠다
//						
//				실제 Aspect (@LogExecutionTime Annotation이 달린 곳에 적용)
//					@Around Annotation이 붙은 메서드는 @LogExecutionTime 붙은 다른 메서드를 불러올 수 있다
					
	}

}
