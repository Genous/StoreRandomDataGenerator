package com.store.data.generator.generators;

import com.store.data.generator.models.Employee;
import com.store.data.generator.models.Item;
import com.store.data.generator.models.Purchase;
import com.store.data.generator.utils.DateSelector;
import com.store.data.generator.utils.EmployeeSelector;
import com.store.data.generator.utils.ItemSelector;
import org.joda.time.DateTime;

import java.util.List;
import java.util.LinkedList;
import com.store.data.generator.models.Department;

/**
    1- Items are ONLY sold by employees in the same department
    2- Employees with higher levels sell slightly more items
    3- Number of items stolen cannot exceed number of items available
    4- Purchases have to be at normal operation hours (9 am to 5 pm)
 */

public class PurchaseGenerator
{
    public LinkedList<Purchase> generatePurchases(final List<Item> items, final List<Employee> employees)
    {
        // date >> item >> employee
        // 3 methods. get Date / get Item/ get Employee

        final LinkedList<Purchase> purchaseList = new LinkedList<>();

        int purchaseId = 1;

        for(Department department : Department.values())
        {
            for(int i = 0; i < department.getTotalPurchases(); i++)
            {
                final DateTime date = dateSel.getDate();
                final Item item = itemSel.getItem();
                final Employee employee = empSel.getEmp(item);
                final Purchase purchase = new Purchase(
                                            purchaseId,
                                            5,
                                            date,
                                            item.getId(),
                                            employee.getId());
                 purchaseList.add(purchase);
                 purchaseId++;
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
