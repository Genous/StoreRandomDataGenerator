package com.store.data.generator.generators;

import com.store.data.generator.calculators.StorageCostCalculator;
import com.store.data.generator.models.Department;
import com.store.data.generator.models.Item;
import com.store.data.generator.models.ItemSize;
import com.store.data.generator.models.ItemType;

import java.util.LinkedList;

/**
 *     private final long id;
 *     private final ItemType itemType;
 *     private final ItemSize itemSize;
 *     private final int costOfStoragePerUnit;
 *     private final Department department;
 */
public class ItemGenerator
{

    public LinkedList<Item> listAllCombinations()
    {
        long currentItemId = 1;
        final LinkedList<Item> itemsList = new LinkedList<>();

        for(Department department : Department.values())
        {
            for (ItemType itemType : ItemType.values())
            {
                for (ItemSize itemSize : ItemSize.values())
                {
                    final Item currentItem1 = new Item(
                            currentItemId,
                            itemType,
                            itemSize,
                            storageCostCalculator.calculateCost(itemType, itemSize, department, Math.random()),
                            department,
                            100);
                    itemsList.add(currentItem1);
                    currentItemId++;

                    final Item currentItem2 = new Item(
                            currentItemId,
                            itemType,
                            itemSize,
                            storageCostCalculator.calculateCost(itemType, itemSize, department, Math.random()),
                            department,
                            100);
                    itemsList.add(currentItem2);
                    currentItemId++;

                    final Item currentItem3 = new Item(
                            currentItemId,
                            itemType,
                            itemSize,
                            storageCostCalculator.calculateCost(itemType, itemSize, department, Math.random()),
                            department,
                            100);
                    itemsList.add(currentItem3);
                    currentItemId++;
                }
            }
        }

        return itemsList;
    }

    public ItemGenerator(final StorageCostCalculator storageCostCalculator)
    {
        this.storageCostCalculator = storageCostCalculator;
    }

    private final StorageCostCalculator storageCostCalculator;
}
