package com.example.education_spring_boot.model;

import com.example.education_spring_boot.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    @Column(unique = true)
    private Long identity;

    private String fullName;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Location province;

    private String priorityGroup;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;
}

