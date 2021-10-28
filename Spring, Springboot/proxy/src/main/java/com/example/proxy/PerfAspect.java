package com.example.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//스프링에서 사용하며, 흩어진 코드를 하나로 뭉쳐서 프로그래밍할 수 있도록 함함
@Aspect
@Component // bean으로 등록
public class PerfAspect {

//    성능 측정 코드
    @Around("bean(gameService") // 측정을 어디다 할 것인가. gameService의 bean에다가 저장하고 아래 코드를 제공
    public void timestamp(ProceedingJoinPoint point) throws Throwable { // point => gameService의 메소드를 가리킨다.
        long before = System.currentTimeMillis();
        point.proceed(); // 아무것도 하지 않는 gameService의 startGame을 실행하는 것
        System.out.println(System.currentTimeMillis() - before);
    }
}
