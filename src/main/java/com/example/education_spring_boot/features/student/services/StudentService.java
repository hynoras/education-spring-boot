package com.example.education_spring_boot.features.student.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.example.education_spring_boot.features.student.models.dtos.detail.PersonalInfoForm;
import com.example.education_spring_boot.features.student.models.dtos.detail.StudentDetail;
import com.example.education_spring_boot.features.student.models.dtos.detail.StudentIdMap;
import com.example.education_spring_boot.features.student.models.dtos.list.StudentList;
import com.example.education_spring_boot.shared.model.DefaultResponse;
import com.example.education_spring_boot.shared.model.PaginatedList;

public interface StudentService {
  public PaginatedList<StudentList> getAllStudent(
      Integer currentPage,
      Integer pageSize,
      String sortBy,
      String sortOrder,
      List<String> gender,
      List<String> major,
      List<String> department,
      String search);

  public StudentDetail getStudentDetail(String studentId);

  public String getStudentIdByUsername(String username);

  public DefaultResponse addStudentPersonalInfo(PersonalInfoForm personalInfoForm);

  public DefaultResponse updateStudentPersonalInfo(
      String studentId, Map<String, Object> updateColumns);

  public DefaultResponse updateStudentAvatar(MultipartFile avatar, String studentId)
      throws IOException;

  public DefaultResponse deleteStudentPersonalInfo(String studentId);

  public DefaultResponse deleteManyStudentPersonalInfo(List<StudentIdMap> studentIds);
}
