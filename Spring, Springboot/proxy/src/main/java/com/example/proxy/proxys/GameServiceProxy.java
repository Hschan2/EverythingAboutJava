package com.example.proxy.proxys;

import com.example.proxy.GameService;

public class GameServiceProxy extends GameService {

    @Override
    public void startGame() {
        long before = System.currentTimeMillis();
        super.startGame();
//        실행이 걸리는 시간
        System.out.println(System.currentTimeMillis() - before);
    }
}
