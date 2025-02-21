package com.example.education_spring_boot.repository;

import com.example.education_spring_boot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}
