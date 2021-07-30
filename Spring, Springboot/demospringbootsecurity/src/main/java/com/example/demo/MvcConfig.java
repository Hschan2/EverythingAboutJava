package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home"); // home 요청이 오면 home의 view를 보여줌
        registry.addViewController("/").setViewName("home"); // 기본 페이지를 home으로 보여줌
        registry.addViewController("/hello").setViewName("hello"); // hello 요청이 오면 hello의 view를 보여줌
        registry.addViewController("/login").setViewName("login"); // 위와 동일한 요청과 출력
    }
}
