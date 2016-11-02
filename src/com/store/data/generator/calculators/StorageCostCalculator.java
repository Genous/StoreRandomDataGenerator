package com.store.data.generator.calculators;

import com.store.data.generator.models.ItemSize;
import com.store.data.generator.models.ItemType;
import com.store.data.generator.models.Department;

public class StorageCostCalculator
{
    public double calculateCost(
            final ItemType itemType,
            final ItemSize itemSize,
            final Department department,
            final double variance)
    {
        double costVar = (itemType.getBaseStorageCostPerMonth()
                + itemType.getBaseStorageCostPerMonth()
                * (itemSize.getAdditionalStorageCostIndexPerMonth() * itemType.getStorageCostIncreasePerSizeIncrease()))*100 - department.getStorageCostVariancePerAdultOrChild() + variance;

        costVar = Math.floor(costVar * 100) / 100;

        return costVar;
    }
}
