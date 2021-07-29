package com.springboot.start.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {

    @GetMapping("/")
    public String Hello() {
        return "Hello";
    }

    // localhost:8080/h 주소로 이동하면 출력
    @GetMapping("/h")
    public String secondHello() {
        return "Hello";
    }
}
