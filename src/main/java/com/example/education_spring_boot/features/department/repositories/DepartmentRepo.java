package com.example.education_spring_boot.features.department.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.education_spring_boot.features.department.models.dtos.DepartmentNameList;
import com.example.education_spring_boot.features.department.models.entities.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
  @Query(
      "SELECT new com.example.education_spring_boot.features.department.models.dtos.DepartmentNameList"
          + "(d.departmentName)"
          + " FROM Department d")
  List<DepartmentNameList> findDepartmentName();
}
