package com.example.education_spring_boot.features.auth.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.education_spring_boot.features.auth.constants.AccountRoutes;
import com.example.education_spring_boot.features.auth.models.dtos.LoginRequest;
import com.example.education_spring_boot.features.auth.models.dtos.RegisterRequest;
import com.example.education_spring_boot.features.auth.services.AccountServiceImpl;
import com.example.education_spring_boot.shared.constants.auth.AuthConstants;
import com.example.education_spring_boot.shared.constants.controller.ControllerMappings;
import com.example.education_spring_boot.shared.model.DefaultResponse;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ControllerMappings.API_PREFIX)
public class AuthController {
  private final AccountServiceImpl accountService;
  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

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
  public ResponseEntity<DefaultResponse> authenticate(
      @Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
    return ResponseEntity.ok(accountService.login(loginRequest, response));
  }

  @GetMapping(AccountRoutes.BASE + AccountRoutes.ACCOUNT)
  public ResponseEntity<?> getUserDetails() {
    Map<String, String> response = accountService.getAccountDetail();
    return ResponseEntity.ok(response);
  }
}
