package com.bs.lec20.mall.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.lec20.mall.Mall;

@Controller
@RequestMapping("/mall")
public class MallController {

	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@ModelAttribute("serverTime")
	public String getServerTime(Locale locale) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		return dateFormat.format(date);
	}
	
	// 생성된 쿠키를 가지고 와서 사용
	@RequestMapping("/index")
	public String mallIndex(Mall mall, 
			@CookieValue(value="gender", required=false) Cookie genderCookie, 
			HttpServletRequest request) {
		
		// @CookieValue(value="gender", required=false) => Default 값이 없어도 리셉션이 발생하지 않는다
		
		if(genderCookie != null) // 쿠키가 있으면
			mall.setGender(genderCookie.getValue());
		
		return "/mall/index";
	}
	
	// 생성된 쿠키를 가지고 사용
	@RequestMapping("/main")
	public String mallMain(Mall mall, HttpServletResponse response){
		
		Cookie genderCookie = new Cookie("gender", mall.getGender());
		
		if(mall.isCookieDel()) { // 쿠키가 삭제 되었으면 쿠키 사용 끝
			genderCookie.setMaxAge(0);
			mall.setGender(null);
		} else {
			genderCookie.setMaxAge(60*60*24*30); // 60초 * 60분 * 24시간 * 30일 => 한 달
		}
		response.addCookie(genderCookie);
		
		return "/mall/main";
	}
	
}