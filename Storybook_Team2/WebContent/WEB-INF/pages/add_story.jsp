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
		
	<script>
		function popupLocation() {
			window.open("<c:url value='/locationList?bookId=${bookId}'/>", "_blank", "scrollbars=1,resizable=1,height=700,width=650");
		}
		
		function popupCharacter() {
			window.open("<c:url value='/characterList?bookId=${bookId}'/>", "_blank", "scrollbars=1,resizable=1,height=700,width=650");
		}
	</script>
</head>
<body>
	<%@ include file="navigation_bar.jsp" %>
	
	<h2>Write Story</h2>
	
	book info <Br/>
	userId: ${userId} / bookId: ${bookId}<br/>
	Title: ${book.title}<br/>
	Genre: ${book.genre}<br/>
	
	<form action="addStory" method="post">
		<input type="hidden" name="bookId" value="${book.bookId}"/><br/><br/>
		
		Chapter Title: <input type="text" name="chapterTitle"/><br/><br/>
		
		Location: <br/><br/>
		
		<input type="text" name="checkedLocationIds" id="checkedLocationIds"/>
		<a href='#' onclick='popupLocation()'>Location List</a>
		<br/><br/>
		
		Character: <br/>
		
		<input type="text" name="checkedCharacterIds" id="checkedCharacterIds"/>
		<a href='#' onclick='popupCharacter()'>Character List</a>
		<br/><br/>
		
		Note: <textarea name='note'></textarea><br/>
		
		<button type="submit">submit</button>
	</form>
</body>
</html>