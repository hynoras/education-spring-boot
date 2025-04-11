package com.example.education_spring_boot.repository;

import com.example.education_spring_boot.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Long> {
}
