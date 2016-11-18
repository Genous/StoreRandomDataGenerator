package com.store.data.generator;

import com.store.data.generator.calculators.PriceCalculator;
import com.store.data.generator.calculators.StorageCostCalculator;
import com.store.data.generator.generators.EmployeeGenerator;
import com.store.data.generator.generators.ItemGenerator;
import com.store.data.generator.generators.NameGenerator;
import com.store.data.generator.models.*;
import com.store.data.generator.utils.NameRetriever;
import org.joda.time.DateTime;
import com.store.data.generator.generators.PurchaseGenerator;
import com.store.data.generator.utils.EmployeeSelector;
import com.store.data.generator.utils.ItemSelector;
import com.store.data.generator.utils.DateSelector;

import java.io.*;
import java.util.*;

/**
 * Purchases:
 * 1- Make sure number of items sold doesn't exceed current inventory
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

/**
 * TRENDS TO CONSIDER:
         * 1) Seasonal trends -> shirts sell more in the summer months, jackets in the winter months, etc;
         * DONE - 2) Certain holiday days/weeks, like black friday, see increases in overall purchases, etc. - DONE
         * 3) Have certain departments sell more items than others (e.g. womens sells 20% more items than boys on average)
         * DONE - 4) Certain days of the week see more sales on average (e.g. friday sells more on average than on mondays) - DONE
         * DONE - 5) Increase chances of getting higher level employees to sell items more frequently than lower level employees - DONE
         * DONE - 6) Have certain sizes be sold more on average than others (e.g. womens dept sees more sales of Medium size items vs. mens seeing more Large sizes being sold) - DONE
         * 7)
         *
         *  [Kevin] I was thinking we somehow check if an item has an attribute that satisfies these above conditions, and just repeatedly add say, X more instances of that specific item to the itemlist, so
         *  itemselector has a higher chance of picking out those specific items randomly vs others that don't satisfy those conditions (may be better solutions though, just brainstorming)
         *  Same above for employeeselector too for higher vs lower employees, maybe each level 5 is repeated in the list 5 times, vs 4 being in it 4 times, etc.
         */

public class Demo
{
    public static void main(String args[]) throws Exception
    {
 //       testEmployeeGenerator();
  //      testItemsGenerator();
//       testPurchaseGenerator();
 //       writeToEmployeeList();
  //     writeToItemList();
//        resetStocks();
//        testPurchaseDateTrends();
 //       testEmployeeTrends();
    }

    private static void testPurchaseDateTrends() throws IOException
    {
        List<Employee> employeeList = new ArrayList<>();
        readEmpFile(employeeList);

        List<Item> itemList = new ArrayList<Item>();
        readItemFile(itemList);

        int startYear, startMonth, startDay, endYear, endMonth, endDay;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter starting date (Month Day Year)");
        startMonth = scan.nextInt();
        startDay = scan.nextInt();
        startYear = scan.nextInt();

        System.out.println("Enter ending date (Month Day Year) [does not generate purchases ON the end date] [keep start to end date range below ~5 months or else earliest dates will cut off on terminal]");

        endMonth = scan.nextInt();
        endDay = scan.nextInt();
        endYear = scan.nextInt();

        Random rand = new Random();
        DateTime start = new DateTime(startYear,startMonth,startDay,0,0,0);
        DateTime end = new DateTime(endYear, endMonth, endDay,0,0,0);

        final ItemSelector itemSel = new ItemSelector(itemList, rand, itemList.size());
        final EmployeeSelector empSel = new EmployeeSelector(employeeList, rand);

        final PriceCalculator priceCalculator = new PriceCalculator();
        final PurchaseGenerator purchaseGen = new PurchaseGenerator(start, end, empSel, itemSel, priceCalculator);
        List<Purchase> purchaseList = purchaseGen.generatePurchases(itemList, employeeList);

        System.out.println(purchaseList.size());
    }

