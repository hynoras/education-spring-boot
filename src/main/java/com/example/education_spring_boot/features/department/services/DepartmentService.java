package com.example.education_spring_boot.features.department.services;

import java.util.List;

import com.example.education_spring_boot.features.department.models.dtos.DepartmentNameList;

public interface DepartmentService {
  List<DepartmentNameList> getAllDepartmentName();
}
