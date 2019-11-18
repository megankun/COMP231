<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"></jsp:include>


<h3 style="color: navy;" align="center"><B>Consultant:</B></h3>
<div id="box">

<form name="myform" action="asave.jsp">
<table>
<tr><td>user:</td><td><select name="branch">
					<option>Select a User</option>
					<option>investor</option>
					<option>Designer</option>
					<option>Writer</option>
					</select></td></tr>
					<tr><td><br></td></tr>
<tr><td>Username:  </td><td><input type="text" name="username"/></td></tr>
	<tr><td><br></td></tr>
	
<tr><td>Password:  </td><td><input type="password" name="userpass"/></td></tr>
	<tr><td><br></td></tr>
<tr><td>Date of Joining:</td><td><input type="text" name="doj"/></td></tr>
	<tr><td><br></td></tr>
<tr><td>Date of Birth:</td><td><input type="text" name="dob"/></td></tr>
	<tr><td><br></td></tr>
	<tr><td>Salary:</td><td><input type="text" name="salary"/></td></tr>
	<tr><td><br></td></tr>
		<tr><td></td><td><input type="submit" value="create" style="padding: 2"></td></tr>
	<tr><td><br></td></tr>
</table>
<div id="location" align="left"></div>
</form>

</div>

</html>
