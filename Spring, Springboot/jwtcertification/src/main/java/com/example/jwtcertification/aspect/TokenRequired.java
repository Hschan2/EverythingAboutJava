package com.example.jwtcertification.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 어노테이션 생성하기
// UserController에서 사용할 메소드에 사용
@Retention(RetentionPolicy.RUNTIME) // Runtime 때 실행
@Target(ElementType.METHOD)
public @interface TokenRequired {
}
