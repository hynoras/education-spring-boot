package com.example.education_spring_boot.repository;

import com.example.education_spring_boot.model.dto.major.MajorNameList;
import com.example.education_spring_boot.model.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepo extends JpaRepository<Major, Long> {
    @Query(
        "SELECT new com.example.education_spring_boot.model.dto.major.MajorNameList" +
        "(m.majorName)" +
        " FROM Major m"
    )
    List<MajorNameList> findMajorName();
}
