package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import org.springframework.stereotype.Component;

import static com.etraveli.movierental.services.util.Constants.*;

@Component
public class RegularMoviePricing implements PricingStrategy {
    @Override
    public MovieType getApplicableCategory() {
        return MovieType.REGULAR;
    }
    @Override
    public double calculateCharge(int daysRented) {
        return daysRented > BASE_DAYS_FOR_CATAGORY_REGULAR ?
        BASE_RATE_FOR_CATAGORY_REGULAR + (daysRented - BASE_DAYS_FOR_CATAGORY_REGULAR) * EXTRA_RATE_PER_DAY_REGULAR :
        BASE_RATE_FOR_CATAGORY_REGULAR;
    }
}
