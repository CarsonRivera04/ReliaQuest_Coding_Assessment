package com.challenge.api.service;

import com.challenge.api.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setup() {
        employeeService = new EmployeeService(); // uses mock data
    }

    @Test
    void testGetAllEmployees_returnsNonEmptyList() {
        List<Employee> employees = employeeService.getAllEmployees();
        assertFalse(employees.isEmpty(), "Employee list should not be empty");
    }

    @Test
    void testGetEmployeeByUuid_returnsCorrectEmployee() {
        Employee firstEmployee = employeeService.getAllEmployees().get(0);
        UUID uuid = firstEmployee.getUuid();

        Employee fetched = employeeService.getEmployeeByUuid(uuid);
        assertEquals(firstEmployee.getFullName(), fetched.getFullName());
    }

    @Test
    void testGetEmployeeByUuid_throwsExceptionIfNotFound() {
        UUID randomUuid = UUID.randomUUID();
        assertThrows(Exception.class, () -> employeeService.getEmployeeByUuid(randomUuid));
    }

    @Test
    void testCreateEmployee_addsNewEmployee() {
        int before = employeeService.getAllEmployees().size();
        Employee newEmp = employeeService.createEmployee(
                "Charlie", "Brown", 60000, 28, "QA Engineer", "charlie@example.com"
        );
        int after = employeeService.getAllEmployees().size();

        assertEquals(before + 1, after);
        assertNotNull(newEmp.getUuid());
    }
}