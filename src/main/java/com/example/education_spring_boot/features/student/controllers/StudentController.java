package com.example.education_spring_boot.features.student.controllers;

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

import com.example.education_spring_boot.features.auth.constants.AccountColumns;
import com.example.education_spring_boot.features.auth.constants.AccountRoutes;
import com.example.education_spring_boot.features.department.constants.DepartmentTables;
import com.example.education_spring_boot.features.major.constants.MajorTables;
import com.example.education_spring_boot.features.student.constants.StudentColumns;
import com.example.education_spring_boot.features.student.constants.StudentRoutes;
import com.example.education_spring_boot.features.student.models.dtos.detail.IdentityMap;
import com.example.education_spring_boot.features.student.models.dtos.detail.PersonalInfoForm;
import com.example.education_spring_boot.features.student.models.dtos.detail.StudentDetail;
import com.example.education_spring_boot.features.student.models.dtos.list.StudentList;
import com.example.education_spring_boot.features.student.services.StudentServiceImpl;
import com.example.education_spring_boot.shared.constants.auth.AuthConstants;
import com.example.education_spring_boot.shared.constants.controller.ControllerMappings;
import com.example.education_spring_boot.shared.constants.controller.PaginationConstants;
import com.example.education_spring_boot.shared.constants.controller.SortConstants;
import com.example.education_spring_boot.shared.constants.database.CommonColumnNames;
import com.example.education_spring_boot.shared.model.DefaultResponse;
import com.example.education_spring_boot.shared.model.PaginatedList;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ControllerMappings.API_PREFIX)
public class StudentController {

  private final StudentServiceImpl studentService;
  private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

  @Autowired
  public StudentController(StudentServiceImpl studentService) {
    this.studentService = studentService;
  }

  @GetMapping(StudentRoutes.BASE_PLURAL)
  @PreAuthorize(AuthConstants.ADMIN_PREAUTHORIZE)
  public ResponseEntity<PaginatedList<StudentList>> getAllStudent(
      @RequestParam(
              name = PaginationConstants.CURRENT_PAGE_PARAM,
              defaultValue = PaginationConstants.DEFAULT_CURRENT_PAGE)
          Integer currentPage,
      @RequestParam(
              name = PaginationConstants.PAGE_SIZE_PARAM,
              defaultValue = PaginationConstants.DEFAULT_PAGE_SIZE)
          Integer pageSize,
      @RequestParam(name = SortConstants.SORT_BY_PARAM, defaultValue = StudentColumns.IDENTITY)
          String sortBy,
      @RequestParam(name = SortConstants.SORT_ORDER_PARAM, defaultValue = SortConstants.ORDER_DESC)
          String sortOrder,
      @RequestParam(name = CommonColumnNames.GENDER, required = false) List<String> gender,
      @RequestParam(name = MajorTables.NAME, required = false) List<String> major,
      @RequestParam(name = DepartmentTables.NAME, required = false) List<String> department,
      @RequestParam(name = ControllerMappings.SEARCH_PARAM, required = false, defaultValue = "")
          String search) {
    return ResponseEntity.ok(
        studentService.getAllStudent(
            currentPage, pageSize, sortBy, sortOrder, gender, major, department, search));
  }

  @GetMapping(StudentRoutes.BASE + StudentRoutes.BY_ID)
  @PreAuthorize(AuthConstants.ADMIN_STUDENT_PREAUTHORIZE)
  public ResponseEntity<StudentDetail> getStudentDetail(
      @PathVariable(StudentColumns.IDENTITY) String identity) {
    return ResponseEntity.ok(studentService.getStudentDetail(identity));
  }

  @GetMapping(StudentRoutes.BASE + StudentRoutes.ID + AccountRoutes.BY_USERNAME)
  public ResponseEntity<String> getIdentityByUsername(
      @PathVariable(AccountColumns.USERNAME) String username) {
    return ResponseEntity.ok(studentService.getIdentityByUsername(username));
  }

  @PostMapping(StudentRoutes.BASE)
  @PreAuthorize(AuthConstants.ADMIN_PREAUTHORIZE)
  public ResponseEntity<DefaultResponse> addStudentPersonalInfo(
      @Valid @RequestBody PersonalInfoForm personalInfoForm) {
    return ResponseEntity.ok(studentService.addStudentPersonalInfo(personalInfoForm));
  }

  @DeleteMapping(StudentRoutes.BASE + StudentRoutes.BY_ID)
  @PreAuthorize(AuthConstants.ADMIN_PREAUTHORIZE)
  public ResponseEntity<DefaultResponse> deleteStudentPersonalInfo(
      @PathVariable(StudentColumns.IDENTITY) String identity) {
    return ResponseEntity.ok(studentService.deleteStudentPersonalInfo(identity));
  }

  @DeleteMapping(StudentRoutes.BASE_PLURAL)
  @PreAuthorize(AuthConstants.ADMIN_PREAUTHORIZE)
  public ResponseEntity<DefaultResponse> deleteManyStudentPersonalInfo(
      @RequestBody List<IdentityMap> identities) {
    return ResponseEntity.ok(studentService.deleteManyStudentPersonalInfo(identities));
  }

  @PutMapping(StudentRoutes.BASE + StudentRoutes.BY_ID)
  @PreAuthorize(AuthConstants.ADMIN_PREAUTHORIZE)
  public ResponseEntity<DefaultResponse> updateStudentPersonalInfo(
      @PathVariable(StudentColumns.IDENTITY) String identity,
      @RequestBody Map<String, Object> request) {
    return ResponseEntity.ok(studentService.updateStudentPersonalInfo(identity, request));
  }

  @PutMapping(StudentRoutes.BASE + StudentRoutes.AVATAR_BY_ID)
  @PreAuthorize(AuthConstants.ADMIN_PREAUTHORIZE)
  public ResponseEntity<DefaultResponse> updateStudentAVATAR_ATTR(
      @RequestParam(StudentColumns.AVATAR) MultipartFile avatar,
      @PathVariable(StudentColumns.IDENTITY) String identity)
      throws IOException {
    return ResponseEntity.ok(studentService.updateStudentAvatar(avatar, identity));
  }
}
