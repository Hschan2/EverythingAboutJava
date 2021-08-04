package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity // 스프링부트가 설정한 스프링 시큐리티 설정이 사라짐
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // 가장 중요한 설정. 어떻게 보안을 설정할 것인가
                .antMatchers("/", "/home", "/create").permitAll() // home은 모두 접근 가능, create 추가 = 반복적인 로그인 요청 방지
                .antMatchers("/admin/**").hasRole("ADMIN") // ADMIN 밑에 있는 것으로 접근하려면 ADMIN 역할을 가지고 있어야 함
                .anyRequest().authenticated() // 나머지는 인증이 필요 (로그인한 유저)
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll() // 로그인 페이지도 모두 접근 가능
                .and()
                .logout()
                .logoutSuccessUrl("/home") // 로그아웃이 완료되면 home으로 이동
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    For Test
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER") // userAdmin과 같은 유저의 권한 역할. Role 간 관계 설정 가능
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

//    For Reality

}
