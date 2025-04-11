package com.example.education_spring_boot.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateTimeUtils {
    public LocalDate changeTimezone(String datetime, String zoneId) {
        return Instant.parse(datetime)
                .atZone(ZoneId.of(zoneId))
                .toLocalDate();
    }
}
