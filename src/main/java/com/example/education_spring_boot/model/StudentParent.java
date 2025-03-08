package com.example.education_spring_boot.model;

import com.example.education_spring_boot.enums.ParentRelationshipEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_parent")
public class StudentParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private Student studentId;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "nationality", length = 100, nullable = false)
    private String nationality;

    @Column(name = "permanent_address", nullable = false)
    private String permanentAddress;

    @Enumerated(EnumType.STRING)
    private ParentRelationshipEnum relationship;
}
