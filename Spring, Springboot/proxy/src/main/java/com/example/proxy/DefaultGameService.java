package com.example.proxy;

import com.example.proxy.proxys.GameService;

//실제 게임서비스를 구현할 클래스
public class DefaultGameService implements GameService {

//    코드 재사용
//    OCP (Open Closed Principle)에 해당 => 기존의 코드를 변경하지 않고 기능을 추가할 수 있도록 설계
//    SRP (Single Responsibility Principle)도 해당 => 클래스가 변경되어야 할 이유는 단 하나여야 한다. (부가 기능을 추가하지 않음
    @Override
    public void startGame() {
        System.out.println("게임에 실행합니다.");
    }
}
