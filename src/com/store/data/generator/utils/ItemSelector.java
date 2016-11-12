package com.store.data.generator.utils;

import com.store.data.generator.generators.InventoryRestockingGenerator;
import com.store.data.generator.models.Item;
import org.joda.time.DateTime;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Scanner;

public class ItemSelector
{
    public Item getItem(DateTime date) throws IOException
    {
        //todo hash items based on type, then use the date to make patterns based on type. (t-shirts sell more in the summer etc...)
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