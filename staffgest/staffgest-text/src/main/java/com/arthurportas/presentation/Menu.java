package com.arthurportas.presentation;

import com.arthurportas.entities.Employee;
import com.arthurportas.services.EmployeeService;

import java.util.Scanner;

/**
 * Created by arthurportas on 22/04/2017.
 */
public class Menu {

    private Scanner input = new Scanner(System.in);

    public void display() {
        System.out.println("-- Actions --");
        System.out.println(
                "Select an option: \n" +
                    "  1) Create Employee\n" +
                    "  2) Search Employee\n" +
                    "  3) Exit\n "
        );

        int selection = input.nextInt();
        input.nextLine();

        switch (selection) {
            case 1:
                this.createEmployee();
                break;
            case 2:
                this.searchEmployee();
                break;
            case 3:
                this.exit();
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    private void createEmployee() {
        System.out.println("Enter firstname:");
        String firstName = input.next();
        input.nextLine();
        System.out.println("Enter lastname:");
        String lastName = input.next();
        input.nextLine();
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        System.out.println("Creating Employee...");
        Employee employee1 = getEmployeeService().create(employee);
        System.out.println("New Employee saved!");
        System.out.println(employee);
    }

    private void searchEmployee() {
        System.out.println("Enter search criteria:");
        String value = input.next();
        input.nextLine();

        System.out.println("Searching Employee...");
        Employee employee = getEmployeeService().findbyName(value);
        System.out.println("Search results:");
        System.out.println(employee);
    }

    private void exit() {
        System.out.println("Exiting...");
        System.exit(1);
    }

    private EmployeeService getEmployeeService() {
        return new EmployeeService();
    }
}
