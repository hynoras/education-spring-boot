package com.example.education_spring_boot.model.entity;

import com.example.education_spring_boot.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    private static final int USERNAME_LENGTH = 20;
    private static final int PASSWORD_LENGTH = 60;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", unique = true, nullable = false, length = USERNAME_LENGTH)
    private String username;

    @Column(name = "password", unique = false, nullable = false, length = PASSWORD_LENGTH)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleEnum role;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}