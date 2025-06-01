package com.example.education_spring_boot.features.student.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.education_spring_boot.features.department.constants.DepartmentTables;
import com.example.education_spring_boot.features.major.constants.MajorTables;
import com.example.education_spring_boot.features.student.constants.StudentColumns;
import com.example.education_spring_boot.features.student.models.dtos.detail.PersonalInfo;
import com.example.education_spring_boot.features.student.models.entities.Student;

@Repository
public interface StudentRepo
    extends JpaRepository<Student, String>, JpaSpecificationExecutor<Student> {
  @EntityGraph(attributePaths = {MajorTables.NAME, MajorTables.NAME + "." + DepartmentTables.NAME})
  Page<Student> findAll(Specification<Student> spec, Pageable pageable);

  @Query(
      "SELECT new com.example.education_spring_boot.features.student.models.dtos.detail.PersonalInfo"
          + "(s.studentId, s.fullName, s.birthDate, s.gender, s.permanentAddress, s.temporaryAddress, s.ethnicGroup, s.religion, s.citizenId, s.avatar) "
          + "FROM Student s "
          + "WHERE s.studentId = :student_id")
  PersonalInfo findByStudentId(@Param(StudentColumns.STUDENT_ID) String studentId);
}
