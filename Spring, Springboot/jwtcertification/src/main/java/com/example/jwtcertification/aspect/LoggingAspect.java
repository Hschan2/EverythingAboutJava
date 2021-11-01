package com.example.jwtcertification.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// 애플리케이션이 서버에 올라갈 때 동시에 올라간다.
@Aspect
@Component
public class LoggingAspect {

//   Service에서 파라미터 상관없이 get 포함되면 호출
    @Before("execution(* com.example.jwtcertification.service.*.get*(..))")
    public void loggerBefore() {
        System.out.println("Get으로 시작되는 메서드 시작");
    }

    @After("execution(* com.example.jwtcertification.service.*.get*(..))")
    public void loggerAfter() {
        System.out.println("Get으로 시작되는 메서드 종료");
    }

//    Controller에서 모두 호출
    @Around("execution(* com.example.jwtcertification.UserController.*(..))")
    public Object loggerAround(ProceedingJoinPoint pjp) throws Throwable {
        long beforeTimeMillis = System.currentTimeMillis();
        System.out.println("[UserController] 실행 시작: " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        Object result = pjp.proceed(); // 기준

        long afterTimeMillis = System.currentTimeMillis() - beforeTimeMillis;
        System.out.println("[UserController] 실행 완료: " + afterTimeMillis + "mms 소요" + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());

        return result;
    }
    
}
