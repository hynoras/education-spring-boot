package com.example.education_spring_boot.repository;

import com.example.education_spring_boot.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
