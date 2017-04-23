package com.arthurportas.services;

import com.arthurportas.entities.Employee;
import com.arthurportas.persistence.EmployeeDAO;

/**
 * Created by arthurportas on 22/04/2017.
 */
public class EmployeeService {

    public Employee create(Employee employee){
        return getEmployeeDAO().create(employee);
    }

    public Employee findbyName(String value){
        return getEmployeeDAO().findbyName(value);
    }

    private EmployeeDAO getEmployeeDAO() {
        return new EmployeeDAO();
    }
}
