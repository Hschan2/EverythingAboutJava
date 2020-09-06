package com.bs.lec20.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.bs.lec20.member.Member;
import com.bs.lec20.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	// method = RequestMethod.GET => 해당 페이지로 이동했을 때
	// method = RequestMethod.POST => form의 페이지에서 submit 했을 때

	@Autowired
	MemberService service;
	
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
	
	// 회원가입
	@RequestMapping("/joinForm")
	public String joinForm(Member member) {
		return "/member/joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinReg(Member member) {
		
		service.memberRegister(member);
		
		return "/member/joinOk";
	}
	
	// 로그인
	@RequestMapping("/loginForm")
	public String loginForm(Member member) {
		return "/member/loginForm";
	}
	
	// 세션을 이용한 첫 번째 방법. 스프링 없이 JSP 사용할 때 자주 사용
	/*
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String memLogin(Member member, HttpServletRequest request) {
		
		Member mem = service.memberSearch(member);
		
		HttpSession session = request.getSession();
		session.setAttribute("member", mem);
		
		return "/member/loginOk";
	}
	*/
	
	// 세션을 이용한 두 번째 방법. 첫 번째 방법보다 한 단계정도 간단. 스프링에서 자주 사용
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String memLogin(Member member, HttpSession session) {
		
		Member mem = service.memberSearch(member);
		
		// getSession()이 필요 없이 바로 session을 set 하면 된다
		session.setAttribute("member", mem);
		
		return "/member/loginOk";
	}
	
	// Logout 첫 번째 방법
	/*
	@RequestMapping("/logout")
	public String memLogout(Member member, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "/member/logoutOk";
	}
	*/
	
	// Logout 두 번째 방법
	@RequestMapping("/logout")
	public String memLogout(Member member, HttpSession session) {
		
		session.invalidate(); // 세션 삭제. 유효성을 없애겠다
		
		return "/member/logoutOk";
	}
	
	// Modify
//	@RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
//	public ModelAndView modifyForm(HttpServletRequest request) {
//		
//		HttpSession session = request.getSession();
//		Member member = (Member) session.getAttribute("member");
//		
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("member", service.memberSearch(member));
//		
//		mav.setViewName("/member/modifyForm");
//		
//		return mav;
//	}
	@RequestMapping(value = "/modifyForm")
	public String modifyForm(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		
		if(null == member) { // member의 값이 없으면. 즉, 로그인이 되어 있지 않으면
			return "redirect:/"; // redirect로 메인으로 가라
		} else { // 값이 있으면. 즉, 로그인이 되어 있으면
			model.addAttribute("member", service.memberSearch(member)); // memberSearch로 값을 넣어라
		}
		
		return "/member/modifyForm"; // modifyForm으로 이동하라 (값이 있을 때)
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ModelAndView modify(Member member, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Member mem = service.memberModify(member);
		session.setAttribute("member", mem);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memAft", mem);
		mav.setViewName("/member/modifyOk");
		
		return mav;
	}
	
	// Remove
	@RequestMapping("/removeForm")
	public ModelAndView removeForm(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session =  request.getSession();
		Member member = (Member) session.getAttribute("member");
		
		if(null == member) { // 로그인이 안되어 있을 경우
			mav.setViewName("redirect:/"); // 메인으로
		} else { // 로그인이 되어 있을 경우
			mav.addObject("member", member); // mvc에 object 값을 넣고
			mav.setViewName("/member/removeForm"); // 뷰를 removeForm으로 (삭제 페이지로 보여라)
		} 
		
		return mav;
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String memRemove(Member member, HttpServletRequest request) {
		
		service.memberRemove(member);
		
		HttpSession session = request.getSession();
		session.invalidate(); // 세션 종료. 즉 삭제
		
		return "/member/removeOk";
	}
	
	/*
	 getId() => 세션 ID 반환
	 setAttribute() => 세션 객체 속성 저장 (추가)
	 getAttribute() => 세션 객체 저장 속성 반환
	 removeAttribute() => 세션 객체 저장 속성 제거
	 setMaxinactiveInterval() =>  세션 객체 유지시간 설정
	 getMaxinactiveInterval() => 세션 객체 유지시간 반환
	 invalidate() => 세션 객체 모든 정보 삭제
	*/
	
}
