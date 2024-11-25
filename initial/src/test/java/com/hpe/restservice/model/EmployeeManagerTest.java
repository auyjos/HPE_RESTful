package com.hpe.restservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class EmployeeManagerTest {

    private EmployeeManager employeeManager;

    @BeforeEach
    public void setUp() {
        employeeManager = new EmployeeManager();
    }

    @Test
    public void testGetEmployees() {
        Employees employees = employeeManager.getEmployees();
        assertNotNull(employees, "Employees object should not be null");
        assertEquals(3, employees.getEmployeeList().size(), "Initial employee count should be 3");
    }

    @Test
    public void testAddEmployee() {
        Employee newEmployee = new Employee("EMP004", "Anna", "Taylor", "anna.taylor@example.com", "Designer");
        employeeManager.addEmployee(newEmployee);

        Employees employees = employeeManager.getEmployees();
        assertEquals(4, employees.getEmployeeList().size(), "Employee count should increase to 4");
        assertEquals("Anna", employees.getEmployeeList().get(3).getFirst_name(), "The new employee's first name should match");
    }

    @Test
    public void testAddMultipleEmployees() {
        Employee emp1 = new Employee("EMP005", "John", "Doe", "john.doe@example.com", "Analyst");
        Employee emp2 = new Employee("EMP006", "Emily", "Clark", "emily.clark@example.com", "Consultant");

        employeeManager.addEmployees(List.of(emp1, emp2));

        Employees employees = employeeManager.getEmployees();
        assertEquals(5, employees.getEmployeeList().size(), "Employee count should increase to 5");
        assertEquals("Emily", employees.getEmployeeList().get(4).getFirst_name(), "The last added employee's first name should match");
    }
}
