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
			window.open("<c:url value='/locationList?bookId=${bookId}&popup=yes'/>", "_blank", "scrollbars=1,resizable=1,height=700,width=650");
		}
		
		function popupCharacter() {
			window.open("<c:url value='/characterList?bookId=${bookId}&popup=yes'/>", "_blank", "scrollbars=1,resizable=1,height=700,width=650");
		}
	</script>
</head>
<body>
	<%@ include file="navigation_bar.jsp" %>
	
	<div style="width:50%; margin:auto;">
		<h3 class="text-center">Write Story</h3>
		<br/>
		
		<div class="alert alert-primary" role="alert">
		  <h4>Book Information</h4>
			<%-- userId: ${userId} / bookId: ${bookId}<br/> --%>
			<ul>
		  		<li><b>Title: </b>${book.title}</li>
		  		<li><b>Genre: </b>${book.genre}</li>
		  	</ul>
		</div>
		
		<br/><br/>
		<form action="addStory" method="post">
			<input type="hidden" name="bookId" value="${book.bookId}"/>
			<input type="hidden" name="userId" value="${userId}" >
			
			
			<div class="form-group row">
				<label for="chapterTitle" class="col-sm-2 col-form-label">Chapter Title:</label>
			    <div class="col-sm-10">
			    	<input type="text" class="form-control" id="chapterTitle" name="chapterTitle" required>
			    </div>
		    </div>
		
			<div class="form-group row">
				<label for="chapterTitle" class="col-sm-2 col-form-label"><a href='#' onclick='popupLocation()'>Location</a></label>
			    <div class="col-sm-10">
			    	<input type="text" class="form-control" id="checkedLocationIds" name="checkedLocationIds" readonly required>
			    </div>
			    
		    </div>
						
			<div class="form-group row">
				<label for="chapterTitle" class="col-sm-2 col-form-label"><a href='#' onclick='popupCharacter()'>Character</a></label>
			    <div class="col-sm-10">
			    	<input type="text" class="form-control" id="checkedCharacterIds" name="checkedCharacterIds" readonly required>
			    </div>
		    </div>
		    
			<div class="form-group">
				<label for="exampleFormControlTextarea1">Note: </label>
				<textarea class="form-control" id="note" name="note" rows="3" required></textarea>
			</div>
			
			<div class="text-center">
				<button class="btn btn-primary" type="submit">submit</button>
			</div>
		</form>
	</div>
</body>
</html>