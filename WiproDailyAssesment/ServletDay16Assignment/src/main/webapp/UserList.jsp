<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wipro.Day16.User" %>
<!DOCTYPE html>
<html>
<head>
<title>User List</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        padding: 20px;
    }
    h2 { text-align: center; }
    .card-container {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        justify-content: center;
    }
    .card {
        background: white;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
        padding: 20px;
        width: 200px;
        text-align: center;
    }
    .card img {
        width: 100px;
        height: 100px;
        border-radius: 50%;
        object-fit: cover;
    }
    .card h3 { margin: 10px 0 5px; }
    .card p  { margin: 3px 0; color: #666; }
    .btn {
        display: block;
        text-align: center;
        margin: 20px auto;
        padding: 10px 20px;
        background: #4CAF50;
        color: white;
        text-decoration: none;
        border-radius: 4px;
        width: 150px;
    }
</style>
</head>
<body>
    <h2>All Users</h2>
    <a class="btn" href="Signup.jsp">Add New User</a>

    <div class="card-container">
    <%
        List<User> list = (List<User>) request.getAttribute("userList");
        if (list != null) {
            for (User user : list) {
    %>
        <div class="card">
            <img src="<%= user.getImage() %>"
                 alt="<%= user.getName() %>"/>
            <h3><%= user.getName() %></h3>
            <p><%= user.getDesignation() %></p>
            <p><%= user.getDepartment() %></p>
            <p><%= user.getPhone() %></p>
        </div>
    <%
            }
        } else {
    %>
        <p>No users found!</p>
    <%
        }
    %>
    </div>
</body>
</html>