package com.example.education_spring_boot.controller.auth;

import com.example.education_spring_boot.dto.account.LoginRequest;
import com.example.education_spring_boot.dto.account.RegisterRequest;
import com.example.education_spring_boot.service.auth.AccountServiceImpl;
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
    private final AccountServiceImpl accountService;

    @Autowired
    public AuthController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        String response = accountService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        String response = accountService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
