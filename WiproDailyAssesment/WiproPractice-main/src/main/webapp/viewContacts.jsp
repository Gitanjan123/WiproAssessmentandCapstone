<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, mypackage.Contact" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Contacts</title>
    <style>
        body  { font-family:Arial; padding:30px; background:#f4f4f4; }
        h2    { text-align:center; }
        table { width:80%; margin:auto; border-collapse:collapse;
                background:white; border-radius:10px; overflow:hidden; }
        th    { background:#1a6fbf; color:white; padding:12px; }
        td    { padding:10px; text-align:center; border-bottom:1px solid #ddd; }
        tr:hover { background:#f0f0f0; }
        .btn  { display:block; width:200px; margin:20px auto;
                background:#1a6fbf; color:white; padding:12px;
                text-align:center; border-radius:8px; text-decoration:none; }
        .btn:hover { background:#0d4f8c; }
        .empty { text-align:center; color:#888; padding:20px; }
    </style>
</head>
<body>
    <h2>All Contacts</h2>

    <%
        ArrayList<Contact> contacts =
            (ArrayList<Contact>) request.getAttribute("contacts");
    %>

    <table>
        <tr>
            <th>No.</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Email</th>
        </tr>

        <% if (contacts == null || contacts.isEmpty()) { %>
            <tr>
                <td colspan="4" class="empty">No contacts added yet!</td>
            </tr>
        <% } else {
               int i = 1;
               for (Contact c : contacts) { %>
            <tr>
                <td><%= i++ %></td>
                <td><%= c.getName()  %></td>
                <td><%= c.getPhone() %></td>
                <td><%= c.getEmail() %></td>
            </tr>
        <% } } %>
    </table>

    <a href="addContact.jsp" class="btn">Add More Contacts</a>
    <a href="index.jsp"      class="btn">Back to Home</a>
</body>
</html>