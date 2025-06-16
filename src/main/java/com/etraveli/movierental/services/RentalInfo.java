package com.etraveli.movierental.services;

import com.etraveli.movierental.entities.CustomerDetails;
import com.etraveli.movierental.entities.Movie;
import com.etraveli.movierental.entities.MovieRental;
import com.etraveli.movierental.exceptions.InvalidMovieTypeException;
import com.etraveli.movierental.exceptions.MovieNotFoundException;
import com.etraveli.movierental.models.CustomerRequest;
import com.etraveli.movierental.repositories.MovieRepository;
import com.etraveli.movierental.services.enums.MovieType;
import com.etraveli.movierental.services.factory.PricingStrategyFactory;
import com.etraveli.movierental.services.pricing.PricingStrategy;
import static com.etraveli.movierental.services.util.Constants.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public final class RentalInfo {

  private final MovieRepository movieRepository;

  private final PricingStrategyFactory pricingStrategyFactory;

    public RentalInfo(MovieRepository movieRepository, PricingStrategyFactory pricingStrategyFactory) {
        this.movieRepository = movieRepository;
        this.pricingStrategyFactory = pricingStrategyFactory;
    }

  /**
   * Generates the rental statement for a customer request.
   */
    public String statement(CustomerRequest request) {
      CustomerDetails customer = new CustomerDetails();
      customer.setId(UUID.randomUUID().toString()); // generate ID
        customer.setName(request.name());

        // Convert each rental request into a domain-specific MovieRental object
        List<MovieRental> rentals = request.rentals().stream()
                .map(r -> createRental(r.movieId(), r.days(), customer))
                .toList();

      customer.setRentals(rentals);

      // Generate and return the rental statement text
      return generateStatement(customer);
    }

  /**
   * Maps movie ID and rental duration to a MovieRental object with associated customer.
   */
  private MovieRental createRental(String movieId, int days, CustomerDetails customer) {
    // Fetch movie from repository or throw exception if not found
    Movie movie = movieRepository.findById(movieId)
            .orElseThrow(() -> new MovieNotFoundException("Movie not found: " + movieId));

    // Create and populate a new MovieRental object
    MovieRental rental = new MovieRental();
    rental.setMovie(movie);
    rental.setDays(days);
    rental.setCustomer(customer);
    return rental;
  }

  /**
   * Generates a detailed rental slip for the given customer.
   */
  public String generateStatement(CustomerDetails customer) {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    StringBuilder rentalSlip = new StringBuilder(String.format(CUSTOMER_DETAIL_STATEMENT, customer.getName()));
    for (MovieRental rental : customer.getRentals()) {
      Movie movie = rental.getMovie();

      // Validate and resolve movie type from code
      MovieType type = Optional.ofNullable(movie.getCode())
              .map(code -> MovieType.valueOf(code.toString().toUpperCase()))
              .orElseThrow(() -> new InvalidMovieTypeException("Invalid movie code for: " + movie.getTitle()));

      // Fetch pricing strategy based on movie type
      PricingStrategy strategy = pricingStrategyFactory.getStrategy(type);

      // Calculate charge and points using strategy
      double charge = strategy.calculateCharge(rental.getDays());
      int points = strategy.calculateFrequentRenterPoints(rental.getDays());

      // Accumulate points and charges
      frequentRenterPoints +=points;
      totalAmount +=charge;

      // Append individual rental line item to statement
      rentalSlip
              .append("\t")
              .append(movie.getTitle())
              .append("\t")
              .append(charge)
              .append("\n");
    }

    // Append total amount and frequent renter points to the statement
    rentalSlip.append(String.format(TOTAL_AMOUNT_STATEMENT, totalAmount));
    rentalSlip.append(String.format(FREQUENT_POINT_STATEMENT, frequentRenterPoints));

    return rentalSlip.toString();
  }
}
