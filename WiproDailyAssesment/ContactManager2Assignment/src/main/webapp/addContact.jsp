<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Contact</title>
    <style>
        body { font-family: Arial; margin: 30px; }
        input { display: block; margin: 10px 0; padding: 8px; width: 300px; }
        button { padding: 10px 20px; background: #4CAF50; 
                 color: white; border: none; cursor: pointer; }
        .error { color: red; }
    </style>
</head>
<body>
    <h2>➕ Add New Contact</h2>

    <!-- Show error if any -->
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <form action="ContactServlet" method="post">
        <input type="hidden" name="action" value="add" />
        
        Name:  <input type="text" name="name" required />
        Phone: <input type="text" name="phone" required />
        Email: <input type="email" name="email" required />
        
        <button type="submit">Add Contact</button>
    </form>
    <br>
    <a href="index.jsp">← Back</a>
</body>
</html>