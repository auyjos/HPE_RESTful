package com.hpe.restservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.restservice.model.EmployeeManager;
import com.hpe.restservice.model.Employees;

@RestController
public class EmployeeController {

    private EmployeeManager employeeManager;

    // Constructor to initialize the EmployeeManager
    public EmployeeController() {
        this.employeeManager = new EmployeeManager();
    }

    // GET endpoint to fetch all employees
    @GetMapping("/employees")
    public Employees getAllEmployees() {
        return employeeManager.getEmployees();
    }
}
