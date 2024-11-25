package com.hpe.restservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpe.restservice.model.Employee;
import com.hpe.restservice.model.EmployeeManager;
import com.hpe.restservice.model.Employees;
import com.hpe.restservice.service.IFileSytemStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeManager employeeManager;

    @Mock
    private IFileSytemStorage fileSystemStorageService;

    @InjectMocks
    private EmployeeController employeeController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllEmployees() {
        // Mock the return value of EmployeeManager
        Employees mockEmployees = new Employees();
        mockEmployees.addEmployee(new Employee("EMP001", "John", "Doe", "john.doe@example.com", "Developer"));
        when(employeeManager.getEmployees()).thenReturn(mockEmployees);

        // Call the endpoint
        Employees result = employeeController.getAllEmployees();

        // Assertions
        assertNotNull(result, "The result should not be null");
        assertEquals(1, result.getEmployeeList().size(), "The employee list size should be 1");
        assertEquals("John", result.getEmployeeList().get(0).getFirst_name(), "The first name should match");
    }

    @Test
    void testAddEmployee() {
        // Prepare mock input
        Employee newEmployee = new Employee("EMP002", "Jane", "Smith", "jane.smith@example.com", "Manager");

        // Call the endpoint
        Employee result = employeeController.addEmployee(newEmployee);

        // Verify the interaction and assert the result
        verify(employeeManager, times(1)).addEmployee(newEmployee);
        assertNotNull(result, "The result should not be null");
        assertEquals("Jane", result.getFirst_name(), "The first name should match");
    }

    @Test
    void testUploadFile() throws Exception {
        // Mock the uploaded file
        String mockJson = "[{\"employee_id\": \"EMP003\", \"first_name\": \"Alice\", \"last_name\": \"Brown\", \"email\": \"alice.brown@example.com\", \"title\": \"HR Specialist\"}]";
        MockMultipartFile mockFile = new MockMultipartFile("file", "employees.json", "application/json", mockJson.getBytes());

        // Mock the file save and load behavior
        String fileName = "employees.json";
        when(fileSystemStorageService.saveFile(mockFile)).thenReturn(fileName);
        when(fileSystemStorageService.loadFile(fileName)).thenReturn(new org.springframework.core.io.ByteArrayResource(mockJson.getBytes()));

        // Mock the EmployeeManager behavior
        doNothing().when(employeeManager).addEmployees(anyList());

        // Call the endpoint
        ResponseEntity<String> response = employeeController.uploadFile(mockFile);

        // Verify interactions and assert the response
        verify(fileSystemStorageService, times(1)).saveFile(mockFile);
        verify(fileSystemStorageService, times(1)).loadFile(fileName);
        verify(employeeManager, times(1)).addEmployees(anyList());

        assertEquals(HttpStatus.OK, response.getStatusCode(), "The status code should be 200 OK");
        assertEquals("File uploaded, saved, and employees added successfully!", response.getBody(), "The response message should match");
    }

    @Test
    void testUploadFileWithError() throws Exception {
        // Mock an exception during file save
        MockMultipartFile mockFile = new MockMultipartFile("file", "employees.json", "application/json", "invalid json".getBytes());
        when(fileSystemStorageService.saveFile(mockFile)).thenThrow(new RuntimeException("File storage error"));

        // Call the endpoint
        ResponseEntity<String> response = employeeController.uploadFile(mockFile);

        // Verify interactions and assert the response
        verify(fileSystemStorageService, times(1)).saveFile(mockFile);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "The status code should be 500");
        assertEquals("Failed to upload file.", response.getBody(), "The response message should indicate failure");
    }
}
