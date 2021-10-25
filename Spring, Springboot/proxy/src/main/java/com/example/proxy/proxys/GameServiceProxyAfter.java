package com.example.proxy.proxys;

import com.example.proxy.DefaultGameService;

public class GameServiceProxyAfter implements GameService {

//    프록시로 사용할 클래스는 서브젝트 타입을 가지고 있어야 한다.
    private GameService gameService;

//    startGame 안에 조건문으로 구현
//    public GameServiceProxyAfter(GameService gameService) {
//        this.gameService = gameService;
//    }

    @Override
    public void startGame() {
        long before = System.currentTimeMillis();

        if (this.gameService == null) {
            this.gameService = new DefaultGameService();
        }
        // 그 외 스프링 시큐리티에서 제어 등 코드 작성
        
        gameService.startGame();
        System.out.println(System.currentTimeMillis() - before);
    }
}
