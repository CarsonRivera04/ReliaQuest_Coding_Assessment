package com.challenge.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.challenge.api.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees_returnsOk() throws Exception {
        mockMvc.perform(get("/api/v1/employee"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetEmployeeByUuid_returnsOk() throws Exception {
        var employee = employeeService.getAllEmployees().get(0);
        mockMvc.perform(get("/api/v1/employee/" + employee.getUuid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(employee.getFullName()));
    }

    @Test
    void testCreateEmployee_returnsCreated() throws Exception {
        String newEmployeeJson =
                """
            {
                "firstName": "Charlie",
                "lastName": "Brown",
                "salary": 60000,
                "age": 28,
                "jobTitle": "QA Engineer",
                "email": "charlie@example.com"
            }
            """;

        mockMvc.perform(post("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newEmployeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName").value("Charlie Brown"));
    }
}
