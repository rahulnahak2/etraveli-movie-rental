package com.etraveli.movierental.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public record CustomerRequest (
        @NotBlank(message = "Customer name is Required")
        String name,
        @Valid
        List<MovieRentalRequest> rentals
){}