     private static void testEmployeeTrends() throws IOException
     {
     List<Employee> employeeList = new ArrayList<>();
     readEmpFile(employeeList);

     List<Item> itemList = new ArrayList<Item>();
     readItemFile(itemList);

     int startYear, startMonth, startDay, endYear, endMonth, endDay;
     Scanner scan = new Scanner(System.in);

     System.out.println("Enter starting date (Month Day Year)");
     startMonth = scan.nextInt();
     startDay = scan.nextInt();
     startYear = scan.nextInt();

     System.out.println("Enter ending date (Month Day Year) [does not generate purchases ON the end date] [takes a while for long date ranges]");

     endMonth = scan.nextInt();
     endDay = scan.nextInt();
     endYear = scan.nextInt();

     Random rand = new Random();
     DateTime start = new DateTime(startYear,startMonth,startDay,0,0,0);
     DateTime end = new DateTime(endYear, endMonth, endDay,0,0,0);

     final ItemSelector itemSel = new ItemSelector(itemList, rand, itemList.size());
     final EmployeeSelector empSel = new EmployeeSelector(employeeList, rand);

     final PriceCalculator priceCalculator = new PriceCalculator();
     final PurchaseGenerator purchaseGen = new PurchaseGenerator(start, end, empSel, itemSel, priceCalculator);
     List<Purchase> purchaseList = purchaseGen.generatePurchases(itemList, employeeList);

         int oneCount = 0;
         int twoCount = 0;
         int threeCount = 0;
         int fourCount = 0;
         int fiveCount = 0;

         for(Purchase purchases : purchaseList)
         {
            for(Employee employee : employeeList)
            {
                if(employee.getId() == purchases.getEmployeeId())
                {
                    switch(employee.getLevel())
                    {
                        case ONE:
                            oneCount++;
                            break;
                        case TWO:
                            twoCount++;
                            break;
                        case THREE:
                            threeCount++;
                            break;
                        case FOUR:
                            fourCount++;
                            break;
                        case FIVE:
                            fiveCount++;
                            break;
                        default:
                            break;
                    }
                }
            }
         }

         System.out.println(oneCount);
         System.out.println(twoCount);
         System.out.println(threeCount);
         System.out.println(fourCount);
         System.out.println(fiveCount);
     }

