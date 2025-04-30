package com.example.education_spring_boot.features.student.models.entities;

import com.example.education_spring_boot.shared.enums.GenderEnum;
import com.example.education_spring_boot.features.auth.models.entities.Account;
import com.example.education_spring_boot.features.location.models.entities.Location;
import com.example.education_spring_boot.features.major.models.entities.Major;
import com.example.education_spring_boot.features.parent.models.entities.StudentParent;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

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
    @JoinColumn(name = "account_id", unique = true)
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

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "provinceid")
    private Location province;

    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "majorid")
    private Major major;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "studentId")
    private List<StudentParent> parent;
}

