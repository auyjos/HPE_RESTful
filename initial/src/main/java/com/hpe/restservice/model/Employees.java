package com.hpe.restservice.model;

import java.util.ArrayList;
import java.util.List;

public class Employees {

    private List<Employee> employeeList;

    public Employees() {
        this.employeeList = new ArrayList<>();
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    // Add a new employee
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    // Add multiple employees
    public void addEmployees(List<Employee> employees) {
        employeeList.addAll(employees);
    }
}
