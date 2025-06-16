package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.etraveli.movierental.services.util.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularMoviePricingTest {
    private RegularMoviePricing pricing;

    @BeforeEach
    void setUp() {
        pricing = new RegularMoviePricing();
    }
    @Test
    void testGetApplicableCategory() {
        assertEquals(MovieType.REGULAR, pricing.getApplicableCategory());
    }

    @Test
    void testCalculateCharge_whenDaysLessThanOrEqualToBase() {
        int days = BASE_DAYS_FOR_CATAGORY_REGULAR;
        assertEquals(BASE_RATE_FOR_CATAGORY_REGULAR, pricing.calculateCharge(days));
    }

    @Test
    void testCalculateCharge_whenDaysGreaterThanBase() {
        int days = BASE_DAYS_FOR_CATAGORY_REGULAR + 2;
        double expected = BASE_RATE_FOR_CATAGORY_REGULAR + (2 * EXTRA_RATE_PER_DAY_REGULAR);
        assertEquals(expected, pricing.calculateCharge(days));
    }

    @Test
    void testCalculateCharge_whenDaysIsZero() {
        int days = 0;
        assertEquals(BASE_RATE_FOR_CATAGORY_REGULAR, pricing.calculateCharge(days));
    }

    @Test
    void testCalculateCharge_whenDaysNegative() {
        int days = -3;
        assertEquals(BASE_RATE_FOR_CATAGORY_REGULAR, pricing.calculateCharge(days), 0.001,
                "Negative days should default to base rate per business logic");
    }
}
