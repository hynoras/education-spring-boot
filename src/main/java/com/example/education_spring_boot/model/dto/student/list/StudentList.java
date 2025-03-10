package com.example.education_spring_boot.model.dto.student.list;

import com.example.education_spring_boot.enums.GenderEnum;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentList {
    private String identity;
    private String full_name;
    private LocalDate birth_date;
    private GenderEnum gender;
    private String major_name;
    private String department_name;
}
