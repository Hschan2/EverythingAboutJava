package com.example.proxy;

import com.example.proxy.proxys.GameService;
import com.example.proxy.proxys.GameServiceProxyAfter;

public class Client {
    public static void main(String[] args) {
//        GameService 인터페이스와 GameServiceProxyAfter 클래스를 사용한다.
//        DefaultGameService를 사용하는데 즉시 가져와서 사용하는 것이 아닌 프록시를 걸쳐서 실행되도록 한다.
//        GameService (게임 실행)는 본인의 역할만 수행하며 그 외의 일은 GameServiceProxyAfter (걸리는 시간 구하기)에서 수행한다.
//        만약 인자에 DefaultGameService를 넣고 싶지 않다면 GameServiceProxyAfter에서 생성자를 없애고 조건문을 넣으면 된다.
//        GameService gameService = new GameServiceProxyAfter(new DefaultGameService());
        GameService gameService = new GameServiceProxyAfter();
        gameService.startGame();
    }
}
