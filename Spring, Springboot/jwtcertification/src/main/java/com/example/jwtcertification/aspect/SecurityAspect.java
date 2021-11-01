package com.example.jwtcertification.aspect;

import com.example.jwtcertification.security.SecurityService;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SecurityAspect {

    @Autowired
    SecurityService securityService;

    @Before("@annotation(tokenRequired)")
    public void authenticateWithToken(TokenRequired tokenRequired) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

//        Token 체크
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            throw new IllegalArgumentException("Token is Empty");
        }

        if (securityService.getClaims(token) == null || securityService.getSubject(token) == null) {
            throw new IllegalArgumentException("Token Error. Claims or Subject are Null.");
       }

//        Subject 기반 자체 인증 로직
    }
}
