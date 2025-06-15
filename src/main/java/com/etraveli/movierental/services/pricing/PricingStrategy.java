package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;

public interface PricingStrategy {
    MovieType getApplicableCategory();

    double calculateCharge(int daysRented);

    default int calculateFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
