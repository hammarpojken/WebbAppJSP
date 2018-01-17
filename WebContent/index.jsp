<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="style/login.css">
<title>Login</title>
</head>
<body>
Welcome ${uname}
<div id="loginDiv">
	<div id="loginformcontainer">
		<form id="loginform" action="Login">
		Enter username: <input type="text"><br>
		Enter password: <input type="password"><br>
		<input type="submit" value="Login"><br>
		</form>
	</div>
</div>


</body>
</html>