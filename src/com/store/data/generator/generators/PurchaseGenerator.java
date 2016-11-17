package com.store.data.generator.generators;

import com.store.data.generator.calculators.PriceCalculator;
import com.store.data.generator.models.Employee;
import com.store.data.generator.models.Item;
import com.store.data.generator.models.Purchase;
import com.store.data.generator.utils.EmployeeSelector;
import com.store.data.generator.utils.ItemSelector;
import org.joda.time.DateTime;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.LinkedList;

/**
    1- Items are ONLY sold by employees in the same department
    2- Employees with higher levels sell slightly more items
    3- Number of items stolen cannot exceed number of items available
    4- Purchases have to be at normal operation hours (9 am to 5 pm)
 */

public class PurchaseGenerator
{
    public LinkedList<Purchase> generatePurchases(final List<Item> items, final List<Employee> employees) throws IOException
    {
        final LinkedList<Purchase> purchaseList = new LinkedList<>();

        int DailyPurchAmt = 0;

        DateTime day = start;

        File nextPurchID = new File("PurchID.txt");
        Scanner fileScan = new Scanner(nextPurchID);
        long purchaseId = Long.parseLong(fileScan.next(), 10); // read current ID;
        fileScan.close();

        while(!day.toLocalDate().isEqual(end.toLocalDate()))
        {
            if(day.getDayOfWeek() == 6 || day.getDayOfWeek() == 7) // skip saturday/sunday
            {
                day = day.plusDays(1);
                continue;
            }

            DailyPurchAmt = getDateVariance(day);

            for(int i = 0; i < DailyPurchAmt; i++)
            {
                final Item item = itemSel.getItem(day);
                final Employee employee = empSel.getEmp(item);
                final Purchase purchase = new Purchase(
                                            purchaseId,
                                            priceCalc.calculatePrice(item.getCostOfStoragePerUnit(), Math.random()),
                                            day,
                                            item.getId(),
                                            employee.getId());
                 purchaseList.add(purchase);
                 purchaseId++;

                 FileWriter PurchWriteNewID = new FileWriter(nextPurchID.getAbsoluteFile());
                 BufferedWriter bWriteNewID = new BufferedWriter(PurchWriteNewID);
                 bWriteNewID.write(Long.toString(purchaseId));
                 bWriteNewID.close();
            }

            day = day.plusDays(1);
         }

        File PurchFile = new File("PurchaseList.txt");
        FileWriter PurchWrite = new FileWriter(PurchFile, true);
        BufferedWriter bWrite = new BufferedWriter(PurchWrite);

        for(Purchase purchase : purchaseList)
        {
            String test = purchase.toString();
            bWrite.write(test);
        }

        bWrite.close();

        return purchaseList;
    }

    public int getDateVariance(DateTime date)
    {
         int dayVariance = 0;

         switch (date.getDayOfWeek()) // add purchase amount variance for normal days
         {
         case 1: // monday - the least sales
              dayVariance += ThreadLocalRandom.current().nextInt(30, 50 + 1);
         break;
         case 2: // tuesday - low to medium sales
              dayVariance += ThreadLocalRandom.current().nextInt(40, 60 + 1);
         break;
         case 3: // wednesday - medium sales
             dayVariance += ThreadLocalRandom.current().nextInt(50, 85 + 1);
         break;
         case 4: // thursday - medium sales
             dayVariance += ThreadLocalRandom.current().nextInt(50, 85 + 1);
         break;
         case 5: // friday - the most sales
             dayVariance += ThreadLocalRandom.current().nextInt(60, 100 + 1);
         break;
         default: // shouldn't ever need to access this value but yolo
             dayVariance += ThreadLocalRandom.current().nextInt(50, 85 + 1);
         break;
         }

         switch (date.getMonthOfYear()) // add extra purchases for holiday weeks/days
         {
         case 12: // Christmas week / new Years Eve -> Dec 15 to Dec 31; increase the closer you get do dec 25 for christmas week
            switch(date.getDayOfMonth())
            {
                case 15:case 16:case 17:
                    dayVariance += ThreadLocalRandom.current().nextInt(5, 10+1);
                    break;
                case 18:case 19:case 20:case 21:case 22:
                    dayVariance += ThreadLocalRandom.current().nextInt(20, 35+1);
                    break;
                case 23:
                case 24:
                    dayVariance += ThreadLocalRandom.current().nextInt(40, 60+1);
                    break;
                case 25:case 26:case 27:
                    dayVariance += ThreadLocalRandom.current().nextInt(10, 20+1);
                    break;
                case 28:case 29:case 30:
                    dayVariance += ThreadLocalRandom.current().nextInt(7, 14+1);
                    break;
                case 31:
                    dayVariance += ThreadLocalRandom.current().nextInt(15, 23+1);
                    break;
                default:
                    dayVariance += 0;
                    break;

            }
            break;
         case 2:// valentines day -> feb 13/14
             switch(date.getDayOfMonth())
             {
                 case 13:
                     dayVariance += ThreadLocalRandom.current().nextInt(15, 20+1);
                     break;
                 case 14:
                     dayVariance += ThreadLocalRandom.current().nextInt(5, 10+1);
                     break;
                 default:
                     dayVariance += 0;
                     break;
             }
             break;
         case 11: // black friday/thanksgiving week Nov 20ish to Nov 27ish
             switch(date.getDayOfMonth())
             {
                 case 20:case 21:case 22:case 23:
                     dayVariance += ThreadLocalRandom.current().nextInt(10, 15+1);
                     break;
                 case 24:
                     dayVariance += ThreadLocalRandom.current().nextInt(5, 10+1);
                     break;
                 case 25:case 26:case 27:
                     dayVariance += ThreadLocalRandom.current().nextInt(35, 55+1);
                     break;
                 default:
                     dayVariance += 0;
                     break;
             }
            break;
         case 7: // independence day -> july 3/4
             switch(date.getDayOfMonth())
             {
                 case 3:
                     dayVariance += ThreadLocalRandom.current().nextInt(15, 20+1);
                     break;
                 case 4:
                 dayVariance += ThreadLocalRandom.current().nextInt(10, 15+1);
                     break;
                 default:
                     dayVariance += 0;
                     break;
             }
            break;
         default:
             dayVariance += 0;
             break;
         }

        return dayVariance;
    }

    public PurchaseGenerator(final DateTime start, final DateTime end, final EmployeeSelector empSel, final ItemSelector itemSel, final PriceCalculator priceCalc)
    {
        this.start = start;
        this.end = end;
        this.empSel = empSel;
        this.itemSel = itemSel;
        this.priceCalc = priceCalc;
    }

    private final DateTime start;
    private final DateTime end;
    private final EmployeeSelector empSel;
    private final ItemSelector itemSel;
    private final PriceCalculator priceCalc;
}
