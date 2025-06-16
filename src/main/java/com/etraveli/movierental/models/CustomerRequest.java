package com.etraveli.movierental.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * Represents a customer's rental request, including customer name and list of movie rentals.
 */
public record CustomerRequest (
        @NotBlank(message = "Customer name is Required") // Validate that customer name is not blank
        String name,
        @Valid // Validate each MovieRentalRequest object in the list
        List<MovieRentalRequest> rentals
){}
