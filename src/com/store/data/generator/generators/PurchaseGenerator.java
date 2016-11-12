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

            DailyPurchAmt = ThreadLocalRandom.current().nextInt(60, 100 + 1);

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

        return purchaseList;
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
