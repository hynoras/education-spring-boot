package com.example.education_spring_boot.features.department.models.entities;

import com.example.education_spring_boot.features.department.constants.DepartmentColumns;
import com.example.education_spring_boot.features.department.constants.DepartmentTables;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = DepartmentTables.NAME)
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = DepartmentColumns.DEPARTMENT_ID)
  private Integer departmentId;

  @Column(name = DepartmentColumns.DEPARTMENT_NAME, unique = true)
  private String departmentName;
}
