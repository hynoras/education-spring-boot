package com.example.education_spring_boot.features.auth.services;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.education_spring_boot.features.auth.constants.AccountColumns;
import com.example.education_spring_boot.features.auth.models.dtos.LoginRequest;
import com.example.education_spring_boot.features.auth.models.dtos.RegisterRequest;
import com.example.education_spring_boot.features.auth.models.entities.Account;
import com.example.education_spring_boot.features.auth.repositories.AccountRepo;
import com.example.education_spring_boot.shared.constants.datetime.DateTimeConstants;
import com.example.education_spring_boot.shared.exception.DatabaseException;
import com.example.education_spring_boot.shared.exception.JwtExpiredException;
import com.example.education_spring_boot.shared.model.DefaultResponse;
import com.example.education_spring_boot.shared.utils.CookieUtils;
import com.example.education_spring_boot.shared.utils.JwtUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AccountServiceImpl implements AccountService {
  private final AccountRepo accountRepo;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final CookieUtils cookieUtils;
  private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

  @Autowired
  public AccountServiceImpl(
      AccountRepo accountRepo,
      PasswordEncoder passwordEncoder,
      AuthenticationManager authenticationManager,
      JwtUtils jwtUtils,
      CookieUtils cookieUtils) {
    this.accountRepo = accountRepo;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
    this.cookieUtils = cookieUtils;
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

  public DefaultResponse login(LoginRequest loginRequest, HttpServletResponse response) {
    try {
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginRequest.getUsername(), loginRequest.getPassword()));
      if (authentication.isAuthenticated()) {
        String token = jwtUtils.generateToken(loginRequest.getUsername());
        Cookie cookie =
            cookieUtils.generateCookie(
                "Authorization", token, DateTimeConstants.ONE_HOUR_IN_SECOND);
        response.addCookie(cookie);
      }
      return new DefaultResponse(new Date(), "Logged in successfully", "");
    } catch (DataAccessException e) {
      throw new DatabaseException("An error occurred when authenticating: ", e);
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("Username or password is incorrect!");
    }
  }

  @Override
  public Map<String, String> getAccountDetail() {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Optional<String> roleOptional =
          authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst();
      String role = roleOptional.orElse("USER");
      if (!authentication.isAuthenticated()
          || "anonymousUser".equals(authentication.getPrincipal())) {
        throw new JwtExpiredException("Jwt is expired!");
      }
      return Map.of(AccountColumns.USERNAME, authentication.getName(), AccountColumns.ROLE, role);
    } catch (DataAccessException e) {
      throw new DatabaseException("An error occurred when fetching account details: ", e);
    } catch (AuthenticationCredentialsNotFoundException e) {
      throw new AuthenticationCredentialsNotFoundException("Username or password is incorrect!");
    }
  }
}
