package com.example.jwtcertification.controller;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// 예외 처리 방법 (Exception 처리 방법만)
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

//    Jwt 만료 에러 발생 시 에러 출력
    @ExceptionHandler(value = ExpiredJwtException.class)
    public Map<String, String> handleJwtException(ExpiredJwtException e) {
        Map<String, String> map = new HashMap<>();
        map.put("ErrorMessage", e.getMessage());
        return map;
    }

    //    예외 발생 시 에러 출력
    @ExceptionHandler(value = Exception.class)
    public Map<String, String> handleException(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("ErrorMessage", e.getMessage());
        return map;
    }

//    NullPointerException 등 계속 작성하면 된다.
}
