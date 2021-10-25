package com.example.proxy;

import com.example.proxy.proxys.GameServiceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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


	}

}
