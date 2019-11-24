<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Users</title>
	<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css"/>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	
		
</head>
<body>
	<%@ include file="navigation_bar.jsp" %>
	
	<h1 class="text-center">List of users</h1>

	<table class="table" style="width:50%;margin-left:auto;margin-right:auto;">
		<thead>
			<tr>
				<th scope="col">Name</th>
				<th scope="col">User Type</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td>
					    <a class="p-2 text-muted" href="<c:url value="/userAboutMeInfo?selectedUserId=${user.getUserId()}&userId=${userId}"/>">
					    ${user.getFirstName()} ${user.getLastName()}
					    </a>
					</td>
					<td>${user.getUserType()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>		
	
	<div class="text-center">
		<a class="btn btn-primary" href="<c:url value="/userAboutMeEdit?userId=${userId}"/>">Edit My About Me</a>	
	</div>


	
</body>
</html>