package com.example.education_spring_boot.controller.admin;

import com.example.education_spring_boot.dto.AccountDTO;
import com.example.education_spring_boot.model.Account;
import com.example.education_spring_boot.service.admin.Account.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
//        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<String> register(@Valid @RequestBody AccountDTO accountDTO) {
            String response = accountService.register(accountDTO);
            return ResponseEntity.ok(response);
        }
}
