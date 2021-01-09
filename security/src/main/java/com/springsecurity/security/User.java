package com.springsecurity.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter // 자동으로 getter
@NoArgsConstructor // Parameter가 없는 생성자를 생성 (기본 생성자)
@AllArgsConstructor // 필드 값을 모두 포함한 생성자를 자동 생성
@Builder // 모델 객체 생성 시, Builder를 자동으로 추가
@Entity // 테이블과 매핑
public class User implements UserDetails {
    // User 정보 담기
    // SpringSecurity는 UserDetails 객체를 통해 권한 정보를 관리
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 30, nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // 사용할 username
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
