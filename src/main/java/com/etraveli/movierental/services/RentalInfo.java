package com.etraveli.movierental.services;

import com.etraveli.movierental.configuration.MovieCatalog;
import com.etraveli.movierental.models.CustomerDetails;
import com.etraveli.movierental.models.Movie;
import com.etraveli.movierental.models.MovieRental;
import com.etraveli.movierental.services.enums.MovieType;
import com.etraveli.movierental.services.factory.PricingStrategyFactory;
import com.etraveli.movierental.services.pricing.PricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.etraveli.movierental.services.util.Constants.*;

@Service
public class RentalInfo {
  @Autowired
  private MovieCatalog movieCatalog;
  @Autowired
  PricingStrategyFactory pricingStrategyFactory;


  public String statement(CustomerDetails customer) {
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    StringBuilder rentalSlip = new StringBuilder(String.format(CUSTOMER_DETAIL_STATEMENT, customer.getName()));
    for (MovieRental rental : customer.getRentals()) {
      Movie movie = movieCatalog.getMovieById(rental.getMovieId());
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
