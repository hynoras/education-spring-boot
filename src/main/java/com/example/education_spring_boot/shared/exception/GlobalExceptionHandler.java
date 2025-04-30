package com.example.education_spring_boot.shared.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.education_spring_boot.shared.model.DefaultResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<DefaultResponse> handleBadCredentialsException(
      BadCredentialsException ex, WebRequest request) {
    return new ResponseEntity<>(
        new DefaultResponse(
            new Date(), "Username or password is incorrect!", request.getDescription(false)),
        HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<DefaultResponse> handleResourceNotFoundException(
      ResourceNotFoundException ex, WebRequest request) {
    return new ResponseEntity<>(
        new DefaultResponse(new Date(), ex.getMessage(), request.getDescription(false)),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<DefaultResponse> handleDatabaseException(
      DatabaseException ex, WebRequest request) {
    return new ResponseEntity<>(
        new DefaultResponse(new Date(), ex.getMessage(), request.getDescription(false)),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<DefaultResponse> handleGenericException(Exception ex, WebRequest request) {
    return new ResponseEntity<>(
        new DefaultResponse(
            new Date(), "An unexpected error occurred.", request.getDescription(false)),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
