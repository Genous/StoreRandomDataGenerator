package com.store.data.generator.utils;

import com.store.data.generator.models.Department;
import com.store.data.generator.models.Employee;
import com.store.data.generator.models.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import java.util.List;

public class EmployeeSelector
{
    public Employee getEmp(Item item)
    {
        int randomNum = rand.nextInt(employeeMap.get(item.getDepartment()).size());

        return employeeMap.get(item.getDepartment()).get(randomNum);
    }

    public EmployeeSelector(final List<Employee> employeeList, Random rand)
    {
        for(Department department : Department.values())
        {
            employeeMap.put(department, new ArrayList<>());
        }
        for (Employee employee : employeeList)
        {
            employeeMap.get(employee.getDepartment()).add(employee);
        }

        this.rand = rand;
    }

    private HashMap<Department, ArrayList<Employee>> employeeMap = new HashMap<>();
    private Random rand;
}