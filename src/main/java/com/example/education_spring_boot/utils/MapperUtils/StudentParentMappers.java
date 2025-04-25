package com.example.education_spring_boot.utils.MapperUtils;

import com.example.education_spring_boot.model.dto.parent.ParentInfoForm;
import com.example.education_spring_boot.model.entity.StudentParent;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

import static com.example.education_spring_boot.constants.MappingValues.UNKNOWN_VALUE;

@Configuration
public class StudentParentMappers {
    private final ModelMapper modelMapper;

    public StudentParentMappers(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        parentInfoMapper();
    }

    public void parentInfoMapper() {
        modelMapper.typeMap(ParentInfoForm.class, StudentParent.class).setPostConverter(ctx -> {
            StudentParent parentCtx = ctx.getDestination();
            ParentInfoForm form = ctx.getSource();
            parentCtx.setPermanentAddress(Optional.ofNullable(form.getPermanent_address()).orElse(UNKNOWN_VALUE));
            parentCtx.setNationality(Optional.ofNullable(form.getNationality()).orElse(UNKNOWN_VALUE));
            return parentCtx;
        }).addMappings(mapper -> {
            mapper.map(ParentInfoForm::getStudent_id, StudentParent::setStudentId);
            mapper.map(ParentInfoForm::getFull_name, StudentParent::setFullName);
            mapper.map(ParentInfoForm::getBirth_date, StudentParent::setBirthDate);
            mapper.map(ParentInfoForm::getPermanent_address, StudentParent::setPermanentAddress);
        });
    }
}
