package com.example.education_spring_boot.specs;

import com.example.education_spring_boot.model.Student;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentSpecification {
    public Specification<Student> filterByGender(List<String> genders) {
        return (root, query, builder) -> genders == null || genders.isEmpty()
            ? builder.conjunction()
            : root.get("gender").in(genders);
    }
    public Specification<Student> filterByMajor(List<String> majors) {
        return (root, query, builder) -> majors == null || majors.isEmpty()
            ? builder.conjunction()
            : root.get("major").in(majors);
    }
    public Specification<Student> filterByDepartment(List<String> departments) {
        return (root, query, builder) -> departments == null || departments.isEmpty()
            ? builder.conjunction()
            : root.get("department").in(departments);
        }
}
