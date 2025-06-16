package com.etraveli.movierental.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a request for renting a movie.
 */
public record MovieRentalRequest (
        @NotBlank(message = "Movie ID is required")     // Movie identifier must not be blank
        String movieId,
        @Min(value = 1, message = "Days must be at least 1")    // Minimum rental period is 1 day
        int days

){}
