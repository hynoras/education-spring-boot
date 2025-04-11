package com.example.education_spring_boot.repository;

import com.example.education_spring_boot.model.dto.student.detail.ParentInfo;
import com.example.education_spring_boot.model.entity.StudentParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentParentRepo extends JpaRepository<StudentParent, Long> {
    @Query(
        "SELECT new com.example.education_spring_boot.model.dto.student.detail.ParentInfo" +
        "(sp.fullName, sp.birthDate, sp.nationality, sp.permanentAddress, sp.relationship) " +
        "FROM StudentParent sp " +
        "WHERE sp.studentId.identity = :identity"
    )
    List<ParentInfo> findByIdentity(@Param("identity") String identity);
}
