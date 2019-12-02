<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Funding List</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
<style>
h4{ text-align: center;}
</style>

</head>
<body>
	<%@ include file="navigation_bar.jsp" %>
	
	
		<br>
	<table class="table">
		<thead>
			<tr>
				<th scope="col"></th>	
				<th scope="col">Investor Name</th>
				<th scope="col">Funding Price</th>
			</tr>
		</thead>
		<tbody>
		<c:set var="total" value="${0}"/>
			<c:forEach var="funding" items="${fundingList}">
				<tr>
					<td></td>
					<td>${funding.nameOnCard}</td>
					<td>${funding.totalPrice}</td>
				</tr>
				 <c:set var="total" value="${total + funding.totalPrice}" />
			</c:forEach>
		</tbody>
		
	<h4>	<strong>Total Funding Price: $</strong><c:out value="${total}"></c:out></h4>
	<br>
	<h3>Funding List</h3>
	</table>
<a class="btn btn-primary" href="<c:url value="/episode?userId=${userId}"/>">Back To Episode</a>
					
</body>
</html>