package com.springsecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원 가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String, String> user) {
        return userRepository.save(User.builder() // userRepository에 저장
            .email(user.get("email")) // email (username) 저장
            .password(passwordEncoder.encode(user.get("password"))) // 암호화된 비밀번호 저장
            .roles(Collections.singletonList("ROLE_USER")) // 최조 가입 시에 USER로 설정
            .build()).getId();
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-mail")); // 이메일이 틀리면
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) { // 비밀번호가 틀리면
            throw new IllegalArgumentException("잘못된 비밀번호");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles()); // 로그인 실행
    }
}
