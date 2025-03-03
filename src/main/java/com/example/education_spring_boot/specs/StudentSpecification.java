package com.example.education_spring_boot.specs;

import com.example.education_spring_boot.model.Student;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class StudentSpecification {
    public Specification<Student> filterStudent(String filterBy, String filterValue) {
        return (root, query, builder) -> {
            return switch (filterBy) {
                case "gender" -> builder.equal(root.get("gender"), filterValue);
                case "major" -> builder.equal(root.join("major").get("majorName"), filterValue);
                case "department" ->
                        builder.equal(root.join("major").join("department").get("departmentName"), filterValue);
                default -> builder.conjunction();
            };
        };
    }
}
