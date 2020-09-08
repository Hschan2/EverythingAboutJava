package com.bs.lec20.member.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.lec20.member.Member;
import com.bs.lec20.member.dao.MemberDao;

@Service
public class MemberService implements IMemberService {
	
	@Autowired
	MemberDao dao;
	
	@Override
	public void memberRegister(Member member) {
//		printMembers(dao.memberInsert(member));
		int result = dao.memberInsert(member);
		
		if(result == 0) System.out.println("가입 실패");
		else System.out.println("가입 성공");
	}

	@Override
	public Member memberSearch(Member member) {
		
		Member mem = dao.memberSelect(member);
		
		if(mem == null) System.out.println("로그인 실패");
		else System.out.println("로그인 성공");
		
		return mem;
	}

	@Override
	public Member memberModify(Member member) {
		
		int result = dao.memberUpdate(member);
		
		if(result == 0) {
			System.out.println("정보 수정 실패");
			return null; // 실패했으니 null을 반환
		} else {
			System.out.println("정보 수정 성공");
		}
		
		return member;
	}
	
	@Override
	public int memberRemove(Member member) {
		
		int result = dao.memberDelete(member);
		
		if(result == 0) System.out.println("회원 탈퇴 실패"); 
		else System.out.println("회원 탈퇴 성공"); // result == 1
		
		return result;
	}
	
	// 데이터베이스 쓰기 전 (로컬)
//	private void printMembers(Map<String, Member> map) {
//		
//		Set<String> keys = map.keySet();
//		Iterator<String> iterator = keys.iterator();
//		
//		while (iterator.hasNext()) {
//			String key = iterator.next();
//			Member mem = map.get(key);
//			printMember(mem);
//		}
//		
//	}
//	
//	private void printMember(Member mem) {
//		
//		System.out.print("ID:" + mem.getMemId() + "\t");
//		System.out.print("|PW:" + mem.getMemPw() + "\t");
//		System.out.print("|MAIL:" + mem.getMemMail() + "\n");
//		
//	}
}
