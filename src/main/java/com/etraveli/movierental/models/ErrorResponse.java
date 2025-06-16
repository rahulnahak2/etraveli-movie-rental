package com.etraveli.movierental.models;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a standardized error response structure for API responses.
 */
public record ErrorResponse (
        LocalDateTime timestamp,  // The time when the error occurred
        int status,               // HTTP status code (e.g., 400, 404, 500)
        String error,             // Short error description or error type
        String message,           // Detailed error message for the client
        List<String> details      // Additional details or validation errors if any
){
    /**
     * Factory method to create an ErrorResponse instance with current timestamp.
     */
    public static ErrorResponse of(
            int status,
            String error,
            String message,
            List<String> details
    ) {
        return new ErrorResponse(LocalDateTime.now(), status, error, message, details);
    }
}
