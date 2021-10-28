package com.example.proxy;

import com.example.proxy.proxys.GameServiceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);

//		프록시 디자인 패턴
//            특정 객체에 대한 접근을 제어하거나 기능을 추가할 수 있는 패턴
//            클래스를 바로 실행하지 않고 프록시를 거쳐서 실행하는 부분에서 프록시에 해당
//            즉, 맨 처음 요청은 프록시가 받음
//            접근 제어, 초기화 지연, 로깅, 캐싱 적용 가능
//            프록시는 되도록 인터페이스로 생성하여 사용

//        클라이언트가 게임 서비스를 받고 게임을 실행하는 예시
//        GameService gameService = new GameService();
        GameService gameService = new GameServiceProxy();
        gameService.startGame();

//        프록시 패턴의 장점
//                기존 코드를 변경하지 않고 새로운 기능을 추가할 수 있다.
//                기존 코드가 해야하는 일만 유지할 수 있다.
//                기능 추가 및 초기화 지연 등으로 다양하게 활용할 수 있다.

//        프록시 패턴의 단점
//                코드의 복잡도가 증가한다.

//		자바와 스프링에서의 프록시 패턴
//				자바
//						다이나믹 프록시 (Runtime과 관련, 앱 실행중에 동적으로 변경 가능 )
//						java.lang.reflect.Proxy
//				스프링
//						스프링 AOP
//							스프링 AOP는 다른 스프링 어노테이션의 기반. (예. @Transactional, @Cacheable 등) 그러니 더 알아볼 것을 추천
	}

}
