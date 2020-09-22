package com.bs.lec20.member.service;

import com.bs.lec20.member.Member;

public interface IMemberService {
	int memberRegister(Member member);
	Member memberSearch(Member member);
	Member memberModify(Member member);
	int memberRemove(Member member);
}
