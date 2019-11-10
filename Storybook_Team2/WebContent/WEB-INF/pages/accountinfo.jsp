<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Account Information</title>
<link href="<%=request.getContextPath()%>/css/indexCss.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
</head>
<body class="text-center">

	<form action="edituserinfo" method="post" class="form-signin" >
		
		<h1 class="h3 mb-3 font-weight-normal">Account Information</h1>
		<input type="text" name="userId" value= ${user.userId} hidden></input>
		Email:	 
		<input type="text" id="email" name="email" class="form-control" placeholder="Email" readonly="readonly" value= ${user.email}  required autofocus>
		Password:
		<input type="text" id="password" name="password" class="form-control" placeholder="Password" value= ${user.password} required autofocus>
		UserType:
		<input type="text" id="userType" name="userType" class="form-control" placeholder="userType" value= ${user.userType} required autofocus>
		FirstName:
		<input type="text" id="firstName" name="firstName" class="form-control" placeholder="firstName" value= ${user.firstName} required autofocus>
		LastName:
		<input type="text" id="lastName" name="lastName" class="form-control" placeholder="lastName" value= ${user.lastName} required autofocus>
		PhoneNumber:
		<input type="text" id="phoneNumber" name="phoneNumber" class="form-control" placeholder="phoneNumber" value= ${user.phoneNumber} required autofocus>
		
		<button class="btn btn-lg btn-primary btn-block" type="submit" name="Edit User Account">Edit</button>
		<br/>
		
	</form>
	
</body>
</html>