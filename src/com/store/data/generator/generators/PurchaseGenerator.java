package com.store.data.generator.generators;

import com.store.data.generator.models.Employee;
import com.store.data.generator.models.Item;
import com.store.data.generator.models.Purchase;
import com.store.data.generator.utils.DateSelector;
import com.store.data.generator.utils.EmployeeSelector;
import com.store.data.generator.utils.ItemSelector;

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
        // 3 methods. get Date / get Item/ get Employee

        final LinkedList<Purchase> purchaseList = new LinkedList<>();

        int purchaseId = 1;

        for()
        {
            for()
            {
                for()
                {

                }
            }
         }

        return purchaseList;
    }

    public PurchaseGenerator(final DateSelector dateSel, final EmployeeSelector empSel, final ItemSelector itemSel)
    {
        this.dateSel = dateSel;
        this.empSel = empSel;
        this.itemSel = itemSel;
    }

    private final DateSelector dateSel;
    private final EmployeeSelector empSel;
    private final ItemSelector itemSel;
}
