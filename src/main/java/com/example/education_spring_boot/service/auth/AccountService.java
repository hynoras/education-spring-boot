package com.example.education_spring_boot.service.auth;

import com.example.education_spring_boot.dto.account.LoginRequest;
import com.example.education_spring_boot.dto.account.RegisterRequest;

public interface AccountService {
    public String register(RegisterRequest registerRequest);
    public String login(LoginRequest loginRequest);
}
