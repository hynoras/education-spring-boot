package com.example.education_spring_boot.features.parent.models.entities;

import com.example.education_spring_boot.shared.enums.ParentRelationshipEnum;
import com.example.education_spring_boot.features.student.models.entities.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student_parent")
public class StudentParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "identity", nullable = false)
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
