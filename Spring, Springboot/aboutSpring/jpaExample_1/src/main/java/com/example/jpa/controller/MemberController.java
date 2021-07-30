package com.example.jpa.controller;

import com.example.jpa.member.MemberEntity;
import com.example.jpa.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // JSON 형태로 결과값 반환
//@RequiredArgsConstructor // final 객체를 Constructor Injection (Autowired)
@RequestMapping("/users")
public class MemberController {

    private MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // POST Method로 회원 가입
    @PostMapping
    public MemberEntity signUp(@RequestParam String name, @RequestParam String email) {
        return memberRepository.save(new MemberEntity(name, email));
    }

    // 테이블 리스트 가져오기
    @GetMapping
    public Iterable<MemberEntity> MemberList() {
        return memberRepository.findAll();
    }

    // ID 값을 사용해서 테이블 값 받기
    @GetMapping(value = "/{id}")
    public Optional<MemberEntity> findOne(@PathVariable Long id) {
        return memberRepository.findById(id);
    }

    // ID로 회원 수정
    @PutMapping(value = "/{id}")
    public MemberEntity MemberUpdate(@PathVariable Long id, @RequestParam String name, @RequestParam String email) {
        Optional<MemberEntity> member = memberRepository.findById(id);
        member.get().setName(name);
        member.get().setEmail(email);
        return memberRepository.save(member.get());
    }

    // ID로 회원 삭제
    @DeleteMapping
    public void MemberDelete(@RequestParam Long id) {
        memberRepository.deleteById(id);
    }
}
