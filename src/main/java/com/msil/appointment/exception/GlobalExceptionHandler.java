package com.msil.appointment.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ApiError> notFound(ResourceNotFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, "NOT_FOUND", ex.getMessage(), List.of(), req);
    }
    @ExceptionHandler(DuplicateRecordException.class)
    ResponseEntity<ApiError> duplicate(DuplicateRecordException ex, HttpServletRequest req) {
        return build(HttpStatus.CONFLICT, "DUPLICATE_RECORD", ex.getMessage(), List.of(), req);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage()).toList();
        return build(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", "Request validation failed", details, req);
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiError> generic(Exception ex, HttpServletRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "Unexpected server error", List.of(), req);
    }
    private ResponseEntity<ApiError> build(HttpStatus status, String code, String message,
                                            List<String> details, HttpServletRequest req) {
        return ResponseEntity.status(status).body(new ApiError(code, message, details,
                req.getRequestURI(), Instant.now()));
    }
}
