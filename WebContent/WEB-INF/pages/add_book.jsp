<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Write Story</title>
	<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css"/>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
		
</head>
<body>
	<%@ include file="navigation_bar.jsp" %>
	
	<div style="width:50%; margin:auto;">
		<!--Page for Creating New Book-->
		<h3 class="text-center">Create New Book</h3>
		<br/>
		
		<br/><br/>
		<form action="addBook" method="post">
			<input type="hidden" name="userId" value="${userId}" >
			
			<div class="form-group row">
				<label for="bookTitle" class="col-sm-2 col-form-label">Book Title:</label>
			    <div class="col-sm-10">
			    	<input type="text" class="form-control" id="bookTitle" name="bookTitle" required>
			    </div>
		    </div>
		    <div class="form-group row">
				<label for="genre" class="col-sm-2 col-form-label">Genre:</label>
			    <div class="col-sm-10">
			    	<input type="text" class="form-control" id="genre" name="genre" required>
			    </div>
		    </div>
			<div class="text-center">
				<button class="btn btn-primary" type="submit">Submit</button>
			</div>
		</form>
	</div>
</body>
</html>