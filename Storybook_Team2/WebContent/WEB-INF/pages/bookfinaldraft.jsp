<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>s
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Check Final Draft</title>
	<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css"/>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script language="javascript">
    window.onload=function(){
    var textarea = document.getElementsByTagName("textarea")[0];
    textarea.scrollTop=textarea.scrollHeight
    }
</script>	
</head>
<body>
	<%@ include file="navigation_bar.jsp" %>
	
	<div style="width:50%; margin:auto;">
		<h3 class="text-center">Check Final Draft</h3>
		<br/>
		
		<div class="alert alert-primary" role="alert">
		  <h4>Book Information</h4>
		 	<ul>
		  		<li><b>Title: </b>${book.title}</li>
		  		<li><b>Genre: </b>${book.genre}</li>
		  	</ul>
		</div>
		<textarea cols="132" rows="20">
		Locations:
			<c:forEach var="location" items="${locations}">
			 Name: ${location.name},   Description: ${location.description}
			</c:forEach>
		
		Characters:
			<c:forEach var="character" items="${characters}">
		      Name: ${character.name},  Age: ${character.age},  Appearance: ${character.appereance}
			</c:forEach>
		
		Stories:
		    <c:forEach var="story" items="${stories}">
		      ChapterTitle: ${story.chapterTitle},  Note: ${story.note}
			</c:forEach>
			
	    </textarea>
	   	   	
		<br/><br/>
	</div>
	
</body>
</html>