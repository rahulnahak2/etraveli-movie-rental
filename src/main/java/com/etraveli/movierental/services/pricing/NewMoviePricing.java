package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import org.springframework.stereotype.Component;

@Component
public class NewMoviePricing implements PricingStrategy{
    @Override
    public MovieType getApplicableCategory() {
        return MovieType.NEW;
    }

    @Override
    public double calculateCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int calculateFrequentRenterPoints(int daysRented) {
        return daysRented > 2 ? 2 : 1;
    }
}
