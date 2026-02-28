package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

/**
 * Service layer responsible for managing Employee objects.
 * Holds mock data in-memory for demonstration purposes.
 */
@Service
public class EmployeeService {

    // In-memory store to simulate persistence
    private final Map<UUID, Employee> employeeStore = new HashMap<>();

    /**
     * Constructor initializes mock employees for testing.
     */
    public EmployeeService() {
        Employee e1 = new EmployeeImpl("Alice", "Smith", 70000, 30, "Software Engineer",
                "alice.smith@example.com", Instant.now());
        Employee e2 = new EmployeeImpl("Bob", "Johnson", 85000, 35, "Product Manager",
                "bob.johnson@example.com", Instant.now());

        employeeStore.put(e1.getUuid(), e1);
        employeeStore.put(e2.getUuid(), e2);
    }

    /**
     * Returns all employees in the system.
     *
     * @return List of employees
     */
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeStore.values());
    }

    /**
     * Returns a single employee by UUID.
     *
     * @param uuid Employee UUID
     * @return Employee object
     * @throws NoSuchElementException if employee is not found
     */
    public Employee getEmployeeByUuid(UUID uuid) {
        Employee employee = employeeStore.get(uuid);
        if (employee == null) {
            throw new NoSuchElementException("Employee not found for UUID: " + uuid);
        }
        return employee;
    }

    /**
     * Creates a new employee and stores it in-memory.
     *
     * @param firstName Employee first name
     * @param lastName Employee last name
     * @param salary Employee salary
     * @param age Employee age
     * @param jobTitle Employee job title
     * @param email Employee email
     * @return Newly created Employee
     */
    public Employee createEmployee(String firstName, String lastName, Integer salary, Integer age,
                                   String jobTitle, String email) {
        Employee newEmployee = new EmployeeImpl(firstName, lastName, salary, age, jobTitle, email, Instant.now());
        employeeStore.put(newEmployee.getUuid(), newEmployee);
        return newEmployee;
    }
}