package com.hpe.restservice.model;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeManager {

    private Employees employees;

    public EmployeeManager() {
        this.employees = new Employees();
        // Hard-coding some employee data
        employees.addEmployee(new Employee("EMP001", "Jonathan", "Doe", "john.doe@example.com", "Software Engineer"));
        employees.addEmployee(new Employee("EMP002", "Janisse", "Smith", "jane.smith@example.com", "Project Manager"));
        employees.addEmployee(new Employee("EMP003", "Mark", "Anthony", "mark.anthony@example.com", "HR Specialist"));
    }

    public Employees getEmployees() {
        return employees;
    }

    // Method to add a single employee
    public void addEmployee(Employee employee) {
        employees.addEmployee(employee);
    }

    // Method to add multiple employees
    public void addEmployees(List<Employee> newEmployees) {
        employees.addEmployees(newEmployees);
    }
}
