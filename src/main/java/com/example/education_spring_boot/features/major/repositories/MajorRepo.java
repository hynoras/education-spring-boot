package com.example.education_spring_boot.features.major.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.education_spring_boot.features.major.models.dtos.MajorNameList;
import com.example.education_spring_boot.features.major.models.entities.Major;

@Repository
public interface MajorRepo extends JpaRepository<Major, Long> {
  @Query(
      "SELECT new com.example.education_spring_boot.features.major.models.dtos.MajorNameList"
          + "(m.majorName)"
          + " FROM Major m")
  List<MajorNameList> findMajorName();
}
