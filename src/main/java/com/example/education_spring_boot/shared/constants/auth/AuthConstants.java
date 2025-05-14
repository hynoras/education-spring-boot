package com.example.education_spring_boot.shared.constants.auth;

public class AuthConstants {
  public static final String ADMIN_PREAUTHORIZE = "hasAuthority('" + AuthorityRoles.ADMIN + "')";
  public static final String ADMIN_STUDENT_PREAUTHORIZE =
      "hasAnyAuthority('" + AuthorityRoles.ADMIN + "', '" + AuthorityRoles.STUDENT + "')";
}
