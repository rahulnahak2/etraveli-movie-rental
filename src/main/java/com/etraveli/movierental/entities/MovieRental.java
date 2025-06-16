package com.etraveli.movierental.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRental {
    @Id // Marks 'id' as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Auto-generates a unique identifier (typically using auto-increment in the database)
    private Long id;

    private int days;   // Number of days the movie was rented for

    @ManyToOne(fetch = FetchType.LAZY)
    // Many rentals can be associated with one movie
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    // Specifies the foreign key column in 'movie_rental' table that references the 'movie' table
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    // Many rentals can belong to one customer
    @JoinColumn(name = "customer_id", nullable = false)
    // Specifies the foreign key column in 'movie_rental' that references 'customer_details'
    private CustomerDetails customer;
}
