
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><c:choose><c:when test="${title != null}">${title}</c:when><c:otherwise>Sign Up</c:otherwise></c:choose>
	</title>

	<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css"/>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
</head>
<body>
	<!--Sign Up Page Layout-->
	<c:choose>
	    <c:when test="${title != null}">
	    
			<h2 style="text-align:center">${title}</h2>
	    </c:when>    
	    <c:otherwise>
	        
			<h2 style="text-align:center">Sign Up</h2>
	    </c:otherwise>
	</c:choose>
	<br />
	<div style="margin: auto;width: 50%;">		 
		<form action="signUp" method="post">
			<input type="hidden" name="userId" value="${user.userId}"/>
			
			<!--Email-->
			<div class="form-group">
				<label for="userName">Email:</label>
				<input type="text" class="form-control" id="email" name="email" value="${user.email}" required/>
		  	</div>
		  	
			<!--Password-->
			<div class="form-group">
				<label for="password">Password:</label>
				<input type="password" class="form-control" id="password" name="password" value="${user.password}" required/>
		  	</div>
		  	
			<!--User Type: Writer, Editor, Investor-->
			<div class="form-group">
				<label for="userType">User Type:</label>
		
			  	 <input type="radio" id="userType" name="userType" value="Writer"> Writer<br>
				 <input type="radio" id="userType" name="userType" value="Editor"> Editor<br>
				 <input type="radio" id="userType" name="userType" value="Investor"> Investor
			</div>

		  	<!--First Name-->
			<div class="form-group">
				<label for="lastname">First Name:</label>
				<input type="text" class="form-control" id="firstName" name="firstName" value="${user.firstName}"/>
		  	</div>		  	
		  	
			<!--Last Name-->
			<div class="form-group">
				<label for="address">Last Name:</label>
				<input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}"/>
		  	</div>
		  	
			<!--Phone Number-->
			<div class="form-group">
				<label for="city">Phone Number:</label>
				<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}"/>
		  	</div>
		  	
			
		  	
		  	<button type="submit" class="btn btn-primary">Sign Up</button>
		</form>
	</div>
</body>
</html>
