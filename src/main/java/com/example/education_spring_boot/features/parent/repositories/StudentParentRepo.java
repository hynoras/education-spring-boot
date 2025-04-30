package com.example.education_spring_boot.features.parent.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.education_spring_boot.features.parent.models.dtos.ParentInfo;
import com.example.education_spring_boot.features.parent.models.entities.StudentParent;

@Repository
public interface StudentParentRepo extends JpaRepository<StudentParent, Long> {
  @Query(
      "SELECT new com.example.education_spring_boot.features.parent.models.dtos.ParentInfo"
          + "(sp.id, sp.fullName, sp.birthDate, sp.nationality, sp.permanentAddress, sp.relationship) "
          + "FROM StudentParent sp "
          + "WHERE sp.studentId.identity = :identity")
  List<ParentInfo> findByIdentity(@Param("identity") String identity);
}
