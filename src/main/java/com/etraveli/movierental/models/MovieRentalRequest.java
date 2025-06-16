package com.etraveli.movierental.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

public record MovieRentalRequest (
        @NotBlank(message = "Movie ID is required")
        String movieId,
        @Min(value = 1, message = "Days must be at least 1")
        int days

){}