    private static void resetStocks() throws IOException
    {
        List<Item> itemList = new ArrayList<Item>();
        readItemFile(itemList);

        File ItFile = new File("ItemList.txt");

        PrintWriter writer = new PrintWriter(ItFile);
        writer.close();

        FileWriter ItWrite = new FileWriter(ItFile.getAbsoluteFile());
        BufferedWriter bWrite = new BufferedWriter(ItWrite);

        for(Item items: itemList)
        {
            String itString = items.getId() + " " + items.getItemType() + " " + items.getItemSize() + " " + items.getCostOfStoragePerUnit() + " " + items.getDepartment() + " " + 100 + " \n";
            bWrite.write(itString);
        }
        bWrite.close();
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

    private static void writeToEmployeeList() throws IOException
    {
        File empFile = new File("EmployeeList.txt");
        FileReader empRead = new FileReader(empFile.getAbsoluteFile());
        BufferedReader bRead = new BufferedReader(empRead);

        if(bRead.readLine() != null)
        {
            System.out.println("File is already written to, aborting");
            return;
        }

        final NameRetriever nameRetriever = new NameRetriever();
        final NameGenerator nameGenerator = new NameGenerator(nameRetriever);
        final EmployeeGenerator employeeGenerator = new EmployeeGenerator(nameGenerator);
        List<Employee> employeeList = employeeGenerator.generateEmployees();

        FileWriter empWrite = new FileWriter(empFile.getAbsoluteFile());
        BufferedWriter bWrite = new BufferedWriter(empWrite);

        for(Employee employee : employeeList)
        {
            String empString = employee.getId() + " " + employee.getFirstName() + " " + employee.getLastName() + " " + employee.getLevel() + " " + employee.getDepartment() + " \n";
            bWrite.write(empString);
        }
        bWrite.close();
    }

    private static void writeToItemList() throws IOException
    {
        File ItFile = new File("ItemList.txt");
        FileReader ItRead = new FileReader(ItFile.getAbsoluteFile());
        BufferedReader bRead = new BufferedReader(ItRead);

        if(bRead.readLine() != null)
        {
            System.out.println("File is already written to, aborting");
            return;
        }

        final StorageCostCalculator storageCostCalculator = new StorageCostCalculator();
        final ItemGenerator itemGenerator = new ItemGenerator(storageCostCalculator);
        List<Item> itemList = itemGenerator.listAllCombinations();

        FileWriter ItWrite = new FileWriter(ItFile.getAbsoluteFile());
        BufferedWriter bWrite = new BufferedWriter(ItWrite);

        for(Item items: itemList)
        {
            String itString = items.getId() + " " + items.getItemType() + " " + items.getItemSize() + " " + items.getCostOfStoragePerUnit() + " " + items.getDepartment() + " " + items.getStock() + " \n";
            bWrite.write(itString);
        }
        bWrite.close();
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
        List<Employee> employeeList = new ArrayList<>();
        readEmpFile(employeeList);

        List<Item> itemList = new ArrayList<Item>();
        readItemFile(itemList);

        int startYear, startMonth, startDay, endYear, endMonth, endDay;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter starting date (Month Day Year)");
        startMonth = scan.nextInt();
        startDay = scan.nextInt();
        startYear = scan.nextInt();

        System.out.println("Enter ending date (Month Day Year) [does not generate purchases ON the end date] [keep start to end date range below ~5 months or else earliest dates will cut off on terminal]");

        endMonth = scan.nextInt();
        endDay = scan.nextInt();
        endYear = scan.nextInt();

        Random rand = new Random();
        DateTime start = new DateTime(startYear,startMonth,startDay,0,0,0);
        DateTime end = new DateTime(endYear, endMonth, endDay,0,0,0);

        final ItemSelector itemSel = new ItemSelector(itemList, rand, itemList.size());
        final EmployeeSelector empSel = new EmployeeSelector(employeeList, rand);

        final PriceCalculator priceCalculator = new PriceCalculator();
        final PurchaseGenerator purchaseGen = new PurchaseGenerator(start, end, empSel, itemSel, priceCalculator);
        List<Purchase> purchaseList = purchaseGen.generatePurchases(itemList, employeeList);
    }

    private static List<Employee> readEmpFile(List<Employee> inputList) throws IOException
    {
        File empFile = new File("EmployeeList.txt");
        Scanner fileScan = new Scanner(empFile);

        if(!fileScan.hasNext())
        {
            System.out.println("File is empty, aborting");
            return null;
        }

        String val = null;

        while(fileScan.hasNext())
        {
            final Employee employee = new Employee(
                    Long.parseLong(fileScan.next(), 10),
                    fileScan.next(),
                    fileScan.next(),
                    Level.valueOf(fileScan.next()),
                    Department.valueOf(fileScan.next()));

            inputList.add(employee);
        }

        fileScan.close();

        return inputList;
    }

    private static List<Item> readItemFile(List<Item> inputList) throws IOException
    {
        File itFile = new File("ItemList.txt");
        Scanner fileScan = new Scanner(itFile);

        if(!fileScan.hasNext())
        {
            System.out.println("File is empty, aborting");
            return null;
        }

        String val = null;

        while(fileScan.hasNext())
        {
            final Item item = new Item(
                    Long.parseLong(fileScan.next(), 10),
                    ItemType.valueOf(fileScan.next()),
                    ItemSize.valueOf(fileScan.next()),
                    Double.parseDouble(fileScan.next()),
                    Department.valueOf(fileScan.next()),
                    Long.parseLong(fileScan.next(), 10));

            inputList.add(item);
        }

        fileScan.close();

        return inputList;
    }
}