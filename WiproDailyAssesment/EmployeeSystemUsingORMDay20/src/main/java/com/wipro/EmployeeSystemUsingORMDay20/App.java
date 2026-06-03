package com.wipro.EmployeeSystemUsingORMDay20;

import java.util.List;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
            new AnnotationConfigApplicationContext(MyConfig.class);

        EmployeeDao dao = context.getBean(EmployeeDao.class);
        Scanner s = new Scanner(System.in);

        // INSERT
        System.out.println("Enter empId:");
        int empId = s.nextInt();
        System.out.println("Enter empName:");
        String empName = s.next();
        System.out.println("Enter empDepartment:");
        String empDepartment = s.next();
        System.out.println("Enter empSalary:");
        double empSalary = s.nextDouble();
        System.out.println("Enter empEmail:");
        String empEmail = s.next();

        Employee obj = new Employee();
        obj.setEmpId(empId);
        obj.setEmpName(empName);
        obj.setEmpDepartment(empDepartment);
        obj.setEmpSalary(empSalary);
        obj.setEmpEmail(empEmail);
        dao.insertEmployee(obj);

        // DISPLAY ALL
        System.out.println("\n--- All Employees ---");
        for (Employee e : dao.displayAllEmployees()) {
            System.out.println(e.getEmpId() + " | " + e.getEmpName()
                + " | " + e.getEmpDepartment()
                + " | " + e.getEmpSalary()
                + " | " + e.getEmpEmail());
            System.out.println("-------------------------");
        }

        // SEARCH
        System.out.println("\nEnter empId to search:");
        int searchId = s.nextInt();
        Employee found = dao.searchEmployee(searchId);
        if (found != null) {
            System.out.println("Name: "       + found.getEmpName());
            System.out.println("Department: " + found.getEmpDepartment());
            System.out.println("Salary: "     + found.getEmpSalary());
            System.out.println("Email: "      + found.getEmpEmail());
        }

        // UPDATE
        System.out.println("\nEnter empId to update salary:");
        int updateId = s.nextInt();
        System.out.println("Enter new salary:");
        double newSalary = s.nextDouble();
        dao.updateSalary(updateId, newSalary);

        // DISPLAY AFTER UPDATE
        System.out.println("\n--- After Update ---");
        for (Employee e : dao.displayAllEmployees()) {
            System.out.println(e.getEmpId() + " | " + e.getEmpName()
                + " | " + e.getEmpSalary());
            System.out.println("-------------------------");
        }

        // DELETE
        System.out.println("\nEnter empId to delete:");
        int deleteId = s.nextInt();
        dao.deleteEmployee(deleteId);

        // DISPLAY AFTER DELETE
        System.out.println("\n--- After Delete ---");
        for (Employee e : dao.displayAllEmployees()) {
            System.out.println(e.getEmpId() + " | " + e.getEmpName()
                + " | " + e.getEmpSalary());
            System.out.println("-------------------------");
        }

        s.close();
    }
}