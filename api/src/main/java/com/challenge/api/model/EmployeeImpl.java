package com.challenge.api.model;

import java.time.Instant;
import java.util.UUID;

/**
 * Implementation of the Employee interface.
 */
public class EmployeeImpl implements Employee {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

    /**
     * Constructor for creating a new Employee with required fields.
     *
     * @param firstName Employee's first name
     * @param lastName Employee's last name
     * @param salary Employee's salary
     * @param age Employee's age
     * @param jobTitle Employee's job title
     * @param email Employee's email
     * @param contractHireDate Date of hire
     */
    public EmployeeImpl(String firstName, String lastName, Integer salary, Integer age,
                        String jobTitle, String email, Instant contractHireDate) {
        this.uuid = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.salary = salary;
        this.age = age;
        this.jobTitle = jobTitle;
        this.email = email;
        this.contractHireDate = contractHireDate;
        this.contractTerminationDate = null;
    }

    @Override
    public UUID getUuid() { return uuid; }

    @Override
    public void setUuid(UUID uuid) { this.uuid = uuid; }

    @Override
    public String getFirstName() { return firstName; }

    @Override
    public void setFirstName(String name) {
        this.firstName = name;
        updateFullName();
    }

    @Override
    public String getLastName() { return lastName; }

    @Override
    public void setLastName(String name) {
        this.lastName = name;
        updateFullName();
    }

    @Override
    public String getFullName() { return fullName; }

    @Override
    public void setFullName(String name) { this.fullName = name; }

    @Override
    public Integer getSalary() { return salary; }

    @Override
    public void setSalary(Integer salary) { this.salary = salary; }

    @Override
    public Integer getAge() { return age; }

    @Override
    public void setAge(Integer age) { this.age = age; }

    @Override
    public String getJobTitle() { return jobTitle; }

    @Override
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    @Override
    public String getEmail() { return email; }

    @Override
    public void setEmail(String email) { this.email = email; }

    @Override
    public Instant getContractHireDate() { return contractHireDate; }

    @Override
    public void setContractHireDate(Instant date) { this.contractHireDate = date; }

    @Override
    public Instant getContractTerminationDate() { return contractTerminationDate; }

    @Override
    public void setContractTerminationDate(Instant date) { this.contractTerminationDate = date; }

    /**
     * Helper method to update the full name when first or last name changes.
     */
    private void updateFullName() {
        this.fullName = (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
    }
}