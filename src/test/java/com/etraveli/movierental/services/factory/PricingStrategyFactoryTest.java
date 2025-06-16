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
        regularStrategy = mock(PricingStrategy.class);
        when(regularStrategy.getApplicableCategory()).thenReturn(MovieType.REGULAR);

        newStrategy = mock(PricingStrategy.class);
        when(newStrategy.getApplicableCategory()).thenReturn(MovieType.NEW);

        factory = new PricingStrategyFactory(List.of(regularStrategy, newStrategy));
    }
    @Test
    void testGetStrategy_returnsCorrectStrategy() {
        assertEquals(regularStrategy, factory.getStrategy(MovieType.REGULAR));
        assertEquals(newStrategy, factory.getStrategy(MovieType.NEW));
    }
    @Test
    void testGetStrategy_returnsNullForUnknownType() {
        assertNull(factory.getStrategy(MovieType.CHILDRENS));
    }
}
