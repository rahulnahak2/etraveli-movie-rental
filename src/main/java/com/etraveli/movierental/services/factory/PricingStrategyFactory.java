package com.etraveli.movierental.services.factory;

import com.etraveli.movierental.services.enums.MovieType;
import com.etraveli.movierental.services.pricing.PricingStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class PricingStrategyFactory {
    private final HashMap<MovieType, PricingStrategy> strategyMap = new HashMap<>();
    public PricingStrategyFactory(List<PricingStrategy> strategies) {
        for (PricingStrategy strategy : strategies) {
            strategyMap.put(strategy.getApplicableCategory(), strategy);
        }
    }

    public PricingStrategy getStrategy(MovieType category) {
        return strategyMap.getOrDefault(category, null);
    }
}
