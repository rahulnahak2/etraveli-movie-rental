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
        pricing = new NewMoviePricing();
    }
    @Test
    void testGetApplicableCategory() {
        assertEquals(MovieType.NEW, pricing.getApplicableCategory());
    }

    @Test
    void testCalculateCharge_whenDaysRentedPositive() {
        int days = 5;
        double expected = days * PER_DAY_RATE_CATAGORY_NEW;
        assertEquals(expected, pricing.calculateCharge(days));
    }

    @Test
    void testCalculateCharge_whenDaysRentedZero() {
        assertEquals(0.0, pricing.calculateCharge(0));
    }

    @Test
    void testCalculateCharge_whenDaysRentedNegative() {
        int days = -3;
        double expected = days * PER_DAY_RATE_CATAGORY_NEW;
        assertEquals(expected, pricing.calculateCharge(days));
    }

    @Test
    void testCalculateFrequentRenterPoints_basePoints() {
        assertEquals(BASE_FREQUENT_POINTS, pricing.calculateFrequentRenterPoints(DAYS_TO_EARN_BASE_FREQUENT_POINT));
    }

    @Test
    void testCalculateFrequentRenterPoints_extraPoints() {
        int days = DAYS_TO_EARN_BASE_FREQUENT_POINT + 1;
        assertEquals(EXTRA_FREQUENT_POINTS, pricing.calculateFrequentRenterPoints(days));
    }

    @Test
    void testCalculateFrequentRenterPoints_edgeCaseZeroDays() {
        assertEquals(BASE_FREQUENT_POINTS, pricing.calculateFrequentRenterPoints(0));
    }

    @Test
    void testCalculateFrequentRenterPoints_negativeDays() {
        assertEquals(BASE_FREQUENT_POINTS, pricing.calculateFrequentRenterPoints(-1));
    }
}
