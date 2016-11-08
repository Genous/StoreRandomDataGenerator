package com.store.data.generator;

import com.store.data.generator.calculators.StorageCostCalculator;
import com.store.data.generator.generators.EmployeeGenerator;
import com.store.data.generator.generators.ItemGenerator;
import com.store.data.generator.generators.NameGenerator;
import com.store.data.generator.models.Employee;
import com.store.data.generator.models.Item;
import com.store.data.generator.utils.NameRetriever;
import org.joda.time.DateTime;
import com.store.data.generator.models.Purchase;
import com.store.data.generator.generators.PurchaseGenerator;
import com.store.data.generator.utils.EmployeeSelector;
import com.store.data.generator.utils.ItemSelector;
import com.store.data.generator.utils.DateSelector;
import java.util.Random;
import java.io.IOException;
import java.util.List;

/**
 * Purchases:
 * 1- Make sure number of items sold doesn't exceed current inventory
 * 2-
 *
 * Tracking stolen items
 *
 * Tracking how fast items sell
 *
 * Tracking Employee Performance
 *
 * Tracking Department Performance
 *
 * Comparing Department maintenance costs vs Department profits
 *
 * Tracking item sales to find which one are seasonal and when
 */
public class Demo
{
    public static void main(String args[]) throws Exception
    {
//        DateTime dateTime = DateTime.now();
//        testEmployeeGenerator();
//        testItemsGenerator();
        testPurchaseGenerator();
    }

    private static void testEmployeeGenerator() throws IOException
    {
        final NameRetriever nameRetriever = new NameRetriever();
        final NameGenerator nameGenerator = new NameGenerator(nameRetriever);
        final EmployeeGenerator employeeGenerator = new EmployeeGenerator(nameGenerator);
        List<Employee> employeeList = employeeGenerator.generateEmployees();

        for (Employee employee : employeeList)
        {
            System.out.println(employee);
        }

        System.out.println(employeeList.size());
    }

    private static void testItemsGenerator() {
        final StorageCostCalculator storageCostCalculator = new StorageCostCalculator();
        final ItemGenerator itemGenerator = new ItemGenerator(storageCostCalculator);
        List<Item> itemList = itemGenerator.listAllCombinations();

        for (Item items : itemList) {
            System.out.println(items);
        }

        System.out.println(itemList.size());
    }

    private static void testPurchaseGenerator() throws IOException
    {
        final NameRetriever nameRetriever = new NameRetriever();
        final NameGenerator nameGenerator = new NameGenerator(nameRetriever);
        final EmployeeGenerator employeeGenerator = new EmployeeGenerator(nameGenerator);
        List<Employee> employeeList = employeeGenerator.generateEmployees();

        final StorageCostCalculator storageCostCalculator = new StorageCostCalculator();
        final ItemGenerator itemGenerator = new ItemGenerator(storageCostCalculator);
        List<Item> itemList = itemGenerator.listAllCombinations();

        Random rand = new Random();
        DateTime start = new DateTime(2005,3,15,12,32,54);
        DateTime end = new DateTime(2006,4,20,14,56,01);

        final DateSelector dateSel = new DateSelector(start, end, rand);
        final ItemSelector itemSel = new ItemSelector(itemList, rand);
        final EmployeeSelector empSel = new EmployeeSelector(employeeList, rand);
        final PurchaseGenerator purchaseGen = new PurchaseGenerator(dateSel, empSel, itemSel);

        List<Purchase> purchaseList = purchaseGen.generatePurchases(itemList, employeeList);

        for(Purchase purchases : purchaseList)
        {
            System.out.println(purchases);
        }

        System.out.println(purchaseList.size());
    }
}
