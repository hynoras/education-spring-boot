package com.example.education_spring_boot.features.parent.services;

import java.time.LocalDate;
import java.util.*;

import com.example.education_spring_boot.features.parent.constants.ParentColumns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.education_spring_boot.features.parent.models.entities.StudentParent;
import com.example.education_spring_boot.features.parent.repositories.StudentParentRepo;
import com.example.education_spring_boot.shared.constants.database.CommonColumnNames;
import com.example.education_spring_boot.shared.constants.datetime.DateTimeConstants;
import com.example.education_spring_boot.shared.constants.generic.JsonKeys;
import com.example.education_spring_boot.shared.exception.DatabaseException;
import com.example.education_spring_boot.shared.model.DefaultResponse;
import com.example.education_spring_boot.shared.utils.DateTimeUtils;

@Service
public class StudentParentServiceImpl implements StudentParentService {
  private final StudentParentRepo studentParentRepo;
  private final JdbcTemplate jdbcTemplate;
  private final DateTimeUtils dateTimeUtils;
  private static final Logger logger = LoggerFactory.getLogger(StudentParentServiceImpl.class);

  @Autowired
  public StudentParentServiceImpl(
      StudentParentRepo studentParentRepo, JdbcTemplate jdbcTemplate, DateTimeUtils dateTimeUtils) {
    this.studentParentRepo = studentParentRepo;
    this.jdbcTemplate = jdbcTemplate;
    this.dateTimeUtils = dateTimeUtils;
  }

  @Override
  public void addParentInfo(Map<String, Object> addColumns) {
    try {
      StringBuilder sql = new StringBuilder("INSERT INTO student_parent (");
      List<Object> params = new ArrayList<>();
      addColumns.remove(JsonKeys.ID);
        addColumns.remove(ParentColumns.PARENT_ID);
      if (addColumns.containsKey(CommonColumnNames.BIRTH_DATE)) {
        LocalDate localDate =
            dateTimeUtils.changeTimezone(
                addColumns.get(CommonColumnNames.BIRTH_DATE).toString(),
                DateTimeConstants.BANGKOK_ZONE);
        addColumns.put(CommonColumnNames.BIRTH_DATE, localDate);
      }
      addColumns.forEach(
          (key, value) -> {
            if (!Objects.equals(key, JsonKeys.ID)) {
              sql.append(key).append(", ");
              params.add(value);
            }
          });
      sql.delete(sql.length() - 2, sql.length()).append(") VALUES (");
      addColumns.forEach(
          (key, value) -> {
            if (!Objects.equals(key, JsonKeys.ID)) {
              sql.append("?, ");
            }
          });
      sql.delete(sql.length() - 2, sql.length()).append(")");
      jdbcTemplate.update(sql.toString(), params.toArray());
    } catch (DataAccessException e) {
      throw new DatabaseException("An error occurred while inserting parent information", e);
    }
  }

  @Override
  public void updateParentInfo(Map<String, Object> updateColumns) {
    StringBuilder sql = new StringBuilder("UPDATE student_parent SET ");
    List<Object> params = new ArrayList<>();
    if (updateColumns.containsKey(CommonColumnNames.BIRTH_DATE)) {
      LocalDate localDate =
          dateTimeUtils.changeTimezone(
              updateColumns.get(CommonColumnNames.BIRTH_DATE).toString(),
              DateTimeConstants.BANGKOK_ZONE);
      updateColumns.put(CommonColumnNames.BIRTH_DATE, localDate);
    }
    updateColumns.forEach(
        (key, value) -> {
          if (!Objects.equals(key, JsonKeys.ID)) {
            sql.append(key).append(" = ?, ");
            params.add(value);
          }
        });
    sql.delete(sql.length() - 2, sql.length() - 1);
    sql.append("WHERE parent_id = ?");
    params.add(updateColumns.get(ParentColumns.PARENT_ID));
    jdbcTemplate.update(sql.toString(), params.toArray());
  }

  @Override
  public DefaultResponse upsertParentInfo(List<Map<String, Object>> upsertColumns) {
    try {
      upsertColumns.forEach(
          upsertColumn -> {
            Number parent_id = (Number) upsertColumn.get(ParentColumns.PARENT_ID);
            Optional<StudentParent> parent = studentParentRepo.findById(parent_id.longValue());
            if (parent.isEmpty()) {
              addParentInfo(upsertColumn);
            } else {
              updateParentInfo(upsertColumn);
            }
          });
      return new DefaultResponse(new Date(), "Upsert parent info successfully", "none");
    } catch (RuntimeException e) {
      throw new RuntimeException("An error occurred while upserting parent information", e);
    }
  }

  @Override
  public DefaultResponse deleteParentInfo(List<Map<String, Long>> ids) {
    try {
      ids.forEach(
          id -> {
            studentParentRepo.deleteById(id.get(JsonKeys.ID));
          });
      return new DefaultResponse(new Date(), "Deleted parent info successfully", "none");
    } catch (DataAccessException e) {
      throw new DatabaseException(
          "An error occurred while deleting student personal information", e);
    }
  }
}
