package com.example.education_spring_boot.dto.student;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentList {
    private Long identity;
    private String fullName;
    private String majorName;
    private String departmentName;
}
