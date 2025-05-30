package com.example.education_spring_boot.features.auth.services;

import java.util.Map;

import com.example.education_spring_boot.features.auth.models.dtos.LoginRequest;
import com.example.education_spring_boot.features.auth.models.dtos.RegisterRequest;
import com.example.education_spring_boot.shared.model.DefaultResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface AccountService {
  String register(RegisterRequest registerRequest);

  DefaultResponse login(LoginRequest loginRequest, HttpServletResponse response);

  Map<String, String> getAccountDetail();
}
