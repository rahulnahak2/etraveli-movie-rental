package com.etraveli.movierental.services;

import com.etraveli.movierental.entities.Movie;
import com.etraveli.movierental.exceptions.MovieNotFoundException;
import com.etraveli.movierental.models.CustomerRequest;
import com.etraveli.movierental.models.MovieRentalRequest;
import com.etraveli.movierental.repositories.MovieRepository;
import com.etraveli.movierental.services.enums.MovieType;
import com.etraveli.movierental.services.factory.PricingStrategyFactory;
import com.etraveli.movierental.services.pricing.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class RentalInfoTest {
    @InjectMocks
    private RentalInfo rentalInfo;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private PricingStrategyFactory pricingStrategyFactory;

    @Mock
    private PricingStrategy pricingStrategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testStatement_successfulGeneration() {
        //Setup Customer request
        String movieId = "F001";
        String customerName = "C. U. Stomer";
        MovieRentalRequest rentalRequest = new MovieRentalRequest(movieId, 3);
        CustomerRequest request = new CustomerRequest(customerName, List.of(rentalRequest));

        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setTitle("You've Got Mail");
        movie.setCode(MovieType.REGULAR);

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
        when(pricingStrategyFactory.getStrategy(MovieType.REGULAR)).thenReturn(pricingStrategy);
        when(pricingStrategy.calculateCharge(3)).thenReturn(3.50);
        when(pricingStrategy.calculateFrequentRenterPoints(3)).thenReturn(1);

        String result = rentalInfo.statement(request);

        // Assert
        assertTrue(result.contains("C. U. Stomer"));
        assertTrue(result.contains("You've Got Mail"));
        assertTrue(result.contains("3.50"));
        assertTrue(result.contains("1"));
    }
    @Test
    void testStatement_movieNotFound_throwsException() {
        //Setup Customer request
        String movieId = "F0015";
        String customerName = "Alice";
        MovieRentalRequest rentalRequest = new MovieRentalRequest(movieId, 2);
        CustomerRequest request = new CustomerRequest(customerName, List.of(rentalRequest));

        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        // Assert
        MovieNotFoundException ex = assertThrows(MovieNotFoundException.class,
                () -> rentalInfo.statement(request));
        assertEquals("Movie not found: " + movieId, ex.getMessage());
    }
}
