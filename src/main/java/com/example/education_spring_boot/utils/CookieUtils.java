package com.example.education_spring_boot.utils;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtils {
    public Cookie generateCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        return cookie;
    }
}
