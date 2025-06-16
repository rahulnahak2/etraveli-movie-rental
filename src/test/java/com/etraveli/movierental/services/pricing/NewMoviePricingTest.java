package com.etraveli.movierental.services.pricing;

import com.etraveli.movierental.services.enums.MovieType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.etraveli.movierental.services.util.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewMoviePricingTest {
    private NewMoviePricing pricing;

    @BeforeEach
    void setUp() {
        // Initialize pricing strategy before each test
        pricing = new NewMoviePricing();
    }

    // Ensure the pricing strategy applies to NEW movies
    @Test
    void testGetApplicableCategory() {
        assertEquals(MovieType.NEW, pricing.getApplicableCategory());
    }

    // Test charge calculation for valid rental days
    @Test
    void testCalculateCharge_whenDaysRentedPositive() {
        int days = 5;
        double expected = days * PER_DAY_RATE_CATAGORY_NEW;
        assertEquals(expected, pricing.calculateCharge(days));
    }

    // Charge should be zero when no days are rented
    @Test
    void testCalculateCharge_whenDaysRentedZero() {
        assertEquals(0.0, pricing.calculateCharge(0));
    }

    // For base rental duration, return standard frequent renter points
    @Test
    void testCalculateFrequentRenterPoints_basePoints() {
        assertEquals(BASE_FREQUENT_POINTS, pricing.calculateFrequentRenterPoints(DAYS_TO_EARN_BASE_FREQUENT_POINT));
    }

    // For rentals longer than base days, return extra points
    @Test
    void testCalculateFrequentRenterPoints_extraPoints() {
        int days = DAYS_TO_EARN_BASE_FREQUENT_POINT + 1;
        assertEquals(EXTRA_FREQUENT_POINTS, pricing.calculateFrequentRenterPoints(days));
    }

    // Even for 0 days, return base points
    @Test
    void testCalculateFrequentRenterPoints_ZeroDays() {
        assertEquals(BASE_FREQUENT_POINTS, pricing.calculateFrequentRenterPoints(0));
    }
}
