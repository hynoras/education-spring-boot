package com.example.education_spring_boot.service.interfaces;

import com.example.education_spring_boot.dto.account.LoginRequest;
import com.example.education_spring_boot.dto.account.RegisterRequest;

import java.util.Map;

public interface AccountService {
    public String register(RegisterRequest registerRequest);
    public Map<String,String> login(LoginRequest loginRequest);
    public Map<String,String> getAccountDetail();
}
