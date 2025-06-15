package com.etraveli.movierental.models;

import java.util.List;

public class CustomerDetails {
    private String name;
    private List<MovieRental> rentals;

    public CustomerDetails(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    public String getName() {
        return name;
    }

    public List<MovieRental> getRentals() {
        return rentals;
    }
}
