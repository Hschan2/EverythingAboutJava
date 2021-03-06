package com.springsecurity.security;

import com.springsecurity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 레포지토리
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
