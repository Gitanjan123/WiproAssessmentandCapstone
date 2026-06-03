<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Profile</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: Arial; display: flex;
           justify-content: center; align-items: center;
           height: 100vh; background: #f0f2f5; }
    .box { background: white; padding: 40px;
           border-radius: 10px;
           box-shadow: 0 2px 10px rgba(0,0,0,0.1);
           width: 350px; text-align: center; }
    img { width: 150px; height: 150px; border-radius: 50%;
          border: 4px solid #4CAF50; margin-bottom: 15px; }
    h2 { color: #333; margin-bottom: 10px; }
    .designation { background: #4CAF50; color: white;
                   padding: 5px 20px; border-radius: 20px;
                   display: inline-block; font-size: 14px; }
    .btn { display: block; margin-top: 20px; padding: 10px;
           background: #f44336; color: white; border-radius: 5px;
           text-decoration: none; }
    .btn:hover { background: #d32f2f; }
</style>
</head>
<body>
<div class="box">
    <img src="${imageurl}" alt="Profile Photo"/>
    <h2>${username}</h2>
    <p class="designation">${designation}</p>
    <a href="login" class="btn">Logout</a>
</div>
</body>
</html>