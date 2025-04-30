package com.example.education_spring_boot.features.student.controllers;

import static com.example.education_spring_boot.features.student.constants.StudentAttributes.AVATAR_ATTR;
import static com.example.education_spring_boot.features.student.constants.StudentAttributes.IDENTITY_ATTR;
import static com.example.education_spring_boot.features.student.constants.StudentMapping.*;
import static com.example.education_spring_boot.shared.constants.AuthConstants.ADMIN_PREAUTHORIZE;
import static com.example.education_spring_boot.shared.constants.ControllerMappings.*;
import static com.example.education_spring_boot.shared.constants.DatabaseAttributes.GENDER;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.education_spring_boot.features.student.models.dtos.detail.IdentityMap;
import com.example.education_spring_boot.features.student.models.dtos.detail.PersonalInfoForm;
import com.example.education_spring_boot.features.student.models.dtos.detail.StudentDetail;
import com.example.education_spring_boot.features.student.models.dtos.list.StudentList;
import com.example.education_spring_boot.features.student.services.StudentServiceImpl;
import com.example.education_spring_boot.shared.model.DefaultResponse;
import com.example.education_spring_boot.shared.model.PaginatedList;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_PREFIX)
public class StudentController {

  private final StudentServiceImpl studentService;
  private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

  @Autowired
  public StudentController(StudentServiceImpl studentService) {
    this.studentService = studentService;
  }

  @GetMapping(STUDENT_MAPPING)
  @PreAuthorize(ADMIN_PREAUTHORIZE)
  public ResponseEntity<PaginatedList<StudentList>> getAllStudent(
      @RequestParam(name = CURRENT_PAGE_PATH_PARAMS, defaultValue = CURRENT_PAGE_DEFAULT_VALUE)
          Integer currentPage,
      @RequestParam(name = PAGE_SIZE_PATH_PARAMS, defaultValue = PAGE_SIZE_DEFAULT_VALUE)
          Integer pageSize,
      @RequestParam(name = SORT_BY_PATH_PARAMS, defaultValue = IDENTITY_ATTR) String sortBy,
      @RequestParam(name = SORT_ORDER_PATH_PARAMS, defaultValue = SORT_ORDER_DESC_DEFAULT_VALUE)
          String sortOrder,
      @RequestParam(name = GENDER, required = false) List<String> gender,
      @RequestParam(name = "major", required = false) List<String> major,
      @RequestParam(name = "department", required = false) List<String> department,
      @RequestParam(name = "search", required = false, defaultValue = "") String search) {
    return ResponseEntity.ok(
        studentService.getAllStudent(
            currentPage, pageSize, sortBy, sortOrder, gender, major, department, search));
  }

  @GetMapping(STUDENT_MAPPING + IDENTITY_PATH_PARAMS_MAPPING)
  @PreAuthorize(ADMIN_PREAUTHORIZE)
  public ResponseEntity<StudentDetail> getStudentDetail(
      @PathVariable(IDENTITY_ATTR) String IDENTITY_ATTR) {
    return ResponseEntity.ok(studentService.getStudentDetail(IDENTITY_ATTR));
  }

  @PostMapping(STUDENT_MAPPING)
  @PreAuthorize(ADMIN_PREAUTHORIZE)
  public ResponseEntity<DefaultResponse> addStudentPersonalInfo(
      @Valid @RequestBody PersonalInfoForm personalInfoForm) {
    return ResponseEntity.ok(studentService.addStudentPersonalInfo(personalInfoForm));
  }

  @DeleteMapping(STUDENT_MAPPING + IDENTITY_PATH_PARAMS_MAPPING)
  @PreAuthorize(ADMIN_PREAUTHORIZE)
  public ResponseEntity<DefaultResponse> deleteStudentPersonalInfo(
      @PathVariable(IDENTITY_ATTR) String IDENTITY_ATTR) {
    return ResponseEntity.ok(studentService.deleteStudentPersonalInfo(IDENTITY_ATTR));
  }

  @DeleteMapping(STUDENTS_MAPPING)
  @PreAuthorize(ADMIN_PREAUTHORIZE)
  public ResponseEntity<DefaultResponse> deleteManyStudentPersonalInfo(
      @RequestBody List<IdentityMap> identities) {
    return ResponseEntity.ok(studentService.deleteManyStudentPersonalInfo(identities));
  }

  @PutMapping(STUDENT_MAPPING + IDENTITY_PATH_PARAMS_MAPPING)
  @PreAuthorize(ADMIN_PREAUTHORIZE)
  public ResponseEntity<DefaultResponse> updateStudentPersonalInfo(
      @PathVariable(IDENTITY_ATTR) String IDENTITY_ATTR, @RequestBody Map<String, Object> request) {
    return ResponseEntity.ok(studentService.updateStudentPersonalInfo(IDENTITY_ATTR, request));
  }

  @PutMapping(STUDENT_MAPPING + AVATAR_MAPPING + IDENTITY_PATH_PARAMS_MAPPING)
  @PreAuthorize(ADMIN_PREAUTHORIZE)
  public ResponseEntity<DefaultResponse> updateStudentAVATAR_ATTR(
      @RequestParam(AVATAR_ATTR) MultipartFile AVATAR_ATTR,
      @PathVariable(IDENTITY_ATTR) String IDENTITY_ATTR)
      throws IOException {
    return ResponseEntity.ok(studentService.updateStudentAvatar(AVATAR_ATTR, IDENTITY_ATTR));
  }
}
