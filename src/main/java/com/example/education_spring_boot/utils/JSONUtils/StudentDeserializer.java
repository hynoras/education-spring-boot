package com.example.education_spring_boot.utils.JSONUtils;

import com.example.education_spring_boot.exception.ResourceNotFoundException;
import com.example.education_spring_boot.model.entity.Account;
import com.example.education_spring_boot.model.entity.Student;
import com.example.education_spring_boot.repository.AccountRepo;
import com.example.education_spring_boot.repository.StudentRepo;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.util.Optional;

public class StudentDeserializer extends StdDeserializer<Student>{
    private final StudentRepo studentRepo;

    public StudentDeserializer(StudentRepo studentRepo) {
        super(Student.class);
        this.studentRepo = studentRepo;
    }

    @Override
    public Student deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String studentId = node.textValue();
        Optional<Student> student = studentRepo.findById(studentId);
        if (student.isEmpty()) {
            throw new ResourceNotFoundException("Student with id " + studentId + " can not be found!");
        }
        return student.get();
    }
}
