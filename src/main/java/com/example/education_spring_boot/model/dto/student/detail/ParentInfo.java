package com.example.education_spring_boot.model.dto.student.detail;

import com.example.education_spring_boot.enums.ParentRelationshipEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentInfo {
    private String full_name;
    private LocalDate date_of_birth;
    private String nationality;
    private String permanent_address;
    private ParentRelationshipEnum relationship;
}
