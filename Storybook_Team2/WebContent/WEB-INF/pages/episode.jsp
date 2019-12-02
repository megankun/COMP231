<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Episode</title>
<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<script>
		function selectedBookId(opt) {
			var ids = document.getElementsByName('selectedBookId');
			var selectedId;
			
			for(var i = 0; i < ids.length; i++) {
			   if(ids[i].checked)
				   selectedId = ids[i].value;
			 }
			
			if(selectedId == null){
				alert("Please select a book !");
			}else{
				if(opt == 1) { // View Characters
					window.open("<c:url value='/characterUpdateList'/>?userId=${userId}&bookId=" + selectedId, "_self");
				}else if(opt == 2) { // View Locations
					window.open("<c:url value='/updatedLocation'/>?userId=${userId}&bookId=" + selectedId, "_self");
				}else if(opt == 3) { // Create New Character
					window.open("<c:url value='/newCharacter'/>?userId=${userId}&bookId=" + selectedId, "_self");				
				}else if(opt == 4) { // Create New Location
					window.open("<c:url value='/newLocation'/>?userId=${userId}&bookId=" + selectedId, "_self");				
				}else if(opt == 5) { // Check Final Draft
					window.open("<c:url value='/checkfinaldraft'/>?userId=${userId}&bookId=" + selectedId, "_self");				
				}
			}
		}
		
	</script>
</head>
<body>
	<%@ include file="navigation_bar.jsp"%>

	<div class="container">
		<h3 class="text-center">Write story for your book !</h3>
		<br />

		<table class="table">
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col">#</th>
					<th scope="col">Title</th>
					<th scope="col">Genre</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${bookList}">
					<tr>
						<td><input type="radio" id="selectedBookId" name="selectedBookId" value="${book.bookId}"></td>
						<th scope="row">${book.bookId}</th>
						<td>
							<c:choose>
								<c:when test="${userType != 'Investor'}">
									<a href="<c:url value="/chapterList?userId=${userId}&bookId=${book.bookId}"/>">${book.title}</a> 
								</c:when>
								
								<c:otherwise>
									${book.title}									
								</c:otherwise>
							</c:choose>
						</td>
						<td>${book.genre}</td>

						<c:choose>
							<c:when test="${userType == 'Investor'}">
								<td>
									<a class="btn btn-primary" href="<c:url value="/payment?userId=${userId}&bookId=${book.bookId}"/>">Payment</a>
									<a class="btn btn-primary" href="<c:url value="/viewDescription?userId=${userId}&bookId=${book.bookId}"/>">View Description</a>
								</td>
							</c:when>
							
							<c:when test="${userType == 'Writer'}">
								<td>
									<!-- 
									<a class="btn btn-primary" href="<c:url value="/chapterList?userId=${userId}&bookId=${book.bookId}"/>">View Stories</a> 
									<a class="btn btn-success" href="<c:url value="/addStory?userId=${userId}&bookId=${book.bookId}"/>">Write Story</a>
									 -->
									<a class="btn btn-warning" href="<c:url value="/editBook?userId=${userId}&bookId=${book.bookId}"/>">Edit Book</a>
									<a class="btn btn-warning" href="<c:url value="/editpayment?userId=${userId}&bookId=${book.bookId}"/>">Edit PaymentInfo</a>
									<a class="btn btn-danger" href="<c:url value="/deleteBook?userId=${userId}&bookId=${book.bookId}"/>">Delete Book</a>
								</td>
							</c:when>
						</c:choose>

					</tr>
				</c:forEach>
			</tbody>
		</table>

		<br />
		<br />
		<c:choose>
			<c:when test="${userType == 'Investor'}">

			</c:when>
			
			<c:when test="${userType == 'Writer'}">
				<div class="text-center">
					<form action="toAddBook" method="post">
						<input type="hidden" value="${userId}" name="userId">
						<button type="submit" class="btn btn-success">Create New Book</button>
					</form>
				</div>
				<br/>
				<div class="text-center">
					<a href='#' class="btn btn-primary" onclick='selectedBookId(1)'>View Characters</a> 
					<a href='#' class="btn btn-primary" onclick='selectedBookId(2)'>View Locations</a> 
					<a href='#' class="btn btn-success" onclick='selectedBookId(3)'>Create New Character</a>
					<a href='#' class="btn btn-success" onclick='selectedBookId(4)'>Create New Location</a>
					
					<br/><br/>
					<a href='#' class="btn btn-info" onclick='selectedBookId(5)'>Check Final Draft</a>
				</div>
			</c:when>
		</c:choose>



	</div>
</body>
</html>