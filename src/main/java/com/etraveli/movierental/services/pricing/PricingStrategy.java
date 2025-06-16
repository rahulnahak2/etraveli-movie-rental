package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import static com.etraveli.movierental.services.util.Constants.*;

public interface PricingStrategy {
    MovieType getApplicableCategory();

    double calculateCharge(int daysRented);

    default int calculateFrequentRenterPoints(int daysRented) {
        return BASE_FREQUENT_POINTS;
    }
}
