//package com.wipro.EmployeeSystem;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import Employee.DatabaseConnectivity.DatabaseConn;
//import Employee.DatabaseConnectivity.Employee;
//
//public class Test {
//
//        public static void main(String args[])
//        {
//        	Scanner sc=new Scanner(System.in);
//        	Test t=new Test();
//        	
//        	System.out.println("1.Get All Employees");
//        	System.out.println("2. Get Employee by ID");
//        	
//        	int choice=sc.nextInt();
//        	
//        	if(choice==1)
//        	{
//        		t.
//        	}  	
//        }
//        void getEmployees()
//        {
//        	List<Employee> list=new ArrayList<>();
//        	try
//        	{
//        		String query="select * from employee";
//        		
//        		Connection conn=DatabaseConn.getDB();
//        		Statement stmt=conn.createStatement();
//        		ResultSet rs=stmt.executeQuery(query);
//        		
//        		
//        		while(rs.next())
//        		{
//        			Employee obj=new Employee();
//        			obj.setEid(rs.getInt("eid"));
//        			obj.setEname(rs.getString("emp_name"));
//        			obj.setDept(rs.getString("dept"));
//        			obj.setSalary(rs.getString("salary"));
//        			list.add(obj);
//        		}
//        		for(Employee e:list)
//        		{
//        			System.out.println(e);
//        		}
//        	}
//        	catch(Exception e)
//        	{
//        		System.out.println(e);
//        	}
//        }
//        
//        void getEmployeeData(int inputid)
//        {
//        	try
//        	{
//        		String query="Select * from employee where eid="+inputid;
//        		
//        		Connection conn=DatabaseConn.getDB();
//        		
//        		Statement stmt=conn.createStatement();
//        		ResultSet rs=stmt.executeQuery(query);
//        		
//        		if(rs.next())
//        		{
//        			Employee obj=new Employee();
//        			obj.setEid(rs.getInt("eid"));
//        			obj.setEname(rs.getString("emp_name"));
//        			obj.setDept(rs.getString("dept"));
//        			obj.setSalary(rs.getString("salary"));
//        			System.out.println(obj);
//        		}
//        		else
//        		{
//        			System.out.println("Employee not found");
//        		}
//        	}
//        	catch(Exception e)
//        	{
//        		System.out.println(e);
//        	}
//        }
//}

// upper part is for sql 
package Employee.DatabaseConnectivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Employee.DatabaseConnectivity.DatabaseConn;
import Employee.DatabaseConnectivity.Employee;

public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Test t = new Test();

        System.out.println("1. Get All Employees");
        System.out.println("2. Get Employee by ID");

        int choice = sc.nextInt();

        if (choice == 1) {
            t.getEmployees();
        } else if (choice == 2) {
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            t.getEmployeeData(id);
        } else {
            System.out.println("Invalid choice");
        }
    }

    void getEmployees() {
        List<Employee> list = new ArrayList<>();
        try {
            String query = "select * from employee";
            Connection conn = DatabaseConn.getDB();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Employee obj = new Employee();
                obj.setEid(rs.getInt("eid"));
                obj.setEname(rs.getString("emp_name"));
                obj.setDept(rs.getString("dept"));
                obj.setSalary(rs.getString("salary"));
                list.add(obj);
            }

            if (list.isEmpty()) {
                System.out.println("No employees found.");
            } else {
                for (Employee e : list) {
                    System.out.println(e);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void getEmployeeData(int inputid) {
        try {
            String query = "select * from employee where eid=" + inputid;
            Connection conn = DatabaseConn.getDB();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                Employee obj = new Employee();
                obj.setEid(rs.getInt("eid"));
                obj.setEname(rs.getString("emp_name"));
                obj.setDept(rs.getString("dept"));
                obj.setSalary(rs.getString("salary"));
                System.out.println(obj);
            } else {
                System.out.println("Employee not found with ID: " + inputid);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}