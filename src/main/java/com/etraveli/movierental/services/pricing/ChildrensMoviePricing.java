package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import org.springframework.stereotype.Component;

import static com.etraveli.movierental.services.util.Constants.*;

@Component
public class ChildrensMoviePricing implements PricingStrategy {
    @Override
    public MovieType getApplicableCategory() {
        return MovieType.CHILDRENS;
    }

    @Override
    public double calculateCharge(int daysRented) {
        return daysRented > BASE_DAYS_FOR_CATAGORY_CHILDREN ?
        BASE_RATE_FOR_CATAGORY_CHILDREN + (daysRented - BASE_DAYS_FOR_CATAGORY_CHILDREN) * EXTRA_RATE_PER_DAY_CHILDREN
                : BASE_RATE_FOR_CATAGORY_CHILDREN;
    }
}
