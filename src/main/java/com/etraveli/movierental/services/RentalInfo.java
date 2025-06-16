package com.etraveli.movierental.services;

import com.etraveli.movierental.configuration.MovieCatalog;
import com.etraveli.movierental.entities.CustomerDetails;
import com.etraveli.movierental.entities.Movie;
import com.etraveli.movierental.entities.MovieRental;
import com.etraveli.movierental.models.CustomerRequest;
import com.etraveli.movierental.repositories.MovieRepository;
import com.etraveli.movierental.services.enums.MovieType;
import com.etraveli.movierental.services.factory.PricingStrategyFactory;
import com.etraveli.movierental.services.pricing.PricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.etraveli.movierental.services.util.Constants.*;

@Service
public class RentalInfo {
  @Autowired
  private MovieRepository movieRepository;
  @Autowired
  PricingStrategyFactory pricingStrategyFactory;

  public String statement(CustomerRequest request) {
    CustomerDetails customer = new CustomerDetails();
    customer.setId(UUID.randomUUID().toString()); // generate ID
    customer.setName(request.getName());

    List<MovieRental> rentals = request.getRentals().stream().map(r -> {
      Movie movie = movieRepository.findById(r.getMovieId())
              .orElseThrow(() -> new IllegalArgumentException("Movie not found: " + r.getMovieId()));
      MovieRental rental = new MovieRental();
      rental.setMovie(movie);
      rental.setDays(r.getDays());
      rental.setCustomer(customer); // maintain the relationship
      return rental;
    }).toList();

    customer.setRentals(rentals);

    return generateStatement(customer);
  }

  public String generateStatement(CustomerDetails customer) {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    StringBuilder rentalSlip = new StringBuilder(String.format(CUSTOMER_DETAIL_STATEMENT, customer.getName()));
    for (MovieRental rental : customer.getRentals()) {
      Movie movie = rental.getMovie();
      MovieType type = MovieType.valueOf(movie.getCode().toString().toUpperCase());
      PricingStrategy strategy = pricingStrategyFactory.getStrategy(type);
      double charge = strategy.calculateCharge(rental.getDays());
      int points = strategy.calculateFrequentRenterPoints(rental.getDays());

      //add frequent bonus points
      frequentRenterPoints +=points;
      //sum of total rental amount charged
      totalAmount +=charge;
      rentalSlip.append("\t").append(movie.getTitle()).append("\t").append(charge).append("\n");
    }
    // add footer lines
    rentalSlip.append(String.format(TOTAL_AMOUNT_STATEMENT, totalAmount));
    rentalSlip.append(String.format(FREQUENT_POINT_STATEMENT, frequentRenterPoints));

    return rentalSlip.toString();
  }
}
