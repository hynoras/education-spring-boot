package com.example.education_spring_boot.model;

import com.example.education_spring_boot.enums.GenderEnum;
import com.example.education_spring_boot.enums.PriorityGroupEnum;
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

    @Column(name = "identity", nullable = false, unique = true)
    private String identity;

    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "permanent_address", nullable = false)
    private String permanentAddress;

    @Column(name = "temporary_address")
    private String temporaryAddress;

    @Column(name = "ethnic_group", length = 100, nullable = false)
    private String ethnicGroup;

    @Column(name = "religion", length = 100, nullable = true)
    private String religion;

    @Column(name = "citizen_id", length = 20, nullable = false, unique = true)
    private String citizenId;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Location province;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "priority_group")
    private String priorityGroup;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;
}

