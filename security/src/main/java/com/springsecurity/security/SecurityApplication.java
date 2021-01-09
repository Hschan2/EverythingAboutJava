package com.springsecurity.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);

		// 애플리케이션을 실행하고(POSTMAN 등 활용) 나서 /join으로 들어간다.
		// 그리고 POST 요청을 보내서 회원가입을 하고 나서 /login으로 POST 요청을 보내면
		// 토큰을 생성해서 반환을 한다. (Object 형식으로)

		// 이렇게 받은 토큰을 header에 "X-AUTH-TOKEN"에 담아서 제한된 리소스에 대한 요청을 하게 되면
		// 토큰을 통해 권한을 확인하고 리소스를 반환한다.

	}

}
