package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.etraveli.movierental.services.util.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChildrenMoviePricingTest {
    private ChildrensMoviePricing pricing;
    @BeforeEach
    void setUp() {
        // Initialize the pricing strategy instance before each test
        pricing = new ChildrensMoviePricing();
    }

    // Verify that the pricing strategy is mapped to CHILDREN'S movie type
    @Test
    void testGetApplicableCategory() {
        assertEquals(MovieType.CHILDRENS, pricing.getApplicableCategory());
    }

    // If rental days are within or equal to base days, return the base rate
    @Test
    void testCalculateCharge_whenDaysLessThanOrEqualToBase() {
        double charge = pricing.calculateCharge(BASE_DAYS_FOR_CATAGORY_CHILDREN);
        assertEquals(BASE_RATE_FOR_CATAGORY_CHILDREN, charge);
    }

    // If rental days exceed base days, additional charge should be applied
    @Test
    void testCalculateCharge_whenDaysGreaterThanBase() {
        int extraDays = 2;
        double expected = BASE_RATE_FOR_CATAGORY_CHILDREN + (extraDays * EXTRA_RATE_PER_DAY_CHILDREN);
        double charge = pricing.calculateCharge(BASE_DAYS_FOR_CATAGORY_CHILDREN + extraDays);
        assertEquals(expected, charge);
    }
}
