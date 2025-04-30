package com.example.education_spring_boot.features.location.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.education_spring_boot.features.location.models.entities.Location;

public interface LocationRepo extends JpaRepository<Location, Long> {}
