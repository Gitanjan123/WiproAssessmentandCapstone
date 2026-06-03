<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>

    <% if (request.getParameter("error") != null) { %>
        <p style="color:red;">Invalid username or password.</p>
    <% } %>

    <form action="/login" method="post">
        <label>Username: <input type="text" name="name" required/></label><br/><br/>
        <label>Password: <input type="password" name="password" required/></label><br/><br/>
        <button type="submit">Login</button>
    </form>
</body>
</html>