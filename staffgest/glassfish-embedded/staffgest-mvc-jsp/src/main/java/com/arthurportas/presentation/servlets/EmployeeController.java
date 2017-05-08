package com.arthurportas.presentation.servlets;

import com.arthurportas.entities.Employee;
import com.arthurportas.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by arthurportas on 22/04/2017.
 */
@WebServlet(urlPatterns = "/employee")
public class EmployeeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String value = req.getParameter("value");
        Employee employee = getEmployeeService().findbyName(value);
        req.setAttribute("emp", employee);
        req.getRequestDispatcher("search-employee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        employee = getEmployeeService().create(employee);
        req.setAttribute("emp", employee);
        req.getRequestDispatcher("new-employee.jsp").forward(req, resp);
    }

    private EmployeeService getEmployeeService() {
        return new EmployeeService();
    }
}
