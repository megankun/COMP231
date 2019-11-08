<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write Story</title>
</head>
<body>
	<h2>Write Story</h2>
	
	book info <Br/>
	userId: ${userId} / bookId: ${bookId}<br/>
	Title: ${book.title}<br/>
	Genre: ${book.genre}<br/>
	
	<form action="addStory" method="post">
		<input type="hidden" name="bookId" value="${book.bookId}"/><br/>
		Chapter Title: <input type="text" name="chapterTitle"/><br/>
		Location: **** SELECT BOX LIST FROM DB? POPUP? <br/>
		Character: same <br/>
		Note: <textarea name='note'></textarea><br/>
		
		<button type="submit">submit</button>
	</form>
</body>
</html>