package com.bs.lec17.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.lec17.member.Member;
import com.bs.lec17.member.service.MemberService;

@Controller
public class MemberController {

//	MemberService service = new MemberService(); 예전 자바 버전
//	@Autowired
	@Resource(name="memService")
	MemberService service;
	
	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public String memJoin(@ModelAttribute("mem") Member member) {
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
		
		service.memberRegister(member.getMemId(), member.getMemPw(), member.getMemMail(), member.getMemPhone1(), member.getMemPhone2(), member.getMemPhone3());
		
//		model.addAttribute("memId", memId);
//		model.addAttribute("memPw", memPw);
//		model.addAttribute("memMail", memMail);
//		model.addAttribute("memPhone", memPhone1 + " - " + memPhone2 + " - " + memPhone3);
		
		return "memJoinOk";
	}
	
	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
	public String memLogin(Model model, @RequestParam("memId") String memId, @RequestParam("memPw") String memPw) {
//		Model은 데이터를 view에 전달하는 역할
//		HttpServletRequest request 대신 @RequestParam("memId") String memId(받을 데이터 타입 선언) 로 대체 가능
//		위 방법으로 할 때 String memId = request.getParameter("memId"); 불필요
//		@RequestParam(value="memId", Required=true, defaultValue = "1234") required -> 필수냐 아니냐, defaultValue -> 넘어온 값이 없을 시 
		
//		String memId = request.getParameter("memId");
//		String memPw = request.getParameter("memPw");
		
		Member member = service.memberSearch(memId, memPw);
		
		try {
			model.addAttribute("memId", member.getMemId());
			model.addAttribute("memPw", member.getMemPw());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "memLoginOk";
	}
	
}