package com.example.singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Spring {
    public static void main(String[] args) {

//        ApplicationContext 안에서 유일한 인스턴스로 관리를 하는 것이기 때문에 엄연히 따지면 싱글톤과 다르다
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        String hello1 = applicationContext.getBean("hello", String.class);
        String hello2 =applicationContext.getBean("hello", String.class);

//        True. 같은 인스턴스임을 보여준다.
//        싱글톤 스코프라 불리우지만 싱글톤 패턴과 다르다
        System.out.println(hello1 == hello2);
    }
}
