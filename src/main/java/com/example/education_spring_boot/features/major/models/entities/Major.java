package com.example.education_spring_boot.features.major.models.entities;

import com.example.education_spring_boot.features.department.constants.DepartmentColumns;
import com.example.education_spring_boot.features.department.models.entities.Department;

import com.example.education_spring_boot.features.major.constants.MajorColumns;
import com.example.education_spring_boot.features.major.constants.MajorTables;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = MajorTables.NAME)
public class Major {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = MajorColumns.MAJOR_ID)
  private Integer majorId;

  @Column(name = MajorColumns.MAJOR_NAME, unique = true)
  private String majorName;

  @ManyToOne
  @JoinColumn(name = DepartmentColumns.DEPARTMENT_ID, referencedColumnName = DepartmentColumns.DEPARTMENT_ID)
  private Department department;
}

