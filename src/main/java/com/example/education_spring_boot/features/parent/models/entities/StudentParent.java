package com.example.education_spring_boot.features.parent.models.entities;

import java.time.LocalDate;

import com.example.education_spring_boot.features.parent.constants.ParentColumns;
import com.example.education_spring_boot.features.parent.constants.ParentTables;
import com.example.education_spring_boot.features.student.constants.StudentColumns;
import com.example.education_spring_boot.features.student.models.entities.Student;
import com.example.education_spring_boot.shared.constants.database.CommonColumnNames;
import com.example.education_spring_boot.shared.enums.ParentRelationshipEnum;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = ParentTables.NAME)
public class StudentParent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ParentColumns.PARENT_ID)
  private Long parentId;

  @ManyToOne
  @JoinColumn(
      name = CommonColumnNames.STUDENT_ID,
      referencedColumnName = StudentColumns.STUDENT_ID,
      nullable = false)
  private Student studentId;

  @Column(name = CommonColumnNames.FULL_NAME, length = 100, nullable = false)
  private String fullName;

  @Column(name = CommonColumnNames.BIRTH_DATE, nullable = false)
  private LocalDate birthDate;

  @Column(name = ParentColumns.NATIONALITY, length = 100, nullable = false)
  private String nationality;

  @Column(name = CommonColumnNames.PERMANENT_ADDRESS, nullable = false)
  private String permanentAddress;

  @Enumerated(EnumType.STRING)
  private ParentRelationshipEnum relationship;
}
