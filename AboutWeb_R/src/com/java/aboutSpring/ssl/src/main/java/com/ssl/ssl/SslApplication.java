package com.ssl.ssl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SslApplication {

	// SSL을 적용 전과 후를 비교하기 위한 Mapping
	// SSL을 적용하는 것은 application.properties에서 설정
	@GetMapping("/")
	public String hello() {
		return "Hello";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SslApplication.class, args);
	}

}
