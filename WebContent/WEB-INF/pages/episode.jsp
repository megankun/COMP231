<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Episode</title>
	<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css"/>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
		
</head>
<body>
	<%@ include file="navigation_bar.jsp" %>
	
	<h1>Write story for your book !</h1>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Title</th>
				<th scope="col">Genre</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="book" items="${bookList}">
				<tr>
					<th scope="row">${book.bookId}</th>
					<td>${book.title}</td>
					<td>${book.genre}</td>
					<td>
						<form action="chapterList" method="post">
						<input type="hidden" value="${book.bookId}" name="bookId">
						<input type="hidden" value="${userId}" name="userId">
						<button type="submit" class="btn btn-primary">View Stories</button>
						</form>
						<br>
						<a class="btn btn-primary" href="<c:url value="/addStory?userId=${userId}&bookId=${book.bookId}"/>">Write Story</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	
</body>
</html>