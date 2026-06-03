<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>

<title>Add Contact</title>
<style>

body { font-family:Arial; padding:30px;background:#f4f4f4;}

h2 {text=align:center;}

.form {background:white; width:400px; margin:auto;
padding:30px; border-radius:10px; border:1px solid #ddd;
}

input {width:100%; padding:10px; margin:8px 0 16px;
border:1px solid #ccc; border-radius:6px; box-sizing:border-box;

}
.btn {width:100%;background:#1a6fbf; color:white;
padding:12px;border:none;border-radius:6px; font-size:16px;cursor:pointer;
}

.btn:hover {background:#0d4f8c}
.back {display:block; text-align:center; margin-top:16px;
color:#1a6dbf;text-decoration:none;
}
</style>

<body>

<h2>Add New Contact</h2>
<div class="form">
<form method="post" action="ContactServlet">

Name:<br/>
<input type="text" name="name" placeholder="Enter name" required/>
Phone:<br/>
<input type="number" name="phone" placeholder="Enter Phone" required/>

Email:

<input type="text" name="email" placeholder="Enter email" required/>
<input type="submit" value="Add Contact" class="btn"/>
</form>

<a href="index.jsp" class="back">Back to Home</a>
</div>



</body>









</head></html>