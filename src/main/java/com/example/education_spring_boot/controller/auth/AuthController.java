package com.example.education_spring_boot.controller.auth;

import com.example.education_spring_boot.dto.account.RegisterRequest;
import com.example.education_spring_boot.service.auth.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AccountService accountService;

        @PostMapping("/register")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
            String response = accountService.register(request);
            return ResponseEntity.ok(response);
        }
}
