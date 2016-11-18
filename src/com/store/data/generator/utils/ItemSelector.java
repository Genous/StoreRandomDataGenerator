package com.store.data.generator.utils;

import com.store.data.generator.generators.InventoryRestockingGenerator;
import com.store.data.generator.models.Item;
import org.joda.time.DateTime;

import java.io.*;
import java.util.*;

public class ItemSelector
{
    public Item getItem(DateTime date) throws IOException
    {
        //todo hash items based on type, then use the date to make patterns based on type. (t-shirts sell more in the summer etc...)

        /**
         * TRENDS TO CONSIDER:
         * 1) Seasonal trends -> shirts sell more in the summer months, jackets in the winter months, etc;
         * 3) Have certain departments sell more items than others (e.g. womens sells 20% more items than boys on average)
         * 6) Have certain sizes be sold more on average than others (e.g. womens dept sees more sales of
         * Medium size items vs. mens seeing more Large sizes being sold
         */

        for(int i = 0; i < initialSize; i++)
        {
            Item curIt = itemsArray.get(i);

            switch(curIt.getDepartment()) // department select for size variations
            {
                case MEN: // men's dept sells mostly Large, a lot medium,, a little small/XL, and rare XXL

                    break;
                case WOMEN: // women's dept sells mostly small/medium, a little large, rare XL/XXL

                    break;
                case BOYS: // boy's sells mostly small/medium, a little large, rare XL/XXL

                    break;
                case GIRLS: // girl's sells mostly small/medium, rare large/XL/XXL

                    break;
                default:
                    break;
            }
        }

        int randomNum = rand.nextInt(itemsArray.size());

        itemsArray.get(randomNum).setStock(-1);

        if(itemsArray.get(randomNum).getStock() == 0)
        {
            File resFile = new File("ResID.txt");
            Scanner fileScan = new Scanner(resFile);

            itemsArray.get(randomNum).setStock(100);

            final InventoryRestockingGenerator invStock = new InventoryRestockingGenerator(Long.parseLong(fileScan.next(), 10), itemsArray.get(randomNum).getId(), date, 100);

            fileScan.close();

            invStock.generateRestocks();
        }

        updateStocks(itemsArray);

        return itemsArray.get(randomNum);
    }

    private static void updateStocks(ArrayList<Item> itemsArray) throws IOException
    {
        File ItFile = new File("ItemList.txt");

        PrintWriter writer = new PrintWriter(ItFile);
        writer.close();

        FileWriter ItWrite = new FileWriter(ItFile.getAbsoluteFile());
        BufferedWriter bWrite = new BufferedWriter(ItWrite);

        for(Item items: itemsArray)
        {
            String itString = items.getId() + " " + items.getItemType() + " " + items.getItemSize() + " " + items.getCostOfStoragePerUnit() + " " + items.getDepartment() + " " + items.getStock() + " \n";
            bWrite.write(itString);
        }
        bWrite.close();
    }

    public ItemSelector(final List<Item> itemList, Random rand, int initialSize)
    {
        for(Item items : itemList)
        {
            itemList.add(items);
        }
        this.rand = rand;
        this.initialSize = initialSize;
    }

    private ArrayList<Item> itemsArray = new ArrayList<>();
    private Random rand;
    private int initialSize;
}