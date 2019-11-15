<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	<link href="<%=request.getContextPath()%>/css/indexCss.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
	
</head>
<body class="text-center">
	<form action="login" method="post" class="form-signin" >
		<!-- <img class="mb-4" src="" alt="" width="72" height="72"> -->
		
		<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		<div class="message">${message}</div>
		<!-- Name -->
		<label for="username" class="sr-only">User Email</label>
		<input type="text" id="emaill" name="email" class="form-control" placeholder="Email" required autofocus>
		
		<!-- Password -->
		<label for="password" class="sr-only">Password</label>
		<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
		
		<button class="btn btn-lg btn-primary btn-block" type="submit" name="Sign In">Sign in</button>
		<br/>
		<a href="<c:url value="signUp"/>">Sign Up</a>
	</form>
	
</body>
</html>