package com.example.education_spring_boot.dto;

import com.example.education_spring_boot.enumeration.RoleEnum;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String username;
    private String password;
    private String email;
    private RoleEnum role;
}
