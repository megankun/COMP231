<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Location List</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
		
	<script>
		function returnLocationIds() {
			var allLocationIds = document.getElementsByName("locationId");
		  	var checkedIds = [];
		  	var count = 0;
		  
		  	for (var i = 0; i < allLocationIds.length; i++) {
		    	if (allLocationIds[i].checked) {
		    		checkedIds[count] = allLocationIds[i].value;
		    		
		    		count++;
		    	}
		  	}
		  
		  	if(checkedIds.length != 0){
			  	opener.document.getElementById("checkedLocationIds").value = checkedIds;

				window.close()
		  	}else {
		  		alert("Please select location to save!");
		  	}
		}
	</script>
</head>
<body>
	<!--Creates List of Locations-->
	<h3>Location List</h3>
	
	<table class="table">
		<thead>
			<tr>
				<th scope="col"></th>
				<th scope="col">Name</th>
				<th scope="col">Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="location" items="${locationList}">
				<tr>
					<td><input type="checkbox" name="locationId" value="${location.locationId}"/></td>
					<td>${location.name}</td>
					<td>${location.description}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<button class="btn btn-primary" onClick="returnLocationIds()">Save</button>
	<button class="btn btn-primary" onClick="window.close()">Close</button>
</body>
</html>