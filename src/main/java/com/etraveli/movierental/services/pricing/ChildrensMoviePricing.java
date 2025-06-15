package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import org.springframework.stereotype.Component;

@Component
public class ChildrensMoviePricing implements PricingStrategy {
    @Override
    public MovieType getApplicableCategory() {
        return MovieType.CHILDRENS;
    }

    @Override
    public double calculateCharge(int daysRented) {
        return daysRented > 3 ? 1.5 + (daysRented - 3) * 1.5 : 1.5;
    }
}
