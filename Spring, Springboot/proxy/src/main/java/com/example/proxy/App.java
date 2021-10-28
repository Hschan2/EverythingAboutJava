package com.example.proxy;

import com.example.proxy.proxys.GameService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.annotation.Bean;

public class App {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Bean
    public ApplicationRunner applicationRunner(GameService gameService) { // ProxyBean, GameService를 주입 받아서 사용
        return args -> gameService.startGame(); // 아무것도 다른 설정이 되있지 않는 기존 메서드 실행
//        gameService를 인터페이스로 구현하였으면 인터페이스를 사용하고 클래스로 구현하였으면 상속받은 상태의 클래스 가져와서 사용
    }
}
