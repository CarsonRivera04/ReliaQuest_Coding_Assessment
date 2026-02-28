package com.challenge.api.controller;

import com.challenge.api.model.Employee;
import com.challenge.api.service.EmployeeService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * REST Controller exposing Employee management endpoints.
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Returns a list of all employees.
     *
     * @return List of Employee objects
     */
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * Returns a single employee by UUID.
     *
     * @param uuid Employee UUID
     * @return Employee object
     * @throws ResponseStatusException if employee is not found
     */
    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(@PathVariable UUID uuid) {
        try {
            return employeeService.getEmployeeByUuid(uuid);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Creates a new employee.
     *
     * @param requestBody JSON body containing employee attributes:
     *                    firstName, lastName, salary, age, jobTitle, email
     * @return Newly created Employee object
     * @throws ResponseStatusException if the request body is invalid
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Map<String, Object> requestBody) {
        try {
            return employeeService.createEmployee(
                    (String) requestBody.get("firstName"),
                    (String) requestBody.get("lastName"),
                    (Integer) requestBody.get("salary"),
                    (Integer) requestBody.get("age"),
                    (String) requestBody.get("jobTitle"),
                    (String) requestBody.get("email"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid employee data");
        }
    }
}
