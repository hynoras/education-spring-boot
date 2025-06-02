package com.example.education_spring_boot.features.auth.utils;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class AccountUtils {
  public static Optional<String> getCurrentUserRole() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth == null
        ? Optional.empty()
        : auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst();
  }

  public static String getCurrentUsername() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth.getName();
  }

  public static boolean hasRole(String expectedRole) {
    return getCurrentUserRole().map(role -> role.equalsIgnoreCase(expectedRole)).orElse(false);
  }

  public static String getCurrentUserRoleOrDefault(String defaultRole) {
    return getCurrentUserRole().orElse(defaultRole);
  }
}
