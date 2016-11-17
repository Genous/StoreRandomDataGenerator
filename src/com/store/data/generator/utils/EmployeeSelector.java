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
            switch(employee.getLevel()) // increase chances of higher level employees selling more items
            {
                case ONE: // level ones sell the least
                    for(int i = 0; i < 1; i++)
                    {
                        employeeMap.get(employee.getDepartment()).add(employee);

                    }
                    break;
                case TWO: // level twos sell a little more than ones
                    for(int i = 0; i < 4; i++)
                    {
                        employeeMap.get(employee.getDepartment()).add(employee);

                    }
                    break;
                case THREE: // level threes sell a decent amount more than twos
                            // (make it so level threes are most "efficient" raise to have employees at,
                            // later "raises" see diminishing returns in terms of employee performance
                    for(int i = 0; i < 8; i++)
                    {
                        employeeMap.get(employee.getDepartment()).add(employee);

                    }
                    break;
                case FOUR: // level fours sell a little more than threes
                    for(int i = 0; i < 15; i++)
                    {
                        employeeMap.get(employee.getDepartment()).add(employee);

                    }
                    break;
                case FIVE: // level fives sell the same as fours
                    for(int i = 0; i < 16; i++)
                    {
                        employeeMap.get(employee.getDepartment()).add(employee);

                    }
                    break;
                default:
                    employeeMap.get(employee.getDepartment()).add(employee);
                    break;
            }
        }

        this.rand = rand;
    }

    private HashMap<Department, ArrayList<Employee>> employeeMap = new HashMap<>();
    private Random rand;
}