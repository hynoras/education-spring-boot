package com.example.education_spring_boot.service.admin.student;

import com.example.education_spring_boot.dto.PaginatedList;
import com.example.education_spring_boot.dto.student.detail.ParentInformation;
import com.example.education_spring_boot.dto.student.detail.PersonalInformation;
import com.example.education_spring_boot.dto.student.detail.StudentDetail;
import com.example.education_spring_boot.dto.student.list.StudentList;
import com.example.education_spring_boot.model.Student;
import com.example.education_spring_boot.model.StudentParent;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final StudentParentRepo studentParentRepo;
    private final StudentSpecification studentSpecification;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo, StudentParentRepo studentParentRepo, StudentSpecification studentSpecification) {
        this.studentRepo = studentRepo;
        this.studentParentRepo = studentParentRepo;
        this.studentSpecification = studentSpecification;
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
}
