package com.example.education_spring_boot.features.auth.controllers;

import java.util.Map;

import com.example.education_spring_boot.features.auth.constants.AccountRoutes;
import com.example.education_spring_boot.shared.constants.auth.AuthConstants;
import com.example.education_spring_boot.shared.constants.controller.ControllerMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.education_spring_boot.features.auth.models.dtos.LoginRequest;
import com.example.education_spring_boot.features.auth.models.dtos.RegisterRequest;
import com.example.education_spring_boot.features.auth.services.AccountServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ControllerMappings.API_PREFIX)
public class AuthController {
  private final AccountServiceImpl accountService;

  @Autowired
  public AuthController(AccountServiceImpl accountService) {
    this.accountService = accountService;
  }

  @PostMapping(AccountRoutes.BASE + AccountRoutes.REGISTER)
  @PreAuthorize(AuthConstants.ADMIN_PREAUTHORIZE)
  public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
    String response = accountService.register(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping(AccountRoutes.BASE + AccountRoutes.LOGIN)
  public ResponseEntity<Map<String, String>> authenticate(
      @Valid @RequestBody LoginRequest loginRequest) {
    Map<String, String> response = accountService.login(loginRequest);
    return ResponseEntity.ok(response);
  }

  @GetMapping(AccountRoutes.BASE + AccountRoutes.ACCOUNT)
  public ResponseEntity<?> getUserDetails() {
    Map<String, String> response = accountService.getAccountDetail();
    return ResponseEntity.ok(response);
  }
}
