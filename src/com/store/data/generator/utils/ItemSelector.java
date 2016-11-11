package com.store.data.generator.utils;

import com.store.data.generator.models.Item;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class ItemSelector
{
    public Item getItem()
    {
        //todo hash items based on type, then use the date to make patterns based on type. (t-shirts sell more in the summer etc...)
        int randomNum = rand.nextInt(itemsArray.size());

        return itemsArray.get(randomNum);
    }

    public ItemSelector(final List<Item> itemList, Random rand)
    {
        for(Item items : itemList)
        {
            itemsArray.add(items);
        }
        this.rand = rand;
    }

    private ArrayList<Item> itemsArray = new ArrayList<>();
    private Random rand;
}