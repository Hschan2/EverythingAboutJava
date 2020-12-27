package com.bs.lec17.member.dao;

import java.util.Map;

import com.bs.lec17.member.Member;

public interface IMemberDao {
	int memberInsert(Member member);
	Member memberSelect(Member member);
	int memberUpdate(Member member);
	int memberDelete(Member member);
	
//	** 이전 버전
	
//	void memberInsert(String memId, String memPw, String memMail, String memPhone1, String memPhone2, String memPhone3);
//	Member memberSelect(String memId, String memPw);
//	void memberUpdate();
//	void memberDelete();
}
