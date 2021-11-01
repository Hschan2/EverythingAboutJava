package com.example.jwtcertification.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

// Token 생성
@Service
public class SecurityService {

    private static final String SECRET_KEY = "asdfqfdsaddjfqkjdfifqdknfankdkfsdjakjds";

//    로그인 서비스 보낼 때 동시에 보낸다.
    public String createToken(String subject, long expTime) {
        if (expTime <= 0) {
            throw new RuntimeException("만료 시간이 0보다 커야 한다.");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);

//        Key 생성
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                    .setSubject(subject) // id
                    .signWith(signingKey, signatureAlgorithm)
                    .setExpiration(new Date(System.currentTimeMillis() + expTime)) // 만료 시간
                    .compact();
    }
    
//    Subject 꺼내오기
//    Token 검증하는 메서드 생성하고 Boolean으로 return한 것을 사용하여 Token을 검증하는 메서드를 호출하여 사용
    public String getSubject(String token) {
//        claim 생성
        Claims claims = Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .build()
                    .parseClaimsJws(token) // Token 풀기
                    .getBody();

        return claims.getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
    }
}
