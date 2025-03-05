package com.example.education_spring_boot.service.admin.student;

import com.example.education_spring_boot.dto.student.department.DepartmentNameList;
import com.example.education_spring_boot.repository.DepartmentRepo;
import com.example.education_spring_boot.service.interfaces.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

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
