package com.example.education_spring_boot.dto.student.detail;

import com.example.education_spring_boot.enums.GenderEnum;
import com.example.education_spring_boot.model.StudentParent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {
    private PersonalInformation personal_information;
    private List<ParentInformation> parent_information;
}
