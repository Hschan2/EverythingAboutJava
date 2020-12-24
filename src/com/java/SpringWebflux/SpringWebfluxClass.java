package com.java.SpringWebflux;

public class SpringWebfluxClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Spring Webflux
//			* Reactive Streams = 논블로킹(Non-Blocking), 백프레셔(Back Pressure)를 이용한 비동기 데이터 처리의 표준
//		Project Reactor의 웹의 스트리밍 처리를 담당하는 역할
//		적은 수의 스레드로 동시성을 처리 (너무 많은 트래픽 요청으로 인한 레이턴시(지연)를 최소화하기 위해) - 사용자가 요청을 하면 스레드가 생기고 그 스레드가 많이 늘어나면 레이턴시가 생긴다
//		
//		Webflux와 Spring MVC의 차이
//		Webflux - 적은 수의 스레드를 사용해서 한번에 여러 요청들을 다룬다. (Node JS의 이벤트 루프 형식과 같은 것) 
//					사용자의 요청이 들어오면 커넬에서 요청이 있는 동안 돌아와서 다른 프로세싱을 진행한다. => 논블록 - 자원적으로 효율적
//					즉, A의 요청이 들어오면 계속 기다리지 않고 그 다음 프로세스를 진행하고 A의 응답이 오면 A를 계속 진행
//		Spring MVC - 스레드를 늘려서 동시성을 처리한다.
//		
//		Webflux > Spring MVC ? 아니다. 단지 적은 스레드와 메모리를 효율적으로 사용할 수 있는 것이다. 더 빠르다가 아닌 적은 리소스로 많은 트래픽을 감당할 수 있다가 맞다.
//				
//		Functional Programming
//		함수가 반환 값이나 인자가 될 수 있어 람다식과 메서드 체이닝을 통해 결과를 만들 수 있다.
//		함수가 일급객체로 쓰일 수 있어 (== 반환값, 인자가 될 수 있음) 메소드 체이닝과 람다를 섞어 쓰는 것 (JAVA 8 이후)
//		
//		Reactive Programming
//		* Reactive Stream
//			Asynchronous Non-Blocking에서 작동하는 스트림
//			Publisher(웹 클라이언트, DB)에서 변경이 생기면 Subscriber에 변경된 데이터를 Stream으로 전달하는 것
//		이러한 Stream으로 프로그래밍하는 패러다임이 Reactive Programming다.
//		
//		Backpressure
//		Subscriber로 들어오는 Stream의 양을 조정하고 적은 컴퓨팅 자원으로 일을 처리하기 가능한 정도만 받자는 것
//		text/event-stream을 써보면 알 수 있다.
//		BUT 소켓이나 실시간 통신에 사용할 것 같다.
//		
//		Reactive
//		어떤 이벤트가 발생했을 때 그 이벤트를 구독해 다른 변경점을 만들어 낸다. 즉, 말 그대로 반응한다.
//		
//		Reactive API
//		Reactor
//		JDK9 스펙의 Reactive Stream을 구현한 라이브러리
//		RxJava
//		JDK9 스펙의 Reactive Stream을 구현한 라이브러리
//		
//		Flux
//		스트림. Stream.of해서 map, filter, reduce하는 것처럼 0~n개의 배열 같은 시퀀스로 비동기적
//		Just로 배열을 만드는 등...
//		
//		Mono
//		0~1개의 결과 (단일 항목)
//		여러 개를 가지는 Flux와 달리 하나를 가지고 있으며 똑같이 배열 등이 가능하다. 하나를 비동기적으로 스트림의 함수를 이용해 사용 가능하다.
//		
//		Annotated Controller
//		어노테이션을 이용해서 컨트롤 하는 것으로 기존 MVC에서 사용했던 방식
//		MVC와 다르게 Flux와 Mono를 사용 가능하다
//		
//		Functional Endpoints
//		ServerRequest를 받아 사용한다. (MVC - httpServletRequests)
//		bodyToMono는 RequestBody로 들어온 것을 Person.class 형식으로 바꿔서 만든다
//		RouterFunction을 만들고 라우트로 시작해 get,post의 형식으로 연결
//		before와 after로 filter와 interceptor 기능을 구현할 수 있다.
//		사용할 때 핸들러 Function -> 라우터 Function을 만들고 컨피규레이션에 @Bean으로 등록하면 된다.
//		
//		Webflux을 경험하고 싶다면 Web Clients를 사용하면 된다.
//		Non-Blocking WebStack을 고려하면 써도 된다.
//		현재 자주 사용하고 있는 것듣로 리액티브이기 때문에 굳이 바꾸려고 하지 않아도 된다.
//		
//		결론
//		Spring 5에서 기존 Servlet Stack과 다른 Reactive Stack을 만들었다.
//		Spring Webflux는  Spring Reactive Stack의 Web Framework 역할을 한다. (Spring MVC의 포지션)
//		Reactive Programming은 컴퓨터 자원을 효율적으로 윤용하기 위해 고안된 패러다임
//		Reactive Programming은 Asynchronous Non-blocking IO, Data Stream에 기반하여 메소드 체이닝과 람다식을 이룬다.
//		Reactive Stream에서 Publisher는 Subscriber에 Data Stream을 제한없이 보내고 이는 Backpressure로 제한
//		Mono(스트림), Flux(스트림)는 단일 혹은 복수 항목 상관없이 Stream처럼 사용하되 Reactive하게 작동 가능하다.
//		새로운 Functional Endpoints 기능을 통해 조금 더 함수형 프로그래밍답게 Controller를 대체할 수 있다.
//		End-to-end Reactive하지 않다면 의미 없으며 Spring Security Reactive, ReactiveRepository를 함께 사용해야 한다.
//		Spring MVC가 잘 돌아가면 굳이 갈아탈 필요는 없다.
		
	}

}
