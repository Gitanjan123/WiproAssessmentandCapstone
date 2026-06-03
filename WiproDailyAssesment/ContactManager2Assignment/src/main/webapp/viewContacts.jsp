<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Contacts</title>
    <style>
        body { font-family: Arial; margin: 30px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background: #4CAF50; color: white; }
        tr:hover { background: #f5f5f5; }
        .success { color: green; font-weight: bold; }
        .error { color: red; font-weight: bold; }
        a { color: blue; margin: 0 5px; }
    </style>
</head>
<body>
    <h2>📋 Contact List</h2>

    <!-- Success/Error Message -->
    <c:if test="${not empty message}">
        <p class="success">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <!-- Contact Table -->
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>

        <c:choose>
            <c:when test="${empty contacts}">
                <tr>
                    <td colspan="5" style="text-align:center">
                        No contacts found!
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="contact" items="${contacts}">
                    <tr>
                        <td>${contact.id}</td>
                        <td>${contact.name}</td>
                        <td>${contact.phone}</td>
                        <td>${contact.email}</td>
                        <td>
                            <a href="ContactServlet?action=edit&id=${contact.id}">
                                ✏️ Edit
                            </a>
                            <a href="ContactServlet?action=delete&id=${contact.id}"
                               onclick="return confirm('Delete this contact?')">
                                🗑️ Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>

    <br>
    <a href="addContact.jsp">➕ Add New Contact</a> |
    <a href="index.jsp">🏠 Home</a>
</body>
</html>