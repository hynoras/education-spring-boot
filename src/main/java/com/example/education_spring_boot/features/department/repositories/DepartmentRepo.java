package com.example.education_spring_boot.features.department.repositories;

import com.example.education_spring_boot.features.department.models.dtos.DepartmentNameList;
import com.example.education_spring_boot.features.department.models.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    @Query(
        "SELECT new com.example.education_spring_boot.features.department.models.dtos.DepartmentNameList" +
        "(d.departmentName)" +
        " FROM Department d"
    )
    List<DepartmentNameList> findDepartmentName();
}
