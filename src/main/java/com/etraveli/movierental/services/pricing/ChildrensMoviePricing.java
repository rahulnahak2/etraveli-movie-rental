package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import org.springframework.stereotype.Component;

import static com.etraveli.movierental.services.util.Constants.*;

/**
 * Pricing strategy implementation for children’s movies.
 */
@Component
public class ChildrensMoviePricing implements PricingStrategy {
    /**
     * Returns the movie category this strategy supports — Children’s movies.
     */
    @Override
    public MovieType getApplicableCategory() {
        return MovieType.CHILDRENS;
    }

    /**
     * Calculates rental charge based on rental days.
     * - If days rented exceed the base threshold, extra charges apply per additional day.
     * - Otherwise, base rate is charged.
     *
     * @param daysRented Number of days the movie is rented
     * @return Calculated rental charge for children’s movies
     */
    @Override
    public double calculateCharge(int daysRented) {
        return daysRented > BASE_DAYS_FOR_CATAGORY_CHILDREN ?
        BASE_RATE_FOR_CATAGORY_CHILDREN + (daysRented - BASE_DAYS_FOR_CATAGORY_CHILDREN) * EXTRA_RATE_PER_DAY_CHILDREN
                : BASE_RATE_FOR_CATAGORY_CHILDREN;
    }
}
