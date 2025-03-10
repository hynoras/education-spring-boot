package com.example.education_spring_boot.specs;

import com.example.education_spring_boot.model.entity.Major;
import com.example.education_spring_boot.model.entity.Student;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
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
        return (root, query, builder) -> {
            if (majors == null || majors.isEmpty()) return builder.conjunction();
            Join<Student, Major> majorJoin = root.join("major", JoinType.LEFT);
            return majorJoin.get("majorName").in(majors);
        };
    }
    public Specification<Student> filterByDepartment(List<String> departments) {
        return (root, query, builder) -> {
            if (departments == null || departments.isEmpty()) return builder.conjunction();
            Join<Student, Major> majorJoin = root.join("major", JoinType.LEFT).join("department", JoinType.LEFT);
            return majorJoin.get("departmentName").in(departments);
        };
    }
}
