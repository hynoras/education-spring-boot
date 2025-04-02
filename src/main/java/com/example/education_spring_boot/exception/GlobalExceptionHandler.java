package com.example.education_spring_boot.exception;

import com.example.education_spring_boot.model.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Error> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        return new ResponseEntity<>(new Error(new Date(), "Username or password is incorrect!", request.getDescription(false)), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new Error(new Date(), ex.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<Error> handleDatabaseException(DatabaseException ex, WebRequest request) {
        return new ResponseEntity<>(new Error(new Date(), ex.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGenericException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new Error(new Date(), "An unexpected error occurred.", request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
