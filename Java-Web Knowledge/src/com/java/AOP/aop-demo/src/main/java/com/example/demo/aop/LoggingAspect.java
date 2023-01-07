package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private long startTime;

    @Before("execution(* com.example..check*(..))")
    public void onBeforeHandler(JoinPoint joinPoint) {
        startTime = System.nanoTime();
        System.out.println("start: " + startTime);
    }

    @After("execution(* com.example..check*(..))")
    public void onAfterHandler(JoinPoint joinPoint) {
        long endTime = System.nanoTime();
        System.out.println("elapsed time: " + (endTime - startTime));
    }

    // ResponseBody에서 check가 들어간 메소드의 패턴
    @Around("execution(* check*()) && within(com.example..*)")
    public String around(ProceedingJoinPoint pjp) throws Throwable {
        String returnValue = null;
        try {
            returnValue = (String) pjp.proceed();
        } catch(Exception e) {
            // log or re-throw the exception
        }
        // modify the return value
        returnValue = "**" + returnValue + "**";
        return returnValue;
    }
}
