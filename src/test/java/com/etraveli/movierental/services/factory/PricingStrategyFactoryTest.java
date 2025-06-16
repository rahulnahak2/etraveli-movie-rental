package com.etraveli.movierental.services.factory;

import com.etraveli.movierental.services.enums.MovieType;
import com.etraveli.movierental.services.pricing.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PricingStrategyFactoryTest {
    private PricingStrategyFactory factory;

    private PricingStrategy regularStrategy;
    private PricingStrategy newStrategy;
    @BeforeEach
    void setUp() {
        // Mock a strategy for REGULAR movies
        regularStrategy = mock(PricingStrategy.class);
        when(regularStrategy.getApplicableCategory()).thenReturn(MovieType.REGULAR);

        // Mock a strategy for NEW movies
        newStrategy = mock(PricingStrategy.class);
        when(newStrategy.getApplicableCategory()).thenReturn(MovieType.NEW);

        // Initialize factory with mocked strategies
        factory = new PricingStrategyFactory(List.of(regularStrategy, newStrategy));
    }

    // Verify factory returns the correct strategy based on movie type
    @Test
    void testGetStrategy_returnsCorrectStrategy() {
        assertEquals(regularStrategy, factory.getStrategy(MovieType.REGULAR));
        assertEquals(newStrategy, factory.getStrategy(MovieType.NEW));
    }

    // If strategy for a given movie type is not registered, return null
    @Test
    void testGetStrategy_returnsNullForUnknownType() {
        assertNull(factory.getStrategy(MovieType.CHILDRENS));
    }
}
