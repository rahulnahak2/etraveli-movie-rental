package com.etraveli.movierental.models;

import lombok.Data;

import java.util.List;

@Data
public class CustomerRequest {
    private String name;
    private List<MovieRentalRequest> rentals;
}
