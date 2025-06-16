package com.etraveli.movierental.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetails {
    @Id // Marks this field as the primary key
    private String id;
    private String name;    // Customer's name
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Defines a one-to-many relationship with MovieRental entity
    // `mappedBy = "customer"` means MovieRental has a field named `customer` that owns the relationship
    // `cascade = CascadeType.ALL` propagates all operations (persist, merge, remove, etc.) to rentals
    // `fetch = FetchType.LAZY` means rentals are loaded on demand (not immediately with the CustomerDetails)
    private List<MovieRental> rentals; // List of movie rentals associated with the customer
}
