<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>User Account Information</title>
	<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css"/>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
</head>
<body>
	<%@ include file="navigation_bar.jsp" %>
	
	<div style="width:50%; margin:auto;"> 
		<!--Form Displaying User Account Information-->
		<form action="edituserinfo" method="post" class="form-signin" >
			
			<h1 class="h3 mb-3 font-weight-normal">Account Information</h1>
			<!--
				User ID: 
				Email:
				Password:
				First Name:
				Last Name:
				Phone #:
			-->
			<input type="text" name="userId" value= ${user.userId} hidden></input>
			Email:	 
			<input type="text" id="email" name="email" class="form-control" placeholder="Email" readonly="readonly" value= ${user.email}  required autofocus>
			Password:
			<input type="text" id="password" name="password" class="form-control" placeholder="Password" value= ${user.password} required autofocus>
			UserType:
			<input type="text" id="userType" name="userType" class="form-control" readonly="readonly" placeholder="userType" value= ${user.userType} required autofocus>
			FirstName:
			<input type="text" id="firstName" name="firstName" class="form-control" placeholder="firstName" value= ${user.firstName} required autofocus>
			LastName:
			<input type="text" id="lastName" name="lastName" class="form-control" placeholder="lastName" value= ${user.lastName} required autofocus>
			PhoneNumber:
			<input type="text" id="phoneNumber" name="phoneNumber" class="form-control" placeholder="phoneNumber" value= ${user.phoneNumber} required autofocus>
			
			<button class="btn btn-lg btn-primary btn-block" type="submit" name="Edit User Account">Edit</button>
			<br/>
			
		</form>
	</div>
</body>
</html>