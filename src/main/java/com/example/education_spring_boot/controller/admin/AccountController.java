package com.example.education_spring_boot.controller.admin;

import com.example.education_spring_boot.dto.AccountDTO;
import com.example.education_spring_boot.model.Account;
import com.example.education_spring_boot.service.admin.Account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody AccountDTO accountDTO) {
        Account account = accountService.register(accountDTO);
        return ResponseEntity.ok(account);
    }
}
