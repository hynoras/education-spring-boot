package com.example.education_spring_boot.service.auth;

import com.example.education_spring_boot.dto.account.LoginRequest;
import com.example.education_spring_boot.dto.account.RegisterRequest;
import com.example.education_spring_boot.model.Account;
import com.example.education_spring_boot.repository.AccountRepo;
import com.example.education_spring_boot.service.interfaces.AccountService;
import com.example.education_spring_boot.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AccountServiceImpl(AccountRepo accountRepo,
                              PasswordEncoder passwordEncoder,
                              AuthenticationManager authenticationManager,
                              JwtUtil jwtUtil) {
        this.accountRepo = accountRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String register(RegisterRequest registerRequest) {
        try {
            Account account = new Account();
            account.setUsername(registerRequest.getUsername());
            account.setEmail(registerRequest.getEmail());
            account.setRole(registerRequest.getRole());
            account.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            accountRepo.save(account);
            return "Successfully created account!";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Authorization failed: " + e.getMessage());
        }
    }

    public String login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            if (authentication.isAuthenticated()) {
                return jwtUtil.generateToken(loginRequest.getUsername());
            } else {
                throw new UsernameNotFoundException("Username is invalid!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
    }
}
