<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>About Me</title>
	<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css"/>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<%@ include file="navigation_bar.jsp" %>
	<div style="width:50%; margin:auto;">
		<h3 class="text-center">Edit About Me</h3>
		<br/>
		<form action="editAboutMeinfo" method="post">
			<input type="hidden" name="userId" value="${user.getUserId()}"/>
	    
	    <div class="form-group">
			<textarea class="form-control" id="aboutUser" name="aboutUser" rows="5" required>${aboutUser}</textarea>
		</div>
	    
		<div class="text-center">
			<button class="btn btn-primary" type="submit">Update</button>
		</div>
	    </form>
		<br/>
		<form action="userDetailsList">
			<input type="hidden" name="userId" value="${user.getUserId()}"/>
			<div class="text-center">
				<button class="btn btn-primary" type="submit">Back to list of users</button>
			</div>
		</form>
	</div>
	
</body>
</html>