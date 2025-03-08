package com.example.education_spring_boot.dto.student.detail;

import com.example.education_spring_boot.enums.ParentRelationshipEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParentInformation {
    private String fullName;
    private LocalDate birthDate;
    private String nationality;
    private String permanentAddress;
    private ParentRelationshipEnum relationship;
}
