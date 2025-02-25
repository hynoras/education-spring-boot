package com.example.education_spring_boot.config.security;

import com.example.education_spring_boot.service.auth.CustomUserDetailService;
import com.example.education_spring_boot.util.CookieUtil;
import com.example.education_spring_boot.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    int EXPIRATION_TIME = 0;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;
    private final CookieUtil cookieUtil;

    @Autowired
    public  JwtAuthFilter(JwtUtil jwtUtil, CustomUserDetailService customUserDetailService, CookieUtil cookieUtil) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailService = customUserDetailService;
        this.cookieUtil = cookieUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = null;
        String username = null;
        String requestURI = request.getRequestURI();

        if (requestURI.equals("/api/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getCookies() != null) {
            Optional<Cookie> jwtCookie = Arrays.stream(request.getCookies())
                    .filter(cookie -> "Authorization".equals(cookie.getName()))
                    .findFirst();

            if (jwtCookie.isPresent()) {
                token = jwtCookie.get().getValue();
                try {
                    username = jwtUtil.extractUsername(token);

                    if (jwtUtil.isTokenExpired(token)) {
                        Cookie expiredCookie = cookieUtil.generateCookie("Authorization", null, EXPIRATION_TIME);

                        response.addCookie(expiredCookie);
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token expired");
                        return;
                    }
                } catch (ExpiredJwtException e) {
                    Cookie expiredCookie = cookieUtil.generateCookie("Authorization", null, EXPIRATION_TIME);

                    response.addCookie(expiredCookie);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token expired");
                    return;
                }
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities() // Ensure roles are set
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
