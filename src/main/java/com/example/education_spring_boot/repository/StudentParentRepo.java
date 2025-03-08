package com.example.education_spring_boot.repository;

import com.example.education_spring_boot.model.StudentParent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentParentRepo extends JpaRepository<StudentParent, Long> {
}
