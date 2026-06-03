package com.wipro.Day16;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {
	
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name        = request.getParameter("name");
        String phone       = request.getParameter("phone");
        String designation = request.getParameter("designation");
        String department  = request.getParameter("department");
        String image       = request.getParameter("image");

        try {
            // ✅ Phone validation
            if (phone.length() < 10) {
                throw new Exception("Phone number must be at least 10 digits!");
            }

            // ✅ DB Connection
            String URL      = "jdbc:mysql://localhost:3306/day16";
            String USERNAME = "root";
            String PASSWORD = "root";
            String query    = "insert into users(name, phone, image, designation, department) values(?,?,?,?,?)";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connobj = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connobj.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, image);
            statement.setString(4, designation);
            statement.setString(5, department);
            statement.executeUpdate();

            // ✅ Redirect to list page
            RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/UserList.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // ✅ Show error message
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/Signup.jsp");
            dispatcher.forward(request, response);
        }
    }
}