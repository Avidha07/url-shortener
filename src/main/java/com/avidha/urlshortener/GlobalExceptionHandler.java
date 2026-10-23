package com.avidha.urlshortener;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            ConstraintViolationException exception) {

        String message = exception.getConstraintViolations()
                .iterator()
                .next()
                .getMessage();

        Map<String, String> response = Map.of(
                "error", message,
                "status", "400"
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(
            ResponseStatusException exception) {

        Map<String, String> response = Map.of(
                "error", exception.getReason(),
                "status", String.valueOf(exception.getStatusCode().value())
        );

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(response);
    }
}