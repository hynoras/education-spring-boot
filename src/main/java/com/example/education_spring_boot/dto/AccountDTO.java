package com.example.education_spring_boot.dto;

import com.example.education_spring_boot.enumeration.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    @NotEmpty(message = "username must not empty")
    @Size(max = 20, min = 4, message = "account length must be between 4 and 20 characters")
    private String username;

    @NotEmpty(message = "password must not empty")
    @Size(min = 8, message = "password must have at least 8 characters")
    private String password;

    @NotEmpty(message = "email must not empty")
    @Email(message = "Invalid email format")
    private String email;

    private RoleEnum role;
}
