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
        // Initialize pricing instance before each test
        pricing = new RegularMoviePricing();
    }

    // Verify that the strategy is associated with REGULAR movie type
    @Test
    void testGetApplicableCategory() {
        assertEquals(MovieType.REGULAR, pricing.getApplicableCategory());
    }

    // When rental days <= base days, expect base rate
    @Test
    void testCalculateCharge_whenDaysLessThanOrEqualToBase() {
        assertEquals(BASE_RATE_FOR_CATAGORY_REGULAR, pricing.calculateCharge(BASE_DAYS_FOR_CATAGORY_REGULAR));
    }

    // When rental days > base days, expect base rate + extra charge
    @Test
    void testCalculateCharge_whenDaysGreaterThanBase() {
        int days = BASE_DAYS_FOR_CATAGORY_REGULAR + 2;
        double expected = BASE_RATE_FOR_CATAGORY_REGULAR + (2 * EXTRA_RATE_PER_DAY_REGULAR);
        assertEquals(expected, pricing.calculateCharge(days));
    }

    // Even if days = 0, pricing should return base rate
    @Test
    void testCalculateCharge_whenDaysIsZero() {
        int days = 0;
        assertEquals(BASE_RATE_FOR_CATAGORY_REGULAR, pricing.calculateCharge(days));
    }
}
