package com.example.education_spring_boot.features.auth.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

  @NotEmpty(message = "username must not be empty")
  @Size(min = 4, max = 20, message = "username length must be between 4 and 20 characters")
  private String username;

  @NotEmpty(message = "password must not be empty")
  @Size(min = 8, message = "password must have at least 8 characters")
  private String password;
}
