package com.wipro.Day16;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserList")
public class UserListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String URL      = "jdbc:mysql://localhost:3306/day16";
            String USERNAME = "root";
            String PASSWORD = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connobj = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connobj.prepareStatement("select * from users");
            ResultSet result = statement.executeQuery();

            List<User> list = new ArrayList<>();
            while (result.next()) {
                User user = new User();
                user.setName(result.getString("name"));
                user.setPhone(result.getString("phone"));
                user.setImage(result.getString("image"));
                user.setDesignation(result.getString("designation"));
                user.setDepartment(result.getString("department"));
                list.add(user);
            }

            request.setAttribute("userList", list);
            RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/UserList.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            response.getWriter().append("Error: " + e);
        }
    }
}