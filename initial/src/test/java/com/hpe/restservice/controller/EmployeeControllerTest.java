package com.hpe.restservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpe.restservice.model.Employee;

import com.hpe.restservice.model.EmployeeManager;
import com.hpe.restservice.service.IFileSytemStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeControllerTest {

    private MockMvc mockMvc;

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
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        this.objectMapper = new ObjectMapper();
    }


    @Test
    void testAddEmployee() throws Exception {
        // Mock data
        Employee newEmployee = new Employee("EMP003", "Mark", "Anthony", "mark.anthony@example.com", "HR Specialist");

        // Perform POST request
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employee_id").value("EMP003"))
                .andExpect(jsonPath("$.first_name").value("Mark"));

        // Verify interaction
        verify(employeeManager, times(1)).addEmployee(any(Employee.class));
    }

    @Test
    void testUploadFile() throws Exception {
        // Mock file
        String fileContent = """
                [
                    {
                        "employee_id": "EMP004",
                        "first_name": "Jonathan",
                        "last_name": "Doe",
                        "email": "john.doe@example.com",
                        "title": "Software Engineer"
                    },
                    {
                        "employee_id": "EMP005",
                        "first_name": "Janisse",
                        "last_name": "Smith",
                        "email": "jane.smith@example.com",
                        "title": "Project Manager"
                    }
                ]
                """;
        MockMultipartFile file = new MockMultipartFile("file", "employees.json", "application/json", fileContent.getBytes());

        // Mock file storage behavior
        when(fileSystemStorageService.saveFile(any())).thenReturn("employees.json");
        when(fileSystemStorageService.loadFile("employees.json")).thenReturn(null); // Mock Resource not needed in this case

        // Perform POST request for file upload
        mockMvc.perform(multipart("/employees/upload").file(file))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to upload file."));
    }
}
