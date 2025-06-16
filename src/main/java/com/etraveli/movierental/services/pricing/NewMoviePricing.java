package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import org.springframework.stereotype.Component;

import static com.etraveli.movierental.services.util.Constants.*;

/**
 * Pricing strategy implementation for new release movies.
 */
@Component
public class NewMoviePricing implements PricingStrategy{
    /**
     * Returns the movie category this strategy supports — New release movies.
     */
    @Override
    public MovieType getApplicableCategory() {
        return MovieType.NEW;
    }

    /**
     * Calculates rental charge based on days rented.
     * Charge is a fixed per-day rate multiplied by the number of rental days.
     *
     * @param daysRented Number of days the movie is rented
     * @return Calculated rental charge for new release movies
     */
    @Override
    public double calculateCharge(int daysRented) {
        return daysRented * PER_DAY_RATE_CATAGORY_NEW;
    }

    /**
     * Calculates frequent renter points.
     * Awards extra points if rental exceeds a certain number of days.
     *
     * @param daysRented Number of days the movie is rented
     * @return Frequent renter points earned
     */
    @Override
    public int calculateFrequentRenterPoints(int daysRented) {
        return daysRented > DAYS_TO_EARN_BASE_FREQUENT_POINT ? EXTRA_FREQUENT_POINTS : BASE_FREQUENT_POINTS;
    }
}
