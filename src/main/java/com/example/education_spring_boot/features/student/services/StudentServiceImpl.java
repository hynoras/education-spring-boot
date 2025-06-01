package com.example.education_spring_boot.features.student.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.education_spring_boot.features.auth.utils.AccountUtils;
import com.example.education_spring_boot.features.parent.models.dtos.ParentInfo;
import com.example.education_spring_boot.features.parent.repositories.StudentParentRepo;
import com.example.education_spring_boot.features.student.constants.StudentColumns;
import com.example.education_spring_boot.features.student.constants.StudentEntityFields;
import com.example.education_spring_boot.features.student.constants.StudentTables;
import com.example.education_spring_boot.features.student.models.dtos.detail.PersonalInfo;
import com.example.education_spring_boot.features.student.models.dtos.detail.PersonalInfoForm;
import com.example.education_spring_boot.features.student.models.dtos.detail.StudentDetail;
import com.example.education_spring_boot.features.student.models.dtos.detail.StudentIdMap;
import com.example.education_spring_boot.features.student.models.dtos.list.StudentList;
import com.example.education_spring_boot.features.student.models.entities.Student;
import com.example.education_spring_boot.features.student.repositories.StudentRepo;
import com.example.education_spring_boot.shared.constants.controller.SortConstants;
import com.example.education_spring_boot.shared.constants.database.CommonColumnNames;
import com.example.education_spring_boot.shared.constants.datetime.DateTimeConstants;
import com.example.education_spring_boot.shared.exception.AccessDeniedException;
import com.example.education_spring_boot.shared.exception.DatabaseException;
import com.example.education_spring_boot.shared.model.DefaultResponse;
import com.example.education_spring_boot.shared.model.PaginatedList;
import com.example.education_spring_boot.shared.specs.StudentSpecification;
import com.example.education_spring_boot.shared.utils.DateTimeUtils;
import com.example.education_spring_boot.shared.utils.SqlUtils;

@Service
public class StudentServiceImpl implements StudentService {
  private final StudentRepo studentRepo;
  private final StudentParentRepo studentParentRepo;
  private final StudentSpecification studentSpecification;
  private final JdbcTemplate jdbcTemplate;
  private final Cloudinary cloudinary;
  private final ModelMapper modelMapper;
  private final DateTimeUtils dateTimeUtils;
  private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

  @Autowired
  public StudentServiceImpl(
      StudentRepo studentRepo,
      StudentParentRepo studentParentRepo,
      StudentSpecification studentSpecification,
      JdbcTemplate jdbcTemplate,
      Cloudinary cloudinary,
      ModelMapper modelMapper,
      DateTimeUtils dateTimeUtils) {
    this.studentRepo = studentRepo;
    this.studentParentRepo = studentParentRepo;
    this.studentSpecification = studentSpecification;
    this.jdbcTemplate = jdbcTemplate;
    this.cloudinary = cloudinary;
    this.modelMapper = modelMapper;
    this.dateTimeUtils = dateTimeUtils;
  }

  @Override
  public PaginatedList<StudentList> getAllStudent(
      Integer currentPage,
      Integer pageSize,
      String sortBy,
      String sortOrder,
      List<String> gender,
      List<String> major,
      List<String> department,
      String search) {
    try {
      Sort.Direction direction =
          sortOrder.equalsIgnoreCase(SortConstants.ORDER_DESC)
              ? Sort.Direction.DESC
              : Sort.Direction.ASC;
      Pageable paging = PageRequest.of(currentPage, pageSize, Sort.by(direction, sortBy));
      Specification<Student> filters =
          Specification.where(studentSpecification.filterByGender(gender))
              .and(studentSpecification.filterByMajor(major))
              .and(studentSpecification.filterByDepartment(department));
      Page<Student> pagedResult;
      if (search != null) {
        filters =
            filters.and(
                (root, query, builder) ->
                    builder.or(
                        builder.like(root.get(StudentEntityFields.STUDENT_ID), "%" + search + "%"),
                        builder.like(root.get(StudentEntityFields.FULL_NAME), "%" + search + "%")));
      }
      pagedResult = studentRepo.findAll(filters, paging);
      List<StudentList> studentLists =
          pagedResult.getContent().stream()
              .map(student -> modelMapper.map(student, StudentList.class))
              .toList();
      return new PaginatedList<>(
          studentLists,
          pagedResult.getTotalElements(),
          pagedResult.getTotalPages(),
          pagedResult.isLast());
    } catch (DataAccessException e) {
      throw new DatabaseException("An error occurred while fetching student list", e);
    }
  }

