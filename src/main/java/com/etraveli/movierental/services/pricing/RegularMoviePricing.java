package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import org.springframework.stereotype.Component;

import static com.etraveli.movierental.services.util.Constants.*;

/**
 * Pricing strategy implementation for regular movies.
 */
@Component
public class RegularMoviePricing implements PricingStrategy {
    /**
     * Returns the movie category this strategy supports — Regular movies.
     */
    @Override
    public MovieType getApplicableCategory() {
        return MovieType.REGULAR;
    }

    /**
     * Calculates rental charge based on days rented.
     * Applies base rate for up to a certain number of days.
     * Adds extra daily rate for any additional days beyond the base.
     *
     * @param daysRented Number of days the movie is rented
     * @return Calculated rental charge for regular movies
     */
    @Override
    public double calculateCharge(int daysRented) {
        return daysRented > BASE_DAYS_FOR_CATAGORY_REGULAR ?
        BASE_RATE_FOR_CATAGORY_REGULAR + (daysRented - BASE_DAYS_FOR_CATAGORY_REGULAR) * EXTRA_RATE_PER_DAY_REGULAR :
        BASE_RATE_FOR_CATAGORY_REGULAR;
    }
}
