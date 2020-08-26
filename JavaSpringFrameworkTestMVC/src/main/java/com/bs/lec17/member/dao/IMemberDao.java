package com.bs.lec17.member.dao;

import java.util.Map;

import com.bs.lec17.member.Member;

public interface IMemberDao {
	Map<String, Member> memberInsert(Member member);
	Member memberSelect(Member member);
	Member memberUpdate(Member member);
	Map<String, Member> memberDelete(Member member);
	
//	** 이전 버전
	
//	void memberInsert(String memId, String memPw, String memMail, String memPhone1, String memPhone2, String memPhone3);
//	Member memberSelect(String memId, String memPw);
//	void memberUpdate();
//	void memberDelete();
}
