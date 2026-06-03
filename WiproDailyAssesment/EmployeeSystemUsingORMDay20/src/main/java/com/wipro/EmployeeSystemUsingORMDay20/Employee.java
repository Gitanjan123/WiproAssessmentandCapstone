package com.wipro.EmployeeSystemUsingORMDay20;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    int empId;
    String empName;
    String empDepartment;
    double empSalary;
    String empEmail;

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public String getEmpDepartment() { return empDepartment; }
    public void setEmpDepartment(String empDepartment) { this.empDepartment = empDepartment; }

    public double getEmpSalary() { return empSalary; }
    public void setEmpSalary(double empSalary) { this.empSalary = empSalary; }

    public String getEmpEmail() { return empEmail; }
    public void setEmpEmail(String empEmail) { this.empEmail = empEmail; }
}