package com.etraveli.movierental.configuration;

import com.etraveli.movierental.entities.CustomerDetails;
import com.etraveli.movierental.entities.Movie;
import com.etraveli.movierental.entities.MovieRental;
import com.etraveli.movierental.repositories.CustomerRepository;
import com.etraveli.movierental.repositories.MovieRepository;
import com.etraveli.movierental.services.enums.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class MovieCatalog implements CommandLineRunner {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args){
        // Save movies first
        List<Movie> movies = movieRepository.saveAll(List.of(
                new Movie("F001", "You've Got Mail", MovieType.REGULAR),
                new Movie("F002", "Matrix", MovieType.REGULAR),
                new Movie("F003", "Cars", MovieType.CHILDRENS),
                new Movie("F004", "Fast & Furious X", MovieType.NEW)
        ));

        // Create a movie lookup map for easy access
        HashMap<String, Movie> movieMap = new HashMap<>();
        movies.forEach(movie -> movieMap.put(movie.getId(), movie));

        // Create the customer
        CustomerDetails customer = new CustomerDetails();
        customer.setId("C001");
        customer.setName("John Doe");

        // Create rentals and assign movie + customer
        MovieRental rental1 = new MovieRental();
        rental1.setDays(3);
        rental1.setMovie(movieMap.get("F001"));
        rental1.setCustomer(customer);

        MovieRental rental2 = new MovieRental();
        rental2.setDays(4);
        rental2.setMovie(movieMap.get("F004"));
        rental2.setCustomer(customer);

        // Attach rentals to customer
        customer.setRentals(List.of(rental1, rental2));

        // Save customer
        customerRepository.save(customer);
    }
}
