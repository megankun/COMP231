
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><c:choose><c:when test="${title != null}">${title}</c:when><c:otherwise>Payment</c:otherwise></c:choose>
	</title>

	<link href="<%=request.getContextPath()%>/css/naviCss.css" rel="stylesheet" type="text/css"/>	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">	

</head>
<body>
	<%@ include file="navigation_bar.jsp"%>
	
	<hr class="mb-1">
	<br />
	<div class="d-flex justify-content-center">
		<form action="updatepayment" method="post" class="needs-validation">
			
			<h3 style="text-align:center;">Payment</h3>
			<br/>
			<input type="hidden" name="bookId" value="${bookId}"/>
			<input type=hidden name="userId" value="${userId}"/>
			<input type="hidden" name="paymentId" value="${paymentId}"/>
			
			
			<div class="d-block my-3">
				<div class="custom-control custom-radio">
					<input id="debit" name="cardType" value="Visa Debit" type="radio"
						class="custom-control-input" checked required> <label
						class="custom-control-label" for="debit">Debit card</label>

				</div>
				<div class="custom-control custom-radio">
					<input id="credit" name="cardType" value="Visa Credit" type="radio"
						class="custom-control-input" required> <label
						class="custom-control-label" for="credit">Credit card</label>

				</div>
			</div>

			<div class="row">
				<div class="col-md-6 mb-3">

					<label for="cc-name">Name on card</label> <input type="text"
						name="nameOnCard" class="form-control" id="cc-name"
						required   value= ${payment.nameOnCard}>
					<div class="invalid-feedback">Name on card is required</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 mb-3">
					<label for="cc-number">Card number</label> <input
						type="text" name="cardNumber" class="form-control" id="cc-number"
						required value= ${payment.cardNumber}>
					<div class="invalid-feedback">Credit card number is required</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 mb-3">
					<label for="cc-expiration">Expiry Date</label> 
					<input type="number" name="expiryDate" class="form-control" id="cc-expiration" required value= ${payment.expiryDate}>
					<div class="invalid-feedback">Expiration date required</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 mb-3">
					<label for="cc-cvv">CVC</label> 
					<input type="number" min="100" max="999" name="cvc" class="form-control" id="cc-cvv" required value= ${payment.cvc}>
					<div class="invalid-feedback">CVC required</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 mb-3">
					<label for="cc-country">Country</label> 
					<input type="text" name="country" class="form-control" id="cc-country" required value= ${payment.country}>
					<div class="invalid-feedback"> Country required</div>
				</div>
				
				<div class="col-md-3 mb-3">
					<label for="cc-zipCode">Zip Code</label> 
					<input type="text"  name="zipCode" class="form-control" id="cc-zipCode" required value= ${payment.zipCode}>
					<div class="invalid-feedback">Zip code required</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-3 mb-3">
					<label for="cc-price">Fund Price</label> 
					<input type="number" name="totalPrice" class="form-control" id="cc-price" required value= ${payment.totalPrice}>
					<div class="invalid-feedback">Price required</div>
				</div>
			</div>

			<hr class="mb-4">

			<button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>


		</form>
	</div>
</body>
</html>