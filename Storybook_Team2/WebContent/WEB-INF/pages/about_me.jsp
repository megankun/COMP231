<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>s
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>About ${user.getFirstName()} ${user.getLastName()}</title>
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
		<h3 class="text-center">About ${user.getFirstName()} ${user.getLastName()}</h3>
		<br/>
		<div class="form-group">
			<textarea class="form-control" id="aboutUser" name="aboutUser" rows="5" required>${user.getAboutUser()}</textarea>
		</div>
		<br/><br/>
	</div>
	
</body>
</html>