package com.store.data.generator.utils;

import com.store.data.generator.models.Employee;
import com.store.data.generator.models.Item;
import com.store.data.generator.utils.ItemSelector;
import org.joda.time.DateTime;

import java.util.List;

public class EmployeeSelector
{
    public Employee generateEmp(DateTime date, Item item){
        for (Employee employee : employeeList){
            if(employee.getDepartment() == item.getDepartment())
            {
                // random generate shit
            }
        }
    }

    public EmployeeSelector(final List<Employee> employeeList)
    {
        this.employeeList = employeeList;
    }

    private List<Employee> employeeList;
}