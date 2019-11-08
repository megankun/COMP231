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
		
	<script>
		function returnCharacterIds() {
			var allCharacterIds = document.getElementsByName("characterId");
		  	var checkedIds = [];
		  	var count = 0;
		  
		  	for (var i = 0; i < allCharacterIds.length; i++) {
		    	if (allCharacterIds[i].checked) {
		    		checkedIds[count] = allCharacterIds[i].value;
		    		
		    		count++;
		    	}
		  	}
		  
		  	//alert(checkedIds.length);
		  	if(checkedIds.length != 0){
		  		//alert("submit!");
			  	opener.document.getElementById("checkedCharacterIds").value = checkedIds;

				window.close()
		  	}else {
		  		alert("Please select any character to save!");
		  	}
		}
	</script>
</head>
<body>
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
					<td><input type="checkbox" name="characterId" value="${character.characterId}"/></td>
					<td>${character.name}</td>
					<td>${character.age}</td>
					<td>${character.appereance}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<button class="btn btn-primary" onClick="returnCharacterIds()">Save</button>
	<button class="btn btn-primary" onClick="window.close()">Close</button>
</body>
</html>