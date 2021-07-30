package com.example.handlingformsubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreetingController {

	// thymeleaf를 자동으로 불러오다.
	// greeting으로 이동
	@GetMapping("/greeting")
	public String greetingForm(Model model) {
		model.addAttribute("greeting", new Greeting());
		return "greeting";
	}

	// 선언한 변수 (greeting)의 값 result에 전달하고 result는 값을 받아오기
	@PostMapping("/greeting")
	public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
		model.addAttribute("greeting", greeting);
		return "result";
	}

}
