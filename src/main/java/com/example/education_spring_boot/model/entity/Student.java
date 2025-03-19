package com.example.education_spring_boot.model.entity;

import com.example.education_spring_boot.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "identity")
    private String identity;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    @Column(name = "birth_date", columnDefinition = "DATE")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "permanent_address", nullable = false)
    @ColumnDefault("'Unknown'")
    private String permanentAddress;

    @Column(name = "temporary_address")
    private String temporaryAddress;

    @Column(name = "ethnic_group", length = 100, nullable = false)
    @ColumnDefault("'Unknown'")
    private String ethnicGroup;

    @Column(name = "religion", length = 100, nullable = true)
    @ColumnDefault("'None'")
    private String religion;

    @Column(name = "citizen_id", length = 20, nullable = false, unique = true)
    @ColumnDefault("'Unknown'")
    private String citizenId;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Location province;

    @Column(name = "priority_group", length = 20, nullable = false)
    @ColumnDefault("'None'")
    private String priorityGroup;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;
}

