package com.example.education_spring_boot.service.auth;

import com.example.education_spring_boot.exception.DatabaseException;
import com.example.education_spring_boot.model.dto.account.LoginRequest;
import com.example.education_spring_boot.model.dto.account.RegisterRequest;
import com.example.education_spring_boot.model.entity.Account;
import com.example.education_spring_boot.repository.AccountRepo;
import com.example.education_spring_boot.service.interfaces.AccountService;
import com.example.education_spring_boot.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AccountServiceImpl(AccountRepo accountRepo,
                              PasswordEncoder passwordEncoder,
                              AuthenticationManager authenticationManager,
                              JwtUtils jwtUtils) {
        this.accountRepo = accountRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
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
        } catch (DataAccessException e) {
            throw new DatabaseException("An error occurred when authorizing: ", e);
        }
    }

    public Map<String, String> login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            String token = "";
            if (authentication.isAuthenticated()) {
                token = jwtUtils.generateToken(loginRequest.getUsername());
            }
            return Map.of("token", token);
        } catch (DataAccessException e) {
            throw new DatabaseException("An error occurred when authenticating: ", e);
        }
    }

    @Override
    public Map<String, String> getAccountDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            Optional<String> roleOptional = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst();
            String role = roleOptional.orElse("USER");
            return Map.of(
            "username", authentication.getName(),
            "role", role
            );
        } catch (DataAccessException e) {
            if (!authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
                throw new BadCredentialsException("Username or password is incorrect!");
            }
            else {
                throw new DatabaseException("An error occurred when fetching account details: ", e);
            }
        }
    }

}
