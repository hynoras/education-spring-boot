package com.example.education_spring_boot.model;

import com.example.education_spring_boot.enumeration.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Size(max = 20, message = "account must not exceed 20 characters")
    @Column(name = "username", unique = true, nullable = false, length = 20)
    private String username;

    @Column(name = "password", unique = false, nullable = false, length = 60)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleEnum role;  // STUDENT, ADMIN, FINANCE

    @Column(name = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();
}