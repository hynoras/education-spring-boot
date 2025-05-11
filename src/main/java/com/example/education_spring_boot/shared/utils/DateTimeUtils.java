package com.example.education_spring_boot.shared.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

@Component
public class DateTimeUtils {

  public LocalDate changeTimezone(String datetime, ZoneId zoneId) {
    return Instant.parse(datetime).atZone(zoneId).toLocalDate();
  }
}
