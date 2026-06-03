<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Contact</title>
    <style>
        body { font-family: Arial; margin: 30px; }
        input { display: block; margin: 10px 0; padding: 8px; width: 300px; }
        button { padding: 10px 20px; background: #2196F3;
                 color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <h2>✏️ Edit Contact</h2>

    <form action="ContactServlet" method="post">
        <input type="hidden" name="action" value="update" />
        <input type="hidden" name="id" value="${contact.id}" />

        Name:
        <input type="text" name="name" value="${contact.name}" required />
        Phone:
        <input type="text" name="phone" value="${contact.phone}" required />
        Email:
        <input type="email" name="email" value="${contact.email}" required />

        <button type="submit">Update Contact</button>
    </form>
    <br>
    <a href="ContactServlet?action=view">← Back to Contacts</a>
</body>
</html>