package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import org.springframework.stereotype.Component;

@Component
public class RegularMoviePricing implements PricingStrategy {
    @Override
    public MovieType getApplicableCategory() {
        return MovieType.REGULAR;
    }
    @Override
    public double calculateCharge(int daysRented) {
        return daysRented > 2 ? 2 + (daysRented - 2) * 1.5 : 2;
    }
}
