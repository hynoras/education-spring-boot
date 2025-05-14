package com.example.education_spring_boot.features.student.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class StudentAuthentication {
  public boolean isCorrectStudent(String identity1, String identity2) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getAuthorities().stream()
        .anyMatch(auth -> auth.getAuthority().equals("STUDENT"))) {
      assert identity2 != null;
      return identity2.equals(identity1);
    }
    return true;
  }
}
