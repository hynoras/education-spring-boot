package com.example.education_spring_boot.features.student.services;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.education_spring_boot.shared.constants.controller.SortConstants;
import com.example.education_spring_boot.shared.constants.datetime.DateTimeConstants;
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
import com.example.education_spring_boot.features.parent.models.dtos.ParentInfo;
import com.example.education_spring_boot.features.parent.repositories.StudentParentRepo;
import com.example.education_spring_boot.features.student.constants.StudentColumns;
import com.example.education_spring_boot.features.student.models.dtos.detail.IdentityMap;
import com.example.education_spring_boot.features.student.models.dtos.detail.PersonalInfo;
import com.example.education_spring_boot.features.student.models.dtos.detail.PersonalInfoForm;
import com.example.education_spring_boot.features.student.models.dtos.detail.StudentDetail;
import com.example.education_spring_boot.features.student.models.dtos.list.StudentList;
import com.example.education_spring_boot.features.student.models.entities.Student;
import com.example.education_spring_boot.features.student.repositories.StudentRepo;
import com.example.education_spring_boot.shared.exception.DatabaseException;
import com.example.education_spring_boot.shared.model.DefaultResponse;
import com.example.education_spring_boot.shared.model.PaginatedList;
import com.example.education_spring_boot.shared.specs.StudentSpecification;

@Service
public class StudentServiceImpl implements StudentService {
  private final StudentRepo studentRepo;
  private final StudentParentRepo studentParentRepo;
  private final StudentSpecification studentSpecification;
  private final JdbcTemplate jdbcTemplate;
  private final Cloudinary cloudinary;
  private final ModelMapper modelMapper;
  private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

  @Autowired
  public StudentServiceImpl(
      StudentRepo studentRepo,
      StudentParentRepo studentParentRepo,
      StudentSpecification studentSpecification,
      JdbcTemplate jdbcTemplate,
      Cloudinary cloudinary,
      ModelMapper modelMapper) {
    this.studentRepo = studentRepo;
    this.studentParentRepo = studentParentRepo;
    this.studentSpecification = studentSpecification;
    this.jdbcTemplate = jdbcTemplate;
    this.cloudinary = cloudinary;
    this.modelMapper = modelMapper;
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
          sortOrder.equalsIgnoreCase(SortConstants.ORDER_DESC) ? Sort.Direction.DESC : Sort.Direction.ASC;
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
                        builder.like(root.get(StudentColumns.IDENTITY), "%" + search + "%"),
                        builder.like(root.get("fullName"), "%" + search + "%")));
      }
      pagedResult = studentRepo.findAll(filters, paging);
      return new PaginatedList<>(
          pagedResult.getContent().stream()
              .map(
                  student ->
                      new StudentList(
                          student.getIdentity(),
                          student.getFullName(),
                          student.getBirthDate(),
                          student.getGender(),
                          student.getMajor().getMajorName(),
                          student.getMajor().getDepartment().getDepartmentName()))
              .toList(),
          pagedResult.getTotalElements(),
          pagedResult.getTotalPages(),
          pagedResult.isLast());
    } catch (DataAccessException e) {
      throw new DatabaseException("An error occurred while fetching student list", e);
    }
  }

  @Override
  public StudentDetail getStudentDetail(String identity) {
    try {
      PersonalInfo personalInformation = studentRepo.findByIdentity(identity);
      List<ParentInfo> parentInformation = studentParentRepo.findByIdentity(identity);
      return new StudentDetail(personalInformation, parentInformation);
    } catch (DataAccessException e) {
      throw new DatabaseException("An error occurred while fetching student details", e);
    }
  }

  @Override
  public DefaultResponse addStudentPersonalInfo(PersonalInfoForm personalInfoForm) {
    try {
      Student student = modelMapper.map(personalInfoForm, Student.class);
      studentRepo.save(student);
      return new DefaultResponse(new Date(), "Add student personal info successfully", "none");
    } catch (Exception e) {
      throw new DatabaseException("An error occurred while adding student personal information", e);
    }
  }

  @Override
  public DefaultResponse deleteStudentPersonalInfo(String identity) {
    studentRepo.deleteById(identity);
    return new DefaultResponse(
        new Date(), "Delete student with ID " + identity + " successfully", "none");
  }

  @Override
  public DefaultResponse deleteManyStudentPersonalInfo(List<IdentityMap> identities) {
    try {
      AtomicInteger count = new AtomicInteger();
      identities.forEach(
          identityMap -> {
            studentRepo.deleteById(identityMap.getIdentity());
            count.getAndIncrement();
          });
      return new DefaultResponse(new Date(), "Delete " + count + " student successfully!", "none");
    } catch (DataAccessException e) {
      throw new DatabaseException(
          "An error occurred while deleting student personal information", e);
    }
  }

  @Override
  @Transactional
  public DefaultResponse updateStudentPersonalInfo(
      String identity, Map<String, Object> updateColumns) {
    try {
      StringBuilder sql = new StringBuilder("UPDATE student SET ");
      List<Object> params = new ArrayList<>();
      if (updateColumns.containsKey("birth_date")) {
        LocalDate localDate =
            Instant.parse(updateColumns.get("birth_date").toString())
                .atZone(DateTimeConstants.BANGKOK_ZONE)
                .toLocalDate();
        updateColumns.put("birth_date", localDate);
      }
      updateColumns.forEach(
          (key, value) -> {
            sql.append(key).append(" = ?, ");
            params.add(value);
          });
      sql.delete(sql.length() - 2, sql.length() - 1);
      sql.append("WHERE identity = ?");
      params.add(identity);
      jdbcTemplate.update(sql.toString(), params.toArray());
      return new DefaultResponse(
          new Date(), "Updated student " + identity + " successfully!", "none");
    } catch (DataAccessException e) {
      throw new DatabaseException(
          "An error occurred while updating student personal information", e);
    }
  }

  @Override
  @Transactional
  public DefaultResponse updateStudentAvatar(MultipartFile avatar, String identity)
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
      jdbcTemplate.update("UPDATE student SET avatar = ? WHERE identity = ?", avatarURL, identity);
      return new DefaultResponse(
          new Date(), "Updated student " + identity + " avatar successfully!", "none");
    } catch (DataAccessException e) {
      throw new DatabaseException("An error occurred when uploading avatar", e);
    }
  }
}
