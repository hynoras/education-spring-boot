package com.example.education_spring_boot.model.dto.student.detail;

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
