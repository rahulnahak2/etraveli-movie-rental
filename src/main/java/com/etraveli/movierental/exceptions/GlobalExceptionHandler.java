package com.etraveli.movierental.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.etraveli.movierental.models.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMovieNotFound(MovieNotFoundException ex) {
        log.warn("Movie not found: {}", ex.getMessage());

        return buildResponse(HttpStatus.NOT_FOUND, "Movie Not Found", ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidRentalException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRental(InvalidRentalException ex) {
        log.warn("Invalid rental: {}", ex.getMessage());

        return buildResponse(HttpStatus.BAD_REQUEST, "Invalid Rental", ex.getMessage(), null);
    }
    @ExceptionHandler(InvalidMovieTypeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMovieType(InvalidMovieTypeException ex) {
        log.warn("Invalid movie type: {}", ex.getMessage());

        return buildResponse(HttpStatus.BAD_REQUEST, "Invalid Movie type", ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());

        log.warn("Validation failed: {}", fieldErrors);

        return buildResponse(HttpStatus.BAD_REQUEST, "Validation Failed", "Check the input fields", fieldErrors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> violations = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.toList());

        log.warn("Constraint violation: {}", violations);

        return buildResponse(HttpStatus.BAD_REQUEST, "Validation Error", "Constraint violations occurred", violations);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        log.error("Unhandled error: {}", ex.getMessage(), ex);

        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage(), null);
    }

    private ResponseEntity<ErrorResponse> buildResponse(
            HttpStatus status, String error, String message, List<String> details) {

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                error,
                message,
                details
                );

        return new ResponseEntity<>(response, status);
    }
}
