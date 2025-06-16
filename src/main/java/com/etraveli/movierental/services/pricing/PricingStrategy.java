package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import static com.etraveli.movierental.services.util.Constants.*;

/**
 * Strategy interface for calculating movie rental pricing and frequent renter points.
 */
public interface PricingStrategy {
    /**
     * Returns the movie category this strategy applies to (e.g., NEW, REGULAR, CHILDRENS).
     */
    MovieType getApplicableCategory();


    /**
     * Calculates rental charge based on the number of days the movie is rented.
     *
     * @param daysRented number of rental days
     * @return calculated rental charge
     */
    double calculateCharge(int daysRented);


    /**
     * Calculates frequent renter points.
     * By default, returns base points; specific strategies may override this.
     *
     * @param daysRented number of rental days
     * @return earned frequent renter points
     */
    default int calculateFrequentRenterPoints(int daysRented) {
        return BASE_FREQUENT_POINTS;
    }
}
