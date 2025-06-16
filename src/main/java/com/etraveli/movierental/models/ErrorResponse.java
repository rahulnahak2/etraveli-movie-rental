package com.etraveli.movierental.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse (
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        List<String> details
){
    public static ErrorResponse of(
            int status,
            String error,
            String message,
            List<String> details
    ) {
        return new ErrorResponse(LocalDateTime.now(), status, error, message, details);
    }
}
