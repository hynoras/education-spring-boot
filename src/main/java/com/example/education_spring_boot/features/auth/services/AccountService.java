package com.example.education_spring_boot.features.auth.services;

import java.util.Map;

import com.example.education_spring_boot.features.auth.models.dtos.LoginRequest;
import com.example.education_spring_boot.features.auth.models.dtos.RegisterRequest;

public interface AccountService {
  String register(RegisterRequest registerRequest);

  Map<String, String> login(LoginRequest loginRequest);

  Map<String, String> getAccountDetail();
}
