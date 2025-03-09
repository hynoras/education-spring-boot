package com.example.education_spring_boot.repository;

import com.example.education_spring_boot.dto.student.detail.ParentInformation;
import com.example.education_spring_boot.model.StudentParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentParentRepo extends JpaRepository<StudentParent, Long> {
    @Query(
        "SELECT new com.example.education_spring_boot.dto.student.detail.ParentInformation" +
        "(sp.fullName, sp.birthDate, sp.nationality, sp.permanentAddress, sp.relationship) " +
        "FROM StudentParent sp " +
        "WHERE sp.studentId.identity = :identity"
    )
    List<ParentInformation> findByIdentity(@Param("identity") String identity);
}
