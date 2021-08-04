package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    // For example
    // 실제로는 Service를 이용해서 유저 정보 활용
    @Autowired
    AccountService accountService;

    //    AccountRepository accounts;

    // 새 계정 생성 예시
    @GetMapping("/create")
    public Account create() {
            Account account = new Account();
            account.setEmail("hongst503@hanmail.net");
            account.setPassword("password"); // 실제로는 암호화하여 저장

            return accountService.save(account);
    }

}
