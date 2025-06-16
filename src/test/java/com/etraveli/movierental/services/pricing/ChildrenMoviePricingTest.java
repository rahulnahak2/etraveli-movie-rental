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
        pricing = new ChildrensMoviePricing();
    }

    @Test
    void testGetApplicableCategory() {
        assertEquals(MovieType.CHILDRENS, pricing.getApplicableCategory());
    }
    @Test
    void testCalculateCharge_whenDaysLessThanOrEqualToBase() {
        double charge = pricing.calculateCharge(BASE_DAYS_FOR_CATAGORY_CHILDREN);
        assertEquals(BASE_RATE_FOR_CATAGORY_CHILDREN, charge);
    }

    @Test
    void testCalculateCharge_whenDaysGreaterThanBase() {
        int extraDays = 2;
        double expected = BASE_RATE_FOR_CATAGORY_CHILDREN + (extraDays * EXTRA_RATE_PER_DAY_CHILDREN);
        double charge = pricing.calculateCharge(BASE_DAYS_FOR_CATAGORY_CHILDREN + extraDays);
        assertEquals(expected, charge);
    }

    @Test
    void testCalculateCharge_whenDaysIsZero() {
        double charge = pricing.calculateCharge(0);
        assertEquals(BASE_RATE_FOR_CATAGORY_CHILDREN, charge);
    }

    @Test
    void testCalculateCharge_whenDaysIsNegative() {
        double charge = pricing.calculateCharge(-3);
        assertEquals(BASE_RATE_FOR_CATAGORY_CHILDREN, charge);
    }
}
