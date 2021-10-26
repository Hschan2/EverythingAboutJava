package com.example.proxy;

public class GameService {

//    해당 코드를 변경하지 않고 실행이 얼마나 걸리는지 체크하기
//    그러나 아래처럼 클래스만 생성하여 단순하게 사용할 경우와 달리 코드가 복잡해질 경우가 발생하는데 이는 프록시 패턴의 단점이다.
//    클래스로만 구현할 경우 만들어야 할 것이 많으며 상속을 해야할 경우, 상속은 하나만 가능하고 final로 구현할 경우 상속 불가한 문제
//    인터페이스로 구현할 경우 더욱 유연해진다.
    public void startGame() {
        System.out.println("게임에 실행합니다.");
    }
}
