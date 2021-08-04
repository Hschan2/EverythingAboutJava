package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accounts;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    Autowired를 사용하지 않아도 Bean을 주입받을 수 있는 방법 (생성자)
//    private final AccountRepository accounts;
//    private final PasswordEncoder passwordEncoder;
//    public AccountService(AccountRepository accounts, PasswordEncoder passwordEncoder) {
//        this.accounts = accounts;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails 인터페이스 타입으로 변환 필요
        Account account = accounts.findByEmail(username);

//        아래의 내용을 축소하기
//        authorities => DB 정보 모두 직접 만들어야 함 (DB에 해당하는 모든 정보)
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(account.getEmail(), account.getPassword(), authorities);

//        UserDetails userDetails = new UserDetails() {
////            유저의 권한
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                List<GrantedAuthority> authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//                return authorities;
//            }
//
//            @Override
//            public String getPassword() {
//                return account.getPassword();
//            }
//
////            유일한 값이어야 한다
//            @Override
//            public String getUsername() {
//                return account.getEmail();
//            }
//
//            @Override
//            public boolean isAccountNonExpired() {
//                return true;
//            }
//
//            @Override
//            public boolean isAccountNonLocked() {
//                return true;
//            }
//
//            @Override
//            public boolean isCredentialsNonExpired() {
//                return true;
//            }
//
//            @Override
//            public boolean isEnabled() {
//                return true;
//            }
//        };
//        return userDetails;
    }

//    패스워드를 bcrypt 형식으로 암호화하여 저장 (PasswordEncoder)
    public Account save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accounts.save(account);
    }
}
