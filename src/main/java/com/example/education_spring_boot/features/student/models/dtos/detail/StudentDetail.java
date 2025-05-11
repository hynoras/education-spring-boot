package com.example.education_spring_boot.features.student.models.dtos.detail;

import java.util.List;

import com.example.education_spring_boot.features.parent.models.dtos.ParentInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {
  private PersonalInfo personal_information;
  private List<ParentInfo> parent_information;
}
