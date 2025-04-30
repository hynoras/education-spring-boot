package com.example.education_spring_boot.features.student.models.dtos.detail;

import com.example.education_spring_boot.features.parent.models.dtos.ParentInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {
    private PersonalInfo personal_information;
    private List<ParentInfo> parent_information;
}
