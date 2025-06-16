package com.etraveli.movierental.services.factory;

import com.etraveli.movierental.services.enums.MovieType;
import com.etraveli.movierental.services.pricing.PricingStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class PricingStrategyFactory {
    // Map to hold movie category and corresponding pricing strategy
    private final HashMap<MovieType, PricingStrategy> strategyMap = new HashMap<>();

    /**
     * Constructor initializes the strategy map by registering all available pricing strategies.
     * @param strategies List of all PricingStrategy implementations to register.
     */
    public PricingStrategyFactory(List<PricingStrategy> strategies) {
        for (PricingStrategy strategy : strategies) {
            strategyMap.put(strategy.getApplicableCategory(), strategy);
        }
    }

    /**
     * Retrieves the pricing strategy corresponding to the given movie category.
     * @param category MovieType for which the pricing strategy is requested.
     * @return Corresponding PricingStrategy or null if none found.
     */
    public PricingStrategy getStrategy(MovieType category) {
        return strategyMap.getOrDefault(category, null);
    }
}
