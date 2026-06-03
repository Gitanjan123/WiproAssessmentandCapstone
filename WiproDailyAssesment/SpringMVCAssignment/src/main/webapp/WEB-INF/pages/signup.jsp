<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Signup</title>
<style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { font-family: Arial; display: flex;
           justify-content: center; align-items: center;
           height: 100vh; background: #f0f2f5; }
    .box { background: white; padding: 40px;
           border-radius: 10px;
           box-shadow: 0 2px 10px rgba(0,0,0,0.1);
           width: 350px; }
    h2 { text-align: center; color: #333; margin-bottom: 20px; }
    input { width: 100%; padding: 10px; margin: 10px 0;
            border: 1px solid #ddd; border-radius: 5px; }
    button { width: 100%; padding: 10px; background: #2196F3;
             color: white; border: none; border-radius: 5px;
             cursor: pointer; font-size: 16px; margin-top: 10px; }
    button:hover { background: #1976D2; }
    .link { text-align: center; margin-top: 15px; }
    a { color: #2196F3; text-decoration: none; }
</style>
</head>
<body>
<div class="box">
    <h2>Sign Up</h2>
    <form action="signup" method="post">
        <input type="text" name="username" 
               placeholder="Username" required/>
        <input type="email" name="email" 
               placeholder="Email" required/>
        <input type="password" name="password" 
               placeholder="Password" required/>
        <button type="submit">Sign Up</button>
    </form>
    <div class="link">
        Already have an account? <a href="login">Login</a>
    </div>
</div>
</body>
</html>