package com.example.education_spring_boot.shared.constants.datetime;

import java.time.ZoneId;
import java.util.Date;

public class DateTimeConstants {
  public static final ZoneId BANGKOK_ZONE = ZoneId.of("Asia/Bangkok");
  public static final int ONE_HOUR_IN_SECOND = 60 * 60;
  public static final int ONE_DAY_IN_SECOND = 24 * 60 * 60;
  public static final Date ONE_HOUR_IN_UNIX = new Date(System.currentTimeMillis() + 1000 * 60 * 60);
}
