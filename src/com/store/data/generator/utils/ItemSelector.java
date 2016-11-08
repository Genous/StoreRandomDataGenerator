package com.store.data.generator.utils;

import com.store.data.generator.models.Item;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class ItemSelector
{
    public Item getItem()
    {
        ArrayList<Item> itList = new ArrayList<>();

        for(Item items : itemList)
        {
            itList.add(items);
        }
        int randomNum = rand.nextInt(itList.size());

        return itList.get(randomNum);
    }

    public ItemSelector(final List<Item> itemList, Random rand)
    {
        this.itemList = itemList;
        this.rand = rand;
    }

    private List<Item> itemList;
    private Random rand;
}