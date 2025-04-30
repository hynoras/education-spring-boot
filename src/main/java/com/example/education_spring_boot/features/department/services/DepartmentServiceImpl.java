package com.example.education_spring_boot.features.department.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.education_spring_boot.features.department.models.dtos.DepartmentNameList;
import com.example.education_spring_boot.features.department.repositories.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService {
  private final DepartmentRepo departmentRepo;

  public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
    this.departmentRepo = departmentRepo;
  }

  @Override
  public List<DepartmentNameList> getAllDepartmentName() {
    return departmentRepo.findDepartmentName();
  }
}
