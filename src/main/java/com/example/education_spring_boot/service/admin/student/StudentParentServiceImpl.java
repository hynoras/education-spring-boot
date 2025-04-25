package com.example.education_spring_boot.service.admin.student;

import com.example.education_spring_boot.exception.DatabaseException;
import com.example.education_spring_boot.model.dto.DefaultResponse;
import com.example.education_spring_boot.model.dto.parent.ParentInfoForm;
import com.example.education_spring_boot.model.entity.StudentParent;
import com.example.education_spring_boot.repository.StudentParentRepo;
import com.example.education_spring_boot.service.interfaces.StudentParentService;
import com.example.education_spring_boot.utils.DateTimeUtils;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;
    private final JdbcTemplate jdbcTemplate;
    private final DateTimeUtils dateTimeUtils;
    private static final Logger logger = LoggerFactory.getLogger(StudentParentServiceImpl.class);

    @Autowired
    public StudentParentServiceImpl(
        StudentParentRepo studentParentRepo,
        ModelMapper modelMapper,
        JdbcTemplate jdbcTemplate,
        DateTimeUtils dateTimeUtils
    ) {
        this.studentParentRepo = studentParentRepo;
        this.modelMapper = modelMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.dateTimeUtils = dateTimeUtils;
    }

    @Override
    public DefaultResponse addParentInfo(List<ParentInfoForm> parentInfoForm) {
        parentInfoForm.forEach(form -> {
            StudentParent studentParent = modelMapper.map(form, StudentParent.class);
            studentParentRepo.save(studentParent);
        });
        return new DefaultResponse(new Date(), "Inserted parent info successfully", "none");
    }

    @Override
    public DefaultResponse updateParentInfo(List<Map<String, Object>> updateColumns) {
        try {
            updateColumns.forEach((columns) -> {
                StringBuilder sql = new StringBuilder("UPDATE student_parent SET ");
                List<Object> params = new ArrayList<>();
                    if (columns.containsKey("birth_date")) {
                        LocalDate localDate = dateTimeUtils.changeTimezone(columns.get("birth_date").toString(), "Asia/Bangkok");
                        columns.put("birth_date", localDate);
                    }
                    columns.forEach((key, value) -> {
                        if(!Objects.equals(key, "id")) {
                            sql.append(key).append(" = ?, ");
                            params.add(value);
                        }
                    });
                    sql.delete(sql.length() - 2, sql.length() - 1);
                    sql.append("WHERE id = ?");
                    params.add(columns.get("id"));
                    jdbcTemplate.update(sql.toString(), params.toArray());
            });
            return new DefaultResponse(new Date(), "Updated parent of student successfully!", "none");
        } catch (DataAccessException e) {
            throw new DatabaseException("An error occurred while updating student personal information", e);
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
            throw new DatabaseException("An error occurred while updating student personal information", e);
        }
    }
}
