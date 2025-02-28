package com.example.education_spring_boot.repository;

import com.example.education_spring_boot.dto.student.StudentList;
import com.example.education_spring_boot.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    @Query("SELECT new com.example.education_spring_boot.dto.student.StudentList(s.identity, s.fullName, s.birthDate, s.gender, m.majorName, d.departmentName) " +
            "FROM Student s " +
            "JOIN s.major m " +
            "JOIN m.department d")
    Page<StudentList> findAllStudent(Pageable pageable);
}
