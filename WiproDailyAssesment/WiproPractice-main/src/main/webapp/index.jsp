<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Manager</title>
    <style>
        body   { font-family:Arial; text-align:center; padding:50px; background:#f4f4f4; }
        h1     { color:#333; }
        .btn   { background:#1a6fbf; color:white; padding:12px 24px;
                 text-decoration:none; border-radius:8px; font-size:16px; }
        .btn:hover { background:#0d4f8c; }
    </style>
</head>
<body>
    <h1>Welcome to Contact Manager!</h1>
    <p>Manage your contacts easily</p>
    <br/>
    <a href="addContact.jsp" class="btn">Add Contact</a>
    &nbsp;&nbsp;
    <a href="ContactServlet" class="btn">View All Contacts</a>
</body>
</html>