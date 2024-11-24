package com.hpe.restservice.model;



import java.util.ArrayList;
import java.util.List;

public class Employees {

    private List<Employee> employeeList;

    // Constructor initializes the list
    public Employees() {
        this.employeeList = new ArrayList<>();
    }

    // Getters and Setters
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    // Add an employee to the list
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }
}
