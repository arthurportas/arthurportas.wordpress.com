package com.arthurportas.presentation.backingbeans;

import com.arthurportas.entities.Employee;
import com.arthurportas.services.EmployeeService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by arthurportas on 29/04/2017.
 */
@ManagedBean
@RequestScoped
public class EmployeeBean implements Serializable {

    private String firstName;

    private String lastName;

    private Employee employee;

    private String value;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String save() {

        Employee employee = new Employee();
        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);

        this.employee = getEmployeeService().create(employee);
        return "new-employee-result.xhtml";
    }

    public String search() {
        this.employee = getEmployeeService().findbyName(this.value);
        return "search-employee-result.xhtml";

    }

    private EmployeeService getEmployeeService() {
        return new EmployeeService();
    }
}
