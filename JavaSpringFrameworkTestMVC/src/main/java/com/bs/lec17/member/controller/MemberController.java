package com.bs.lec17.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.lec17.member.Member;
import com.bs.lec17.member.service.MemberService;

@Controller
public class MemberController {
	
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
	
	@RequestMapping(value = "/memJoin", method = RequestMethod.POST)
	public String memJoin(Member member) {
		
		int result = service.memberRegister(member);
		
		if(result == 0) {
			return "redirect:/";
		} else {
			return "memJoinOk";
		}
	}
	
	@RequestMapping(value = "/memLogin", method = RequestMethod.POST)
	public String memLogin(Member member, HttpSession session) {
		
		Member mem = service.memberSearch(member);
		
		if(mem == null) {
			return "redirect:/";
		} else {
			session.setAttribute("member", mem);
			return "memLoginOk";
		}
	}
	
	@RequestMapping("logout")
	public String memLogout(Member member, HttpSession session) {
		
		session.invalidate();
		
		return "memLoginOk";
	}
	
	@RequestMapping("/removeForm")
	public ModelAndView removeForm(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session =  request.getSession();
		Member member = (Member) session.getAttribute("member");
		
		if(null == member) { // 로그인이 안되어 있을 경우
			mav.setViewName("redirect:/"); // 메인으로
		} else { // 로그인이 되어 있을 경우
			mav.addObject("member", member); // mvc에 object 값을 넣고
			mav.setViewName("memRemoveForm"); // 뷰를 removeForm으로 (삭제 페이지로 보여라)
		} 
		
		return mav;
	}
	
	@RequestMapping(value = "/memRemove", method = RequestMethod.POST)
	public String memRemove(Member member, HttpServletRequest request) {
		
		int result = service.memberRemove(member);
		
		if(result == 0) {
			return "redirect:/";
		} else {
			HttpSession session = request.getSession();
			session.invalidate();
		}

		return "memRemoveOk";
	}
	
	/*
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public String memModify(Model model, Member member) {
		
		Member[] members = service.memberModify(member);
		
		model.addAttribute("memBef", members[0]);
		model.addAttribute("memAft", members[1]);
		
		return "memModifyOk";
	}
	*/
	
	@RequestMapping(value = "modifyForm")
	public String modifyForm(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		
		if(null == member) { // member의 값이 없으면. 즉, 로그인이 되어 있지 않으면
			return "redirect:/"; // redirect로 메인으로 가라
		} else { // 값이 있으면. 즉, 로그인이 되어 있으면
			model.addAttribute("member", service.memberSearch(member)); // memberSearch로 값을 넣어라
			return "memModifyForm"; // modifyForm으로 이동하라 (값이 있을 때)
		}
	}
	
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public ModelAndView memModify(Member member, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Member members = service.memberModify(member);
		session.setAttribute("member", members);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memAft", members);
		
		mav.setViewName("memModifyOk");
		
		return mav;
	}

	
//	** 이전 버전
	
//	MemberService service = new MemberService(); 예전 자바 버전
//	@Autowired
//	@Resource(name="memService")
//	MemberService service;
//	
//	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
//	public String memJoin(@ModelAttribute("mem") Member member) {
//		public String memJoin(Model model, HttpServletRequest request) 기존 방법
//		HttpServletRequest request -> 사용자 요청에 대한 파라미터 받는 방법
//		최근 자주 사용하는 방법(커맨드 객체) => public String memJoin(Member member) 로 선언하면 아래 getParameter도 필요 없다 -> getter, setter을 사용하는 방법. html의 name과 같을 때 프로퍼티 사용해 가져옴
//		service.memberRegister(member.getMemId()...) 로 가져오면 된다
//		model.addAttribute() 도 필요 없어진다
		
//		public String memJoin(@ModelAttribute("mem") Member member)로 선언을 하면 memjoinOk.JSP에서 ${mem.memId}... 로 사용 가능
		
//		String memId = request.getParameter("memId");
//		String memPw = request.getParameter("memPw");
//		String memMail = request.getParameter("memMail");
//		String memPhone1 = request.getParameter("memPhone1");
//		String memPhone2 = request.getParameter("memPhone2");
//		String memPhone3 = request.getParameter("memPhone3");
		
//		service.memberRegister(member.getMemId(), member.getMemPw(), member.getMemMail(), member.getMemPhone1(), member.getMemPhone2(), member.getMemPhone3());
		
//		model.addAttribute("memId", memId);
//		model.addAttribute("memPw", memPw);
//		model.addAttribute("memMail", memMail);
//		model.addAttribute("memPhone", memPhone1 + " - " + memPhone2 + " - " + memPhone3);
		
//		return "memJoinOk";
//	}
	
//	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
//	public String memLogin(Model model, @RequestParam("memId") String memId, @RequestParam("memPw") String memPw) {
//		Model은 데이터를 view에 전달하는 역할
//		HttpServletRequest request 대신 @RequestParam("memId") String memId(받을 데이터 타입 선언) 로 대체 가능
//		위 방법으로 할 때 String memId = request.getParameter("memId"); 불필요
//		@RequestParam(value="memId", Required=true, defaultValue = "1234") required -> 필수냐 아니냐, defaultValue -> 넘어온 값이 없을 시 
		
//		String memId = request.getParameter("memId");
//		String memPw = request.getParameter("memPw");
		
//		Member member = service.memberSearch(memId, memPw);
//		
//		try {
//			model.addAttribute("memId", member.getMemId());
//			model.addAttribute("memPw", member.getMemPw());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		return "memLoginOk";
//	}
	
}