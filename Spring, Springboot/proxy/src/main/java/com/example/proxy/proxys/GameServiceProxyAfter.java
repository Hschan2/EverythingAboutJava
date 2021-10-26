package com.example.proxy.proxys;

import com.example.proxy.DefaultGameService;

public class GameServiceProxyAfter implements GameService {

//    프록시로 사용할 클래스는 서브젝트 타입을 가지고 있어야 한다.
    private GameService gameService;

//    startGame 안에 조건문으로 구현
//    public GameServiceProxyAfter(GameService gameService) {
//        this.gameService = gameService;
//    }

//    GameService의 코드 재사용
    @Override
    public void startGame() {
        long before = System.currentTimeMillis();

//        아래처럼 작성하여 만약 비용이 많이 들 때, 사용할 때만 가져올 수 있도록 시간 지연하는 것이 프록시 패턴 장점
        if (this.gameService == null) {
            this.gameService = new DefaultGameService();
        }
        // 그 외 스프링 시큐리티에서 제어 등 코드 작성
        
        gameService.startGame();
        System.out.println(System.currentTimeMillis() - before);

//        return 타입이 있을 경우 캐싱 적용 가능한 것도 프록시 패턴의 장점
    }
}
