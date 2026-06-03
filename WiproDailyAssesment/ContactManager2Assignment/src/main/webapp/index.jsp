<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Manager</title>
    <style>
        body { font-family: Arial; text-align: center; margin-top: 50px; }
        a { display: inline-block; margin: 10px; padding: 10px 20px;
            background: #4CAF50; color: white; text-decoration: none;
            border-radius: 5px; }
        a:hover { background: #45a049; }
    </style>
</head>
<body>
    <h1>📒 Contact Manager</h1>
    <a href="addContact.jsp">➕ Add Contact</a>
    <a href="ContactServlet?action=view">📋 View Contacts</a>
</body>
</html>