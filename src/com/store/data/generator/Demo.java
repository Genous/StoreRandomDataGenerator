package com.store.data.generator;

import com.store.data.generator.calculators.PriceCalculator;
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
import java.util.Scanner;

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

        int startYear, startMonth, startDay, endYear, endMonth, endDay;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter starting date (Month Day Year)");
        startMonth = scan.nextInt();
        startDay = scan.nextInt();
        startYear = scan.nextInt();

        System.out.println("Enter ending date (Month Day Year) [pls keep start to end date range below 5 months or else earliest dates will cut off]");

        endMonth = scan.nextInt();
        endDay = scan.nextInt();
        endYear = scan.nextInt();

        Random rand = new Random();
        DateTime start = new DateTime(startYear,startMonth,startDay,0,0,0);
        DateTime end = new DateTime(endYear, endMonth, endDay,0,0,0);

        final ItemSelector itemSel = new ItemSelector(itemList, rand);
        final EmployeeSelector empSel = new EmployeeSelector(employeeList, rand);

        final PriceCalculator priceCalculator = new PriceCalculator();
        final PurchaseGenerator purchaseGen = new PurchaseGenerator(start, end, empSel, itemSel, priceCalculator);
        List<Purchase> purchaseList = purchaseGen.generatePurchases(itemList, employeeList);

        for(Purchase purchases : purchaseList)
        {
            System.out.println(purchases);
        }

        System.out.println(purchaseList.size());
    }
}
