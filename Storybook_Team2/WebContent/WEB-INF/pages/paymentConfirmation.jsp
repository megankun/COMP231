<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><c:choose><c:when test="${title != null}">${title}</c:when><c:otherwise>Payment Confirmation</c:otherwise></c:choose>
	</title>

	<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css"/>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	

</head>
<body>
<%@ include file="navigation_bar.jsp" %>
	
	<h1 style="text-align:center;"> Payment confirmed </h1>
	
	<div class="alert alert-primary" role="alert" style="width:60%; margin:0 auto;">
		Thank you, your payment has been successful and your funding is now confirmed. 
	
	</div>
</body>
</html>