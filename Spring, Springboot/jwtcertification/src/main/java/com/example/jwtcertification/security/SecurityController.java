package com.example.jwtcertification.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class SecurityController {

//    의존성 설정
    @Autowired
    private SecurityService securityService;

//    실제는 subject가 id, key값이 비밀번호로 가져와야 하지만 학습이기 때문에 get 방식 사용
//    Token 생성
    @GetMapping("/create/token")
    public Map<String, Object> createToken(@RequestParam(value = "subject") String subject) {
        String token = securityService.createToken(subject, (2*1000*60)); // 2분 만료 시간
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", token);
        return map;
    }

//    Subject 가져오기 (변환 전 Token 값 가져오기)
    @GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam(value = "token") String token) {
        String subject = securityService.getSubject(token);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", subject);
        return map;
    }
}
