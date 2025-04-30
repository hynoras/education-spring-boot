package com.example.education_spring_boot.features.auth.services;

import com.example.education_spring_boot.features.auth.models.dtos.LoginRequest;
import com.example.education_spring_boot.features.auth.models.dtos.RegisterRequest;

import java.util.Map;

public interface AccountService {
    String register(RegisterRequest registerRequest);
    Map<String, String> login(LoginRequest loginRequest);
    Map<String, String> getAccountDetail();
}
