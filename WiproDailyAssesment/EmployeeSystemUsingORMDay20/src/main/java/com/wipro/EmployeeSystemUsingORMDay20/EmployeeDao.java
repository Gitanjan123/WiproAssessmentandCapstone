package com.wipro.EmployeeSystemUsingORMDay20;

import java.util.List;

public interface EmployeeDao {
    void insertEmployee(Employee obj);
    Employee searchEmployee(int empId);
    void updateSalary(int empId, double newSalary);
    void deleteEmployee(int empId);
    List<Employee> displayAllEmployees();
}