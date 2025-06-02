package com.example.education_spring_boot.shared.utils;

import java.util.Set;

public class SqlUtils {
  public static String buildDynamicUpdate(String table, Set<String> columns, String idColumn) {
    StringBuilder sql = new StringBuilder("UPDATE " + table + " SET ");
    columns.forEach(col -> sql.append(col).append(" = ?, "));
    sql.setLength(sql.length() - 2); // remove last comma
    sql.append(" WHERE ").append(idColumn).append(" = ?");
    return sql.toString();
  }
}
