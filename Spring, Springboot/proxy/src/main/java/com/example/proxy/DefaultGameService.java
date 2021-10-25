package com.example.proxy;

import com.example.proxy.proxys.GameService;

//실제 게임서비스를 구현할 클래스
public class DefaultGameService implements GameService {

    @Override
    public void startGame() {
        System.out.println("게임에 실행합니다.");
    }
}
