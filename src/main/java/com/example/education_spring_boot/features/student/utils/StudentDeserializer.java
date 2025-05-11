package com.example.education_spring_boot.features.student.utils;

import java.io.IOException;
import java.util.Optional;

import com.example.education_spring_boot.features.student.models.entities.Student;
import com.example.education_spring_boot.features.student.repositories.StudentRepo;
import com.example.education_spring_boot.shared.exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class StudentDeserializer extends StdDeserializer<Student> {
  private final StudentRepo studentRepo;

  public StudentDeserializer(StudentRepo studentRepo) {
    super(Student.class);
    this.studentRepo = studentRepo;
  }

  @Override
  public Student deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException, JacksonException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    String studentId = node.textValue();
    Optional<Student> student = studentRepo.findById(studentId);
    if (student.isEmpty()) {
      throw new ResourceNotFoundException("Student with id " + studentId + " can not be found!");
    }
    return student.get();
  }
}
