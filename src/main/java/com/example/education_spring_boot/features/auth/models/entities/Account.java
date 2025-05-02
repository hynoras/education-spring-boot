package com.example.education_spring_boot.features.auth.models.entities;

import java.time.LocalDateTime;

import com.example.education_spring_boot.features.auth.constants.AccountTables;
import com.example.education_spring_boot.shared.enums.RoleEnum;

import jakarta.persistence.*;
import lombok.*;

import com.example.education_spring_boot.features.auth.constants.AccountColumns;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = AccountTables.NAME)
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = AccountColumns.USERNAME, unique = true, nullable = false, length = AccountColumns.USERNAME_LENGTH)
  private String username;

  @Column(name = AccountColumns.PASSWORD, unique = false, nullable = false, length = AccountColumns.PASSWORD_LENGTH)
  private String password;

  @Column(name = AccountColumns.EMAIL, unique = true, nullable = false)
  private String email;

  @Enumerated(EnumType.STRING)
  @Column(name = AccountColumns.ROLE, nullable = false)
  private RoleEnum role;

  @Column(name = AccountColumns.CREATED_AT)
  private LocalDateTime createdAt = LocalDateTime.now();
}
