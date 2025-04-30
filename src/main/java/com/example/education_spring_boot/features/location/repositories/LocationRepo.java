package com.example.education_spring_boot.features.location.repositories;

import com.example.education_spring_boot.features.location.models.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Long> {
}
