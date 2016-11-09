package com.store.data.generator.calculators;

public class PriceCalculator
{
    public double calculatePrice(final double storageCost, final double variance)
    {
        double priceVar = storageCost + 1 + variance;

        priceVar = Math.floor(priceVar * 100) / 100;

        return priceVar;
    }
}
