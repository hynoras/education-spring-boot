package com.example.education_spring_boot.shared.config.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import com.example.education_spring_boot.shared.constants.generic.GenericValues;
import com.example.education_spring_boot.shared.exception.JwtExpiredException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.education_spring_boot.features.auth.services.CustomUserDetailService;
import com.example.education_spring_boot.shared.constants.auth.AuthConstants;
import com.example.education_spring_boot.shared.utils.CookieUtils;
import com.example.education_spring_boot.shared.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;
  private final CustomUserDetailService customUserDetailService;
  private final CookieUtils cookieUtils;

  @Autowired
  public JwtAuthFilter(
      JwtUtils jwtUtils, CustomUserDetailService customUserDetailService, CookieUtils cookieUtils) {
    this.jwtUtils = jwtUtils;
    this.customUserDetailService = customUserDetailService;
    this.cookieUtils = cookieUtils;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String token = null;
    String username = null;

    if (request.getCookies() != null) {
      Optional<Cookie> jwtCookie =
          Arrays.stream(request.getCookies())
              .filter(cookie -> "Authorization".equals(cookie.getName()))
              .findFirst();

      if (jwtCookie.isPresent()) {
        token = jwtCookie.get().getValue();
        username = jwtUtils.extractUsername(token);
        if (jwtUtils.isTokenExpired(token)) {
          Cookie expiredCookie =
                  cookieUtils.generateCookie(
                          "Authorization", null, GenericValues.ZERO);
          response.addCookie(expiredCookie);
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token expired");
          throw new JwtExpiredException("Jwt expired");
        }
      }
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

      if (jwtUtils.validateToken(token, userDetails)) {

        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}
