package com.hpe.restservice.controller;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hpe.restservice.model.Employee;
import com.hpe.restservice.model.EmployeeManager;
import com.hpe.restservice.model.Employees;
import com.hpe.restservice.service.IFileSytemStorage;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    // Automatically injected by Spring
    @Autowired
    private EmployeeManager employeeManager;
    
    @Autowired
    private IFileSytemStorage fileSystemStorageService;

    // GET endpoint to fetch all employees
    @GetMapping
    public Employees getAllEmployees() {
        return employeeManager.getEmployees();
    }

    // POST endpoint to add a new employee
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Employee addEmployee(@RequestBody Employee employee) {
        employeeManager.addEmployee(employee); // Add employee to the manager
        return employee; // Return the added employee (optional)
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
    
            // Deserialize the JSON into a list of Employee objects
            List<Employee> employees = objectMapper.readValue(inputStream, new TypeReference<List<Employee>>() {});
    
            // Add employees to employee manager
            Employees employeesWrapper = new Employees();
            employeesWrapper.addEmployees(employees);
            employeeManager.addEmployees(employeesWrapper.getEmployeeList());
    
            return ResponseEntity.ok("File uploaded and employees added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }
    
}
