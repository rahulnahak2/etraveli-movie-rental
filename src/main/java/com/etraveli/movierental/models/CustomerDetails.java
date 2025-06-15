package com.etraveli.movierental.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class CustomerDetails {
    private String name;
    private List<MovieRental> rentals;
}
