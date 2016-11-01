package com.store.data.generator.generators;

import com.store.data.generator.models.Employee;
import com.store.data.generator.models.Item;
import com.store.data.generator.models.Purchase;
import org.joda.time.DateTime;

import java.util.LinkedList;

/**
    1- Items are ONLY sold by employees in the same department
    2- Employees with higher levels sell slightly more items
    3- Number of items stolen cannot exceed number of items available
    4- Purchases have to be at normal operation hours (9 am to 5 pm)
 */
public class PurchaseGenerator
{
    public LinkedList<Purchase> generatePurchases(final LinkedList<Item> items, final LinkedList<Employee> employees)
    {
        // date >> item >> employee
        // 3 methods. getData // get Item/ get employee

    }
}
