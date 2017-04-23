package com.arthurportas.persistence;

import com.arthurportas.entities.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthurportas on 22/04/2017.
 */
public class EmployeeDAO {

    private static List<Employee> dataSource = new ArrayList<>();

    public Employee create(Employee employee){
        dataSource.add(employee);
        return employee;
    }

    public Employee findbyName(String value){

        for (Employee emp: dataSource) {
            if(emp.getFirstName().contains(value) || emp.getLastName().contains(value)){
                return emp;
            }
        }
        return null;
    }
}
