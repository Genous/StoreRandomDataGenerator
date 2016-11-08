package com.store.data.generator.utils;

import com.store.data.generator.models.Employee;
import com.store.data.generator.models.Item;
import java.util.ArrayList;
import java.util.Random;

import java.util.List;

public class EmployeeSelector
{
    public Employee getEmp(Item item){
        ArrayList<Employee> empList = new ArrayList<Employee>();
        for (Employee employee : employeeList){
            if(employee.getDepartment() == item.getDepartment())
            {
                empList.add(employee);
            }
        }
        int randomNum = rand.nextInt(empList.size());

        return empList.get(randomNum);
    }

    public EmployeeSelector(final List<Employee> employeeList, Random rand)
    {
        this.employeeList = employeeList;
        this.rand = rand;
    }

    private List<Employee> employeeList;
    private Random rand;
}