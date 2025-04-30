package com.example.education_spring_boot.features.student.constants;

import static com.example.education_spring_boot.features.student.constants.StudentAttributes.*;

public class StudentMapping {
  public static final String STUDENT_MAPPING = "/" + STUDENT_ATTR;
  public static final String STUDENTS_MAPPING = "/" + STUDENTS_ATTR;
  public static final String AVATAR_MAPPING = "/" + IDENTITY_ATTR;
  public static final String IDENTITY_PATH_PARAMS_MAPPING = "/{" + IDENTITY_ATTR + "}";
}
