package com.example.education_spring_boot.features.parent.services;

import com.example.education_spring_boot.shared.exception.DatabaseException;
import com.example.education_spring_boot.shared.model.dto.DefaultResponse;
import com.example.education_spring_boot.features.parent.models.entities.StudentParent;
import com.example.education_spring_boot.features.parent.repositories.StudentParentRepo;
import com.example.education_spring_boot.shared.utils.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class StudentParentServiceImpl implements StudentParentService {
    private final StudentParentRepo studentParentRepo;
    private final JdbcTemplate jdbcTemplate;
    private final DateTimeUtils dateTimeUtils;
    private static final Logger logger = LoggerFactory.getLogger(StudentParentServiceImpl.class);

    @Autowired
    public StudentParentServiceImpl(
        StudentParentRepo studentParentRepo,
        JdbcTemplate jdbcTemplate,
        DateTimeUtils dateTimeUtils
    ) {
        this.studentParentRepo = studentParentRepo;
        this.jdbcTemplate = jdbcTemplate;
        this.dateTimeUtils = dateTimeUtils;
    }

    @Override
    public void addParentInfo(Map<String, Object> addColumns) {
        StringBuilder sql = new StringBuilder("INSERT INTO student_parent (");
        List<Object> params = new ArrayList<>();
        if (addColumns.containsKey("birth_date")) {
            LocalDate localDate = dateTimeUtils.changeTimezone(addColumns.get("birth_date").toString(), "Asia/Bangkok");
            addColumns.put("birth_date", localDate);
        }
        addColumns.forEach((key, value) -> {
            if(!Objects.equals(key, "id")) {
                sql.append(key).append(", ");
                params.add(value);
            }
        });
        sql.delete(sql.length() - 2, sql.length()).append(") VALUES (");
        addColumns.forEach((key, value) -> {
            if(!Objects.equals(key, "id")) {
                sql.append("?, ");
            }
        });
        sql.delete(sql.length() - 2, sql.length()).append(")");
        logger.debug("SQL: {}", sql);
        logger.debug("params: {}", params);
        jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public void updateParentInfo(Map<String, Object> updateColumns) {
        StringBuilder sql = new StringBuilder("UPDATE student_parent SET ");
        List<Object> params = new ArrayList<>();
        if (updateColumns.containsKey("birth_date")) {
            LocalDate localDate = dateTimeUtils.changeTimezone(updateColumns.get("birth_date").toString(), "Asia/Bangkok");
            updateColumns.put("birth_date", localDate);
        }
        updateColumns.forEach((key, value) -> {
            if(!Objects.equals(key, "id")) {
                sql.append(key).append(" = ?, ");
                params.add(value);
            }
        });
        sql.delete(sql.length() - 2, sql.length() - 1);
        sql.append("WHERE id = ?");
        params.add(updateColumns.get("id"));
        jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public DefaultResponse upsertParentInfo(List<Map<String, Object>> upsertColumns) {
        try {
            upsertColumns.forEach(upsertColumn -> {
                Number id = (Number) upsertColumn.get("id");
                Optional<StudentParent> parent = studentParentRepo.findById(id.longValue());
                if (parent.isEmpty()) {
                    addParentInfo(upsertColumn);
                }
                else {
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
            ids.forEach(id -> {
                studentParentRepo.deleteById(id.get("id"));
            });
            return new DefaultResponse(new Date(), "Deleted parent info successfully", "none");
        } catch (DataAccessException e) {
            throw new DatabaseException("An error occurred while deleting student personal information", e);
        }
    }
}
