<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Chapters</title>
	<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css"/>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
		
</head>
<body>
	<%@ include file="navigation_bar.jsp" %>
	
	<div class="container">
		<h3 class="text-center">Write story for your book !</h3>
		<br />

		<table class="table">
			<thead>
				<tr>
					<th scope="col">Chapter Title</th>
					<th scope="col">Note</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="chapter" items="${chapterList}">
					<tr>
						<td>${chapter.getChapterTitle()}</td>
						<td>${chapter.getNote()}</td>
						<td>
							<c:choose>
								<c:when test="${userType == 'Editor'}">
									<a class="btn btn-primary" href="<c:url value="/uploadDraft?userId=${userId}&bookId=${bookId}&storyId=${chapter.storyId}"/>">Upload Draft</a>
									<a class="btn btn-primary" href="<c:url value="/draftList?userId=${userId}&bookId=${bookId}&storyId=${chapter.storyId}"/>">Draft List</a>
								</c:when>
								<c:otherwise>
									<form action="loadEditStory">
										<input type="hidden" value="${chapter.getBookId()}" name="bookId">
										<input type="hidden" value="${userId}" name="userId">
										<input type="hidden" value="${chapter.getStoryId()}" name="storyId">
										<button type="submit" class="btn btn-primary">Edit</button>
									</form>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>		
		
		<div class="text-center">
			<a class="btn btn-primary" href="<c:url value="/addStory?userId=${userId}&bookId=${bookId}"/>">Write Story</a>
		</div>
	</div>

	
</body>
</html>