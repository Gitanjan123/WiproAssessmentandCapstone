<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Signup</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .container {
        background: white;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
        width: 350px;
    }
    h2 { text-align: center; color: #333; }
    input {
        width: 100%;
        padding: 8px;
        margin: 8px 0;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    button {
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    button:hover { background-color: #45a049; }
    .error {
        color: red;
        text-align: center;
        font-weight: bold;
    }
</style>
</head>
<body>
<div class="container">
    <h2>Signup Form</h2>

    <!-- ✅ Show error if phone < 10 digits -->
    <% String error = (String) request.getAttribute("error");
       if (error != null) { %>
        <p class="error"><%= error %></p>
    <% } %>

    <form action="Signup" method="post">
        <input type="text"
            name="name"
            placeholder="Enter Name" required/>

        <input type="text"
            name="phone"
            placeholder="Enter Phone Number" required/>

        <input type="text"
            name="designation"
            placeholder="Enter Designation" required/>

        <input type="text"
            name="department"
            placeholder="Enter Department" required/>

        <input type="text"
            name="image"
            placeholder="Enter Image URL" required/>

        <button type="submit">Signup</button>
    </form>

    <br/>
    <a href="UserList">View All Users</a>
</div>
</body>
</html>