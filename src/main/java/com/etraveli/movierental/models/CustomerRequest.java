package com.etraveli.movierental.models;

import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class CustomerRequest {
    private String name;
    @Valid
    private List<MovieRentalRequest> rentals;
}
