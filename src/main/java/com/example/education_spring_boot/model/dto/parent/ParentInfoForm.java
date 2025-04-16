package com.example.education_spring_boot.model.dto.parent;

import com.example.education_spring_boot.enums.ParentRelationshipEnum;
import com.example.education_spring_boot.model.entity.Student;
import com.example.education_spring_boot.utils.JSONUtils.StudentDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentInfoForm {
    @NotNull(message = "Student ID must not be null")
    @JsonDeserialize(using = StudentDeserializer.class)
    private Student student_id;

    @NotNull(message = "Parent name must not empty")
    private String full_name;

    @NotNull(message = "Parent date of birth must not empty")
    private LocalDate birth_date;

    @NotNull(message = "Parent nationality must not empty")
    private String nationality;

    @NotNull(message = "Parent permanent address must not empty")
    private String permanent_address;

    private ParentRelationshipEnum relationship;
}
