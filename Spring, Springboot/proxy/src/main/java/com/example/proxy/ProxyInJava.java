package com.example.proxy;

import com.example.proxy.proxys.GameService;
// 프록시 인스턴스를 동적으로 사용 (reflectProxy)
import java.lang.reflect.Proxy;

public class ProxyInJava {
    public static void main(String[] args) {
        ProxyInJava proxyInJava = new ProxyInJava();
        proxyInJava.dynamicProxy();
    }

    private void dynamicProxy() {
        GameService gameServiceProxy = getGameServiceProxy(new DefaultGameService());
        gameServiceProxy.startGame();
    }

//    target은 DefaultGameService를 사용
//    아래처럼 작성할 경우, 프록시 인스턴스를 동적으로 만들어짐, Runtime
    private GameService getGameServiceProxy(GameService target) {
        return (GameService) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{GameService.class}, (proxy, method, args) -> { // invocationHandler()
                    System.out.println("Hello dynamic Proxy"); // 아무것도 하지 않을 때는 삭제. 즉 이 코드가 없으면 invoke의 tartget의 기존 메서드를 실행
                    method.invoke(target, args); // args = 메소드에 넘겨주는 파라미터들
                    System.out.println("Bye dynamic Proxy"); // 아무것도 하지 않을 때는 삭제
                    return null;
                });
    }
}
