package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import org.springframework.stereotype.Component;

import static com.etraveli.movierental.services.util.Constants.*;

@Component
public class NewMoviePricing implements PricingStrategy{
    @Override
    public MovieType getApplicableCategory() {
        return MovieType.NEW;
    }

    @Override
    public double calculateCharge(int daysRented) {
        return daysRented * PER_DAY_RATE_CATAGORY_NEW;
    }

    @Override
    public int calculateFrequentRenterPoints(int daysRented) {
        return daysRented > DAYS_TO_EARN_BASE_FREQUENT_POINT ? EXTRA_FREQUENT_POINTS : BASE_FREQUENT_POINTS;
    }
}
