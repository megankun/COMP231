<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Character List</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
		

</head>
<body>
	<%@ include file="navigation_bar.jsp" %>
	
	<h3>Character List</h3>
	
	<table class="table">
		<thead>
			<tr>
				<th scope="col"></th>
				<th scope="col">Name</th>
				<th scope="col">Age</th>
				<th scope="col">Appereance</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="character" items="${characterList}">
				<tr>
					<td>${character.characterId}</td>
					<td>${character.name}</td>
					<td>${character.age}</td>
					<td>${character.appereance}</td>
					<td>
						<form action="editCharacter">
							<input type="hidden" value="${userId}" name="userId" />
							<input type="hidden" value="${character.characterId}" name="characterId" />
							<input type="hidden" value="${character.name}" name="name">
							<input type="hidden" value="${character.age}" name="age">
							<input type="hidden" value="${character.appereance}" name="appereance">
							<input type="hidden" value="${character.bookId}" name="bookId">
							
							<button type="submit" class="btn btn-primary">Edit</button>
							<a class="btn btn-primary" href="<c:url value="/deleteCharacter?userId=${userId}&bookId=${character.bookId}&characterId=${character.characterId}"/>">Delete</a>
							
						</form>
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<a class="btn btn-primary" href="<c:url value="/episode?userId=${userId}"/>">Back To Episode</a>
					
</body>
</html>