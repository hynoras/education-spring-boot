package com.example.education_spring_boot.features.student.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class StudentAuthentication {
  public boolean isCorrectStudent(String studentId1, String studentId2) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getAuthorities().stream()
        .anyMatch(auth -> auth.getAuthority().equals("STUDENT"))) {
      assert studentId2 != null;
      return studentId2.equals(studentId1);
    }
    return true;
  }
}
