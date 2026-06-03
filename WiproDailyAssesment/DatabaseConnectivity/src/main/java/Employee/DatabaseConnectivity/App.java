//package Employee.DatabaseConnectivity;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.util.Scanner;
//
//public class App {
//    public static void main(String[] args) {
//
//        Scanner s = new Scanner(System.in);
//
//        System.out.println("Enter employee details!");
//
//        System.out.print("Enter ID: ");
//        int eid = s.nextInt();
//
//        System.out.print("Enter Name: ");
//        String name = s.next();
//
////        System.out.print("Enter Salary: ");
////        String salary = s.next();   // because DB column is varchar
//
////        System.out.print("Enter Email: ");
////        String email = s.next();
//
//        System.out.print("Enter Department: ");
//        String dept = s.next();
//
//        String URL = "jdbc:mysql://localhost:3306/wiproj2ee";
//        String USERNAME = "root";
//        String PASSWORD = "root";
//
////        String query = "INSERT INTO employee (eid, emp_name, salary, email, dept) VALUES (?, ?, ?, ?, ?)";
//        	String query="update employee set emp_name=?,dept=? where eid=?";
//        try {
//            // Step 1: Load Driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Step 2: Create Connection
//            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//
//            // Step 3: Prepare Statement
//            PreparedStatement ps = conn.prepareStatement(query);
//
//            ps.setString(1, name);
//            ps.setString(2, dept);
////            ps.setString(3, salary);
////            ps.setString(4, email);
//            ps.setInt(3, eid);
//
//            // Step 4: Execute
//            int rows = ps.executeUpdate();
//
//            if (rows > 0) {
//                System.out.println("✅ Data inserted successfully!");
//            }
//            else
//            {
//            	System.out.println("No employee found");
//            }
//
//            // Step 5: Close
//            conn.close();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//}

package Employee.DatabaseConnectivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Enter employee details!");

        System.out.print("Enter ID: ");
        int eid = s.nextInt();

        System.out.print("Enter Name: ");
        String name = s.next();

        System.out.print("Enter Department: ");
        String dept = s.next();

        String query = "INSERT INTO employee (eid, emp_name, dept) VALUES (?, ?, ?)";

        try {
            Connection conn = DatabaseConn.getDB();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, eid);
            ps.setString(2, name);
            ps.setString(3, dept);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee inserted successfully!");
            } else {
                System.out.println("Insert failed!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}