package com.bs.lec17.member.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bs.lec17.member.Member;
import com.bs.lec17.member.dao.MemberDao;

@Service
//@Service("memService")
//@Component
//@Component("memService")
//@Repository
//@Repository("memService")
public class MemberService implements IMemberService {

	@Autowired
	MemberDao dao;
	
	@Override
	public int memberRegister(Member member) {
		
		int result = dao.memberInsert(member);
		
		if(result == 0) {
			System.out.println("가입 실패");
			return 0;
		}
		else {
			System.out.println("가입 성공");
			return 1;
		}
	}

	@Override
	public Member memberSearch(Member member) {
		
		Member mem = dao.memberSelect(member);
		
		if(mem == null) {
			System.out.println("로그인 실패");
		}
		else {
			System.out.println("로그인 성공");
		}
		
		return mem;
	}

	@Override
	public Member memberModify(Member member) {
		
		int result = dao.memberUpdate(member);
		
		if(result == 0) {
			System.out.println("정보 수정 실패");
			return null;
		} else {
			System.out.println("정보 수정 성공");
		}
		
		return member;
	}

	@Override
	public int memberRemove(Member member) {

		int result = dao.memberDelete(member);
		
		if(result == 0) {
			System.out.println("회원 탈퇴 실패"); 
		}
		else {
			System.out.println("회원 탈퇴 성공"); // result == 1
		}
		
		return result;
	}
	
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
	
//	private void printMember(Member mem) {
//		
//		System.out.print("ID:" + mem.getMemId() + "\t");
//		System.out.print("|PW:" + mem.getMemPw() + "\t");
//		System.out.print("|MAIL:" + mem.getMemMail() + "\t");
//		
//		List<MemPhone> memPhones = mem.getMemPhones();
//		for(MemPhone memPhone : memPhones) {
//			System.out.print("|PHONE:" + memPhone.getMemPhone1() + " - " + 
//											   memPhone.getMemPhone2() + " - " + 
//											   memPhone.getMemPhone3() + "\t");
//		}
//		
//		System.out.print("|AGE:" + mem.getMemAge() + "\t");
//		System.out.print("|ADULT:" + mem.isMemAdult() + "\t");
//		System.out.print("|GENDER:" + mem.getMemGender() + "\t");
//		System.out.print("|FAVORITE SPORTS:" + Arrays.toString(mem.getMemFSports()) + "\n");
//		
//	}
	
//	** 이전 버전
	
//	@Autowired
//	MemberDao dao;
//	
//	@Override
//	public void memberRegister(String memId, String memPw, String memMail,
//			String memPhone1, String memPhone2, String memPhone3) {
//		System.out.println("memberRegister()");
//		System.out.println("memId : " + memId);
//		System.out.println("memPw : " + memPw);
//		System.out.println("memMail : " + memMail);
//		System.out.println("memPhone : " + memPhone1 + " - " + memPhone2 + " - " + memPhone3);
//		
//		dao.memberInsert(memId, memPw, memMail, memPhone1, memPhone2, memPhone3);
//	}
//
//	@Override
//	public Member memberSearch(String memId, String memPw) {
//		System.out.println("memberSearch()");
//		System.out.println("memId : " + memId);
//		System.out.println("memPw : " + memPw);
//		
//		Member member = dao.memberSelect(memId, memPw);
//		
//		return member;
//	}
//
//	@Override
//	public void memberModify() {
//		
//	}
//
//	@Override
//	public void memberRemove() {
//		
//	}

}