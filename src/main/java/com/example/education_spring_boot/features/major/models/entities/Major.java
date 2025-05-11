package com.example.education_spring_boot.features.major.models.entities;

import com.example.education_spring_boot.features.department.models.entities.Department;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "major")
public class Major {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer majorId;

  @Column(unique = true)
  private String majorName;

  @ManyToOne
  @JoinColumn(name = "department_id", referencedColumnName = "departmentid")
  private Department department;
}
