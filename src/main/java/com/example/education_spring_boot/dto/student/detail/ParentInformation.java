package com.example.education_spring_boot.dto.student.detail;

import com.example.education_spring_boot.enums.ParentRelationshipEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentInformation {
    private String full_name;
    private LocalDate birth_date;
    private String nationality;
    private String permanent_address;
    private ParentRelationshipEnum relationship;
}
