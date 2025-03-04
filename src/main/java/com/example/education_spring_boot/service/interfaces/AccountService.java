package com.example.education_spring_boot.service.interfaces;

import com.example.education_spring_boot.dto.account.LoginRequest;
import com.example.education_spring_boot.dto.account.RegisterRequest;

import java.util.Map;

public interface AccountService {
    String register(RegisterRequest registerRequest);
    Map<String, String> login(LoginRequest loginRequest);
    Map<String, String> getAccountDetail();
}
