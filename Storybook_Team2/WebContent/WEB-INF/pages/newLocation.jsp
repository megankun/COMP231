
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="navigation_bar.jsp" %>
	
	<meta charset="UTF-8">
	<title><c:choose><c:when test="${title != null}">${title}</c:when><c:otherwise>Sign Up</c:otherwise></c:choose>
	</title>

	<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css"/>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
</head>
<body>
	<c:choose>
	    <c:when test="${title != null}">
	    
			<h2 style="text-align:center">${title}</h2>
	    </c:when>    
	    <c:otherwise>
	        <br/>
			<h2 style="text-align:center">Create New Character</h2>
	    </c:otherwise>
	</c:choose>
	<br />
	<div style="margin: auto;width: 50%;">		 
		<form action="newLocation" method="post">
			<input type="hidden" name="bookId" value="${bookId}"/>
			<input type="hidden" name="userId" value="${userId}"/>
			<input type="hidden" name="locationId" value="${location.locationId}"/>
			
			
			<div class="form-group">
				<label for="name">Name:</label>
				<input type="text" class="form-control" id="name" name="name" value="${location.name}" required/>
		  	</div>
		  	
			<div class="form-group">
				<label for="age">Description:</label>
				<textarea class="form-control" id="description" name='description' value="${location.description}"></textarea>
				<!-- 
				<input type="number" class="form-control" id="description" name="description" value="${location.description}" required/>
		  	 	-->
		  	</div>
	  	
		  	<button type="submit" class="btn btn-primary">Save</button>
		  	<a class="btn btn-primary" href="<c:url value="/episode?userId=${userId}"/>">Cancel</a>
		  	
		</form>
	</div>
</body>
</html>