  @Override
  public StudentDetail getStudentDetail(String studentId) {
    try {
      if (AccountUtils.hasRole("STUDENT")) {
        if (!Objects.equals(studentId, getStudentIdByUsername(AccountUtils.getCurrentUsername()))) {
          throw new AccessDeniedException("Access denied");
        }
      }
      PersonalInfo personalInformation = studentRepo.findByStudentId(studentId);
      List<ParentInfo> parentInformation = studentParentRepo.findByStudentId(studentId);
      return new StudentDetail(personalInformation, parentInformation);
    } catch (DataAccessException e) {
      throw new DatabaseException("An error occurred while fetching student details", e);
    }
  }

  public String getStudentIdByUsername(String username) {
    try {
      return jdbcTemplate.queryForObject(
          "SELECT s.student_id FROM student s JOIN account a ON s.account_id = a.id WHERE a.username = ?",
          String.class,
          username);
    } catch (DataAccessException e) {
      throw new DatabaseException("An error occurred while fetching student studentId", e);
    }
  }

  @Override
  public DefaultResponse addStudentPersonalInfo(PersonalInfoForm personalInfoForm) {
    try {
      Student student = modelMapper.map(personalInfoForm, Student.class);
      studentRepo.save(student);
      return new DefaultResponse(new Date(), "Add student personal info successfully", "none");
    } catch (DataAccessException e) {
      throw new DatabaseException("An error occurred while adding student personal information", e);
    }
  }

  @Override
  public DefaultResponse deleteStudentPersonalInfo(String studentId) {
    studentRepo.deleteById(studentId);
    return new DefaultResponse(
        new Date(), "Delete student with ID " + studentId + " successfully", "none");
  }

  @Override
  public DefaultResponse deleteManyStudentPersonalInfo(List<StudentIdMap> studentIds) {
    try {
      List<String> ids = studentIds.stream().map(StudentIdMap::getStudent_id).toList();
      studentRepo.deleteAllByIdInBatch(ids);
      return new DefaultResponse(
          new Date(), "Delete " + ids.size() + " student successfully!", "none");
    } catch (DataAccessException e) {
      throw new DatabaseException(
          "An error occurred while deleting student personal information", e);
    }
  }

  @Override
  @Transactional
  public DefaultResponse updateStudentPersonalInfo(
      String studentId, Map<String, Object> updateColumns) {
    try {
      if (updateColumns.containsKey(CommonColumnNames.BIRTH_DATE)) {
        LocalDate localDate =
            dateTimeUtils.changeTimezone(
                updateColumns.get(CommonColumnNames.BIRTH_DATE).toString(),
                DateTimeConstants.BANGKOK_ZONE);
        updateColumns.put(CommonColumnNames.BIRTH_DATE, localDate);
      }
      String sql =
          SqlUtils.buildDynamicUpdate(
              StudentTables.NAME, updateColumns.keySet(), StudentColumns.STUDENT_ID);
      List<Object> params = new ArrayList<>(updateColumns.values());
      params.add(studentId);
      jdbcTemplate.update(sql, params.toArray());
      return new DefaultResponse(
          new Date(), "Updated student " + studentId + " successfully!", "none");
    } catch (DataAccessException e) {
      throw new DatabaseException(
          "An error occurred while updating student personal information", e);
    }
  }

  @Override
  @Transactional
  public DefaultResponse updateStudentAvatar(MultipartFile avatar, String studentId)
      throws IOException {
    try {
      Map result =
          cloudinary
              .uploader()
              .upload(
                  avatar.getBytes(),
                  ObjectUtils.asMap(
                      "folder", "student-avatar",
                      "public_id", avatar.getOriginalFilename(),
                      "unique_filename", "true"));
      String avatarURL = (String) result.get("url");
      jdbcTemplate.update(
          "UPDATE student SET avatar = ? WHERE student_id = ?", avatarURL, studentId);
      return new DefaultResponse(
          new Date(), "Updated student " + studentId + " avatar successfully!", "none");
    } catch (DataAccessException e) {
      throw new DatabaseException("An error occurred when uploading avatar", e);
    }
  }
}
