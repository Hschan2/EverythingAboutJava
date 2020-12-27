package com.springboot.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
//@Controller
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	// 아래 Hello가 실행되기 위해서
	// @Controller => @RestController로 변경하면 된다.
//	@GetMapping("/")
//	public String Hello() {
//		return "Hello";
//	}


}
