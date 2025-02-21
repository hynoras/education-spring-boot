package com.example.education_spring_boot.repository;

import com.example.education_spring_boot.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepo extends JpaRepository<Major, Long> {
}
