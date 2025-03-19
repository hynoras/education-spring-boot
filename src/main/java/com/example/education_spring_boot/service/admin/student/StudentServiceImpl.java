package com.example.education_spring_boot.service.admin.student;

import com.example.education_spring_boot.model.dto.PaginatedList;
import com.example.education_spring_boot.model.dto.student.detail.ParentInformation;
import com.example.education_spring_boot.model.dto.student.detail.PersonalInformation;
import com.example.education_spring_boot.model.dto.student.detail.StudentDetail;
import com.example.education_spring_boot.model.dto.student.list.StudentList;
import com.example.education_spring_boot.model.entity.Student;
import com.example.education_spring_boot.repository.StudentParentRepo;
import com.example.education_spring_boot.repository.StudentRepo;
import com.example.education_spring_boot.service.interfaces.StudentService;
import com.example.education_spring_boot.specs.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final StudentParentRepo studentParentRepo;
    private final StudentSpecification studentSpecification;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo, StudentParentRepo studentParentRepo, StudentSpecification studentSpecification, JdbcTemplate jdbcTemplate) {
        this.studentRepo = studentRepo;
        this.studentParentRepo = studentParentRepo;
        this.studentSpecification = studentSpecification;
        this.jdbcTemplate = jdbcTemplate;
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
        String search
    ) {
        try {
            Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable paging = PageRequest.of(currentPage, pageSize, Sort.by(direction, sortBy));
            Specification<Student> filters = Specification.where(studentSpecification.filterByGender(gender))
                .and(studentSpecification.filterByMajor(major))
                .and(studentSpecification.filterByDepartment(department));
            Page<Student> pagedResult;
            if(search != null) {
                filters = filters.and((root, query, builder) ->
                    builder.or(
                        builder.like(root.get("identity"), "%" + search + "%"),
                        builder.like(root.get("fullName"), "%" + search + "%")
                    )
                );
            }
            pagedResult = studentRepo.findAll(filters, paging);
            return new PaginatedList<>(
                pagedResult.getContent().stream().map(student -> new StudentList(
                    student.getIdentity(),
                    student.getFullName(),
                    student.getBirthDate(),
                    student.getGender(),
                    student.getMajor().getMajorName(),
                    student.getMajor().getDepartment().getDepartmentName()
                )).toList(),
                pagedResult.getTotalElements(),
                pagedResult.getTotalPages(),
                pagedResult.isLast()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch student list", e);
        }
    }

    @Override
    public StudentDetail getStudentDetail(String identity) {
        PersonalInformation personalInformation = studentRepo.findByIdentity(identity);
        List<ParentInformation> parentInformation = studentParentRepo.findByIdentity(identity);
        return new StudentDetail(
            personalInformation,
            parentInformation
        );
    }

    @Override
    @Transactional
    public String updateStudentDetail(String identity, Map<String, Object> updateColumns) {
        try {
            StringBuilder sql = new StringBuilder("UPDATE student SET ");
            List<Object> params = new ArrayList<>();
            if (updateColumns.containsKey("birth_date")) {
                System.out.println("birth_date BEFORE: " + updateColumns.get("birth_date"));
                LocalDate localDate = Instant.parse(updateColumns.get("birth_date").toString())
                                                .atZone(ZoneId.of("Asia/Bangkok"))
                                                .toLocalDate();
                updateColumns.put("birth_date", localDate);
                System.out.println("birth_date AFTER: " + updateColumns.get("birth_date"));
            }
            updateColumns.forEach((key, value) -> {
                sql.append(key).append(" = ?, ");
                params.add(value);
            });
            sql.delete(sql.length() - 2, sql.length() - 1);
            sql.append("WHERE identity = ?");
            params.add(identity);
            System.out.println("updateColumns: " + updateColumns);
            System.out.println("SQL QUERY: " + sql);
            jdbcTemplate.update(sql.toString(), params.toArray());
            return "Updated student " + identity + " successfully!";
        } catch (Exception e) {
            throw new RuntimeException("Failed to update student personal information: ", e);
        }
    }
}
