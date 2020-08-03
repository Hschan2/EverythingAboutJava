package com.mvc.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // Controller 클래스이다
public class Login {
	
//	login의 요청이 들어오면
//	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	@RequestMapping("/login") // 위의 방식을 간단하게. "/"이 하나면 value 생략 가능
//	login 메소드가 실행이 되고
	public String login(Model model) {
		
//		model에 데이터 추가하기
		model.addAttribute("loginKey", "loginValue");
		
//		view는 login.jsp로 실행이 되라
		return "login";
	}
}
