package com.example.education_spring_boot.controller.auth;

import com.example.education_spring_boot.dto.account.CustomUserDetails;
import com.example.education_spring_boot.dto.account.LoginRequest;
import com.example.education_spring_boot.dto.account.RegisterRequest;
import com.example.education_spring_boot.service.auth.AccountServiceImpl;
import com.example.education_spring_boot.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AccountServiceImpl accountService;
    private final CookieUtil cookieUtil;

    @Autowired
    public AuthController(AccountServiceImpl accountService, CookieUtil cookieUtil) {
        this.accountService = accountService;
        this.cookieUtil = cookieUtil;
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        String response = accountService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        int EXPIRATION_TIME = 60 * 60 * 24;
        String token = accountService.login(loginRequest);
        Cookie cookie = cookieUtil.generateCookie("Authorization", token, EXPIRATION_TIME);
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-user")
    public ResponseEntity<?> getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        return ResponseEntity.ok(Map.of(
                "username", authentication.getName(),
                "roles", authentication.getAuthorities()
        ));
    }

}
