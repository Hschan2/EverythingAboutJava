package com.bs.lec17.member.service;

import com.bs.lec17.member.Member;

public interface IMemberService {
	
	int memberRegister(Member member);
	Member memberSearch(Member member);
	Member memberModify(Member member);
	int memberRemove(Member member);
	
//	** 이전 버전
	
//	void memberRegister(String memId, String memPw, String memMail, String memPhone1, String memPhone2, String memPhone3);
//	Member memberSearch(String memId, String memPw);
//	void memberModify();
//	void memberRemove();
}
