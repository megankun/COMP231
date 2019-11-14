<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Search Stories</title>
	
	<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css"/>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
</head>
<body>
	<%@ include file="navigation_bar.jsp" %>
	<div class="container text-center">
		<h3>Search Stories</h3>
		
		<br/>
		
		<form action="searchStories" method="post" style="width:70%; margin:auto;">
			<input type="hidden" name="userId" value="${userId}"/>
		
			<div class="form-group row">
				<select class="col-sm-2 form-control" name="searchFilter">
					<option value="bookTitle">Book Title</option>
					<option value="genre">Genre</option>
					<option value="chapterTitle">Chapter Title</option>
					<option value="storyNote">Story Note</option>
				</select>

				<div class="col-sm-9">
					<input type="text" class="form-control" name="searchStr" />
				</div>
				
				<div class="col-sm-1">
		        	<button class="btn btn-sm btn-outline-secondary">
		        		<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="mx-3" role="img" viewBox="0 0 24 24" focusable="false">
			        		<circle cx="10.5" cy="10.5" r="7.5"></circle>
			        		<path d="M21 21l-5.2-5.2"></path>
		        		</svg>
	        		</button>
				</div>
			</div>
		</form>
		
		<br/>
		<!-- Result List -->
		<c:if test="${bookList.size() > 0}">
			<table class="table" style="width:70%; margin:auto;">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Book Title</th>
						<th scope="col">Book Genre</th>
						<th scope="col">Chapter Title</th>
						<th scope="col">Note</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${bookList}">
						<c:if test="${book.storyList.size() > 0}">
							<c:forEach var="story" items="${book.storyList}">
								<tr>
									<th scope="row">${book.bookId}</th>
									<td>${book.title}</td>
									<td>${book.genre}</td>
									<td>${story.chapterTitle}</td>
									<td>${story.note}</td>
								</tr>
							</c:forEach>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>