package com.example.education_spring_boot.service.admin.student;

import com.example.education_spring_boot.exception.DatabaseException;
import com.example.education_spring_boot.model.dto.DefaultResponse;
import com.example.education_spring_boot.model.dto.student_parent.ParentInfoForm;
import com.example.education_spring_boot.model.entity.StudentParent;
import com.example.education_spring_boot.repository.StudentParentRepo;
import com.example.education_spring_boot.service.interfaces.StudentParentService;
import com.example.education_spring_boot.utils.DateTimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StudentParentServiceImpl implements StudentParentService {
    private final StudentParentRepo studentParentRepo;
    private final ModelMapper modelMapper;
    private final JdbcTemplate jdbcTemplate;
    private final DateTimeUtils dateTimeUtils;

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
    public DefaultResponse updateParentInfo(String student_id, String relationship, Map<String, Object> updateColumns) {
        try {
            StringBuilder sql = new StringBuilder("UPDATE student_parent SET ");
            List<Object> params = new ArrayList<>();
            if (updateColumns.containsKey("birth_date")) {
                LocalDate localDate = dateTimeUtils.changeTimezone(updateColumns.get("birth_date").toString(), "Asia/Bangkok");
                updateColumns.put("birth_date", localDate);
            }
            updateColumns.forEach((key, value) -> {
                sql.append(key).append(" = ?, ");
                params.add(value);
            });
            sql.delete(sql.length() - 2, sql.length() - 1);
            sql.append("WHERE student_id = ? AND relationship = ?");
            params.add(student_id);
            params.add(relationship);
            jdbcTemplate.update(sql.toString(), params.toArray());
            return new DefaultResponse(new Date(), "Updated parent of student " + student_id + " successfully!", "none");
        } catch (DataAccessException e) {
            throw new DatabaseException("An error occurred while updating student personal information", e);
        }
    }

    @Override
    public DefaultResponse deleteParentInfo(String student_id) {
        studentParentRepo.deleteById(Long.valueOf(student_id));
        return new DefaultResponse(new Date(), "Deleted parent info successfully", "none");
    }
}
