<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Location List</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	

</head>
<body>	
	<br>
	<h3> Location List</h3>
	<br>
	<table class="table">
		<thead>
			<tr>
				<th scope="col"></th>
				<th scope="col">Name</th>
				<th scope="col">Description</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
	
			<c:forEach var="location" items="${locationList}">
				<tr>
					<td>${location.locationId}</td>
					<td>${location.name}</td>
					<td>${location.description}</td>
					<td>
						<form action="editLocation">
							<input type="hidden" value="${userId}" name="userId" />
							<input type="hidden" value="${location.name}" name="name">
							<input type="hidden" value="${location.description}" name="description">
							<input type="hidden" value="${location.locationId}" name="locationId">
							<input type="hidden" value="${location.bookId}" name="bookId">
							
							<button type="submit" class="btn btn-primary">Edit</button>
							<a class="btn btn-primary" href="<c:url value="/deleteLocation?userId=${userId}&bookId=${location.bookId}&locationId=${location.locationId}"/>">Delete</a>
							
							
						</form>
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a class="btn btn-primary" href="<c:url value="/episode?userId=${userId}"/>">Back To Episode</a>

</body>
</html>