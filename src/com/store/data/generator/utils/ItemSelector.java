package com.store.data.generator.utils;

import com.store.data.generator.generators.InventoryRestockingGenerator;
import com.store.data.generator.models.Item;
import org.joda.time.DateTime;

import java.io.*;
import java.util.*;

import static java.lang.Math.toIntExact;

public class ItemSelector
{
    public Item getItem(DateTime date) throws IOException
    {
        //todo hash items based on type, then use the date to make patterns based on type. (t-shirts sell more in the summer etc...)

        /**
         SHIRT, -> mostly in summer and spring, decent amount in fall, small in december
         SHORTS, -> mostly in summer and spring, decent in fall, rare in december
         JEANS, -> mostly in fall and winter, sometimes in spring, rare in summer
         HAT, -> mostly in summer, moderately in spring, rare in fall and december
         BELT, -> no real seasonal trends, belts aren't seasonal items
         SOCKS, -> no real trends
         JACKET, -> mostly in fall and winter, sometimes in spring, rare in summer
         SHOES; -> no real trends
         */

        ArrayList<Item> itemsArray = new ArrayList<Item>();

        for(Item items : itemList)
        {
            itemsArray.add(items);
        }

        for(int i = 0; i < initialSize; i++) // look at every unique item in itemsarray, put copy(s) of it in the array
                                             // again to increase chances of it being selected for a purchase for
                                             // trends to show up
        {
            Item curIt = itemsArray.get(i);

            switch(curIt.getDepartment()) // department select for size variations
            {
                case MEN: // men's dept sells mostly Large, a lot medium, a little small/XL, and rare XXL
                    switch(curIt.getItemSize())
                    {
                        case SMALL:
                            for(int x = 0; x < 5; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case MEDIUM:
                            for(int x = 0; x < 8; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case LARGE:
                            for(int x = 0; x < 12; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case XL:
                            for(int x = 0; x < 4; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case XXL:
                            for(int x = 0; x < 2; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        default:
                            itemsArray.add(curIt); // just add 1 copy, shouldnt ever reach this though
                            break;
                    }
                    break;
                case WOMEN: // women's dept sells mostly small/medium, a little large, rare XL/XXL
                    switch(curIt.getItemSize())
                    {
                        case SMALL:
                            for(int x = 0; x < 15; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case MEDIUM:
                            for(int x = 0; x < 10; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case LARGE:
                            for(int x = 0; x < 5; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case XL:
                            for(int x = 0; x < 2; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case XXL:
                            for(int x = 0; x < 1; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        default:
                            itemsArray.add(curIt); // just add 1 copy, shouldnt ever reach this though
                            break;
                    }
                    break;
                case BOYS: // boy's sells mostly small/medium, a little large, rare XL/XXL
                    switch(curIt.getItemSize())
                    {
                        case SMALL:
                            for(int x = 0; x < 15; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case MEDIUM:
                            for(int x = 0; x < 13; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case LARGE:
                            for(int x = 0; x < 7; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case XL:
                            for(int x = 0; x < 2; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case XXL:
                            for(int x = 0; x < 2; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        default:
                            itemsArray.add(curIt); // just add 1 copy, shouldnt ever reach this though
                            break;
                    }
                    break;
                case GIRLS: // girl's sells mostly small/medium, rare large/XL/XXL
                    switch(curIt.getItemSize())
                    {
                        case SMALL:
                            for(int x = 0; x < 12; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case MEDIUM:
                            for(int x = 0; x < 8; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case LARGE:
                            for(int x = 0; x < 3; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case XL:
                            for(int x = 0; x < 1; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case XXL:
                            for(int x = 0; x < 1; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        default:
                            itemsArray.add(curIt); // just add 1 copy, shouldnt ever reach this though
                            break;
                    }
                    break;
                default:
                    itemsArray.add(curIt); // just add 1 copy, shouldnt ever reach this though
                    break;
            }

            switch(date.getMonthOfYear()) // get seasonal trends: Spring = 3 to 6, Summer = 7 to 9, Fall = 10 to 11, Winter = 12 to 2
            {
                case 3:case 4:case 5:case 6: // spring
                    switch(curIt.getItemType())
                    {
                        case SHIRT:
                            for(int x = 0; x < 10; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case SHORTS:
                            for(int x = 0; x < 10; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case JEANS:
                            for(int x = 0; x < 4; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case HAT:
                            for(int x = 0; x < 7; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case BELT:
                            for(int x = 0; x < 5; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case SOCKS:
                            for(int x = 0; x < 5; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case JACKET:
                            for(int x = 0; x < 3; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        case SHOES:
                            for(int x = 0; x < 5; x++)
                            {
                                itemsArray.add(curIt);
                            }
                            break;
                        default:
                            itemsArray.add(curIt); // just add 1 copy, shouldnt ever reach this though
                            break;
                    }
                    break;
                case 7:case 8:case 9: // summer
                switch(curIt.getItemType())
                {
                    case SHIRT:
                        for(int x = 0; x < 12; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case SHORTS:
                        for(int x = 0; x < 10; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case JEANS:
                        for(int x = 0; x < 2; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case HAT:
                        for(int x = 0; x < 8; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case BELT:
                        for(int x = 0; x < 5; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case SOCKS:
                        for(int x = 0; x < 5; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case JACKET:
                        for(int x = 0; x < 2; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case SHOES:
                        for(int x = 0; x < 5; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    default:
                        itemsArray.add(curIt); // just add 1 copy, shouldnt ever reach this though
                        break;
                }
                case 10:case 11: // fall
                switch(curIt.getItemType())
                {
                    case SHIRT:
                        for(int x = 0; x < 7; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case SHORTS:
                        for(int x = 0; x < 5; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case JEANS:
                        for(int x = 0; x < 9; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case HAT:
                        for(int x = 0; x < 2; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case BELT:
                        for(int x = 0; x < 5; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case SOCKS:
                        for(int x = 0; x < 5; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case JACKET:
                        for(int x = 0; x < 7; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case SHOES:
                        for(int x = 0; x < 5; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    default:
                        itemsArray.add(curIt); // just add 1 copy, shouldnt ever reach this though
                        break;
                }
                case 12:case 1:case 2: // winter
                switch(curIt.getItemType())
                {
                    case SHIRT:
                        for(int x = 0; x < 3; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case SHORTS:
                        for(int x = 0; x < 2; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case JEANS:
                        for(int x = 0; x < 7; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case HAT:
                        for(int x = 0; x < 2; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case BELT:
                        for(int x = 0; x < 5; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case SOCKS:
                        for(int x = 0; x < 5; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case JACKET:
                        for(int x = 0; x < 10; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    case SHOES:
                        for(int x = 0; x < 5; x++)
                        {
                            itemsArray.add(curIt);
                        }
                        break;
                    default:
                        itemsArray.add(curIt); // just add 1 copy, shouldnt ever reach this though
                        break;
                }
                default:
                    itemsArray.add(curIt); // just add 1 copy
                    break;
            }
        }

        int randomNum = rand.nextInt(itemsArray.size());

        Item changeItem = itemList.get(0);
        int arrayPos = 0;

        for(Item items : itemList)
        {
            if(items.getId() == itemsArray.get(randomNum).getId())
            {
                changeItem = items;
                changeItem.setStock(-1);
                break;
            }
            else{
                arrayPos++;
            }
        }

        itemList.set(arrayPos, changeItem);

        if(changeItem.getStock() == 0)
        {
            File resFile = new File("ResID.txt");
            Scanner fileScan = new Scanner(resFile);

            changeItem.setStock(100);

            final InventoryRestockingGenerator invStock = new InventoryRestockingGenerator(Long.parseLong(fileScan.next(), 10), changeItem.getId(), date, 100);

            fileScan.close();

            invStock.generateRestocks();
        }

        updateStocks(itemList);

        return changeItem;
    }

    private static void updateStocks(List<Item> itemsList) throws IOException
    {
        File ItFile = new File("ItemList.txt");

        PrintWriter writer = new PrintWriter(ItFile);
        writer.close();

        FileWriter ItWrite = new FileWriter(ItFile.getAbsoluteFile());
        BufferedWriter bWrite = new BufferedWriter(ItWrite);

        for(Item items: itemsList)
        {
            String itString = items.getId() + " " + items.getItemType() + " " + items.getItemSize() + " " + items.getCostOfStoragePerUnit() + " " + items.getDepartment() + " " + items.getStock() + " \n";
            bWrite.write(itString);
        }
        bWrite.close();
    }

    public ItemSelector(final List<Item> itemList, Random rand, int size)
    {
        this.rand = rand;
        this.initialSize = size;
        this.itemList = itemList;
    }

    private Random rand;
    private int initialSize;
    private List<Item> itemList;
}