package com.example.education_spring_boot.repository;

import com.example.education_spring_boot.dto.student.detail.PersonalInformation;
import com.example.education_spring_boot.dto.student.detail.StudentDetail;
import com.example.education_spring_boot.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    @EntityGraph(attributePaths = {"major", "major.department"})
    Page<Student> findAll(Specification<Student> spec, Pageable pageable);

    @Query(
        "SELECT new com.example.education_spring_boot.dto.student.detail.PersonalInformation" +
        "(s.identity, s.fullName, s.birthDate, s.gender, s.permanentAddress, s.temporaryAddress, s.ethnicGroup, s.religion, s.citizenId, s.priorityGroup) " +
        "FROM Student s " +
        "WHERE s.identity = :identity"
    )
    PersonalInformation findByIdentity(@Param("identity") String identity);
}
