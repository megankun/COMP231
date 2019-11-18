<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>PayPal MarkInvoicePaid</title>
	<link rel="stylesheet" type="text/css" href="sdk.css"/> 
	<script type="text/javascript" src="sdk.js"></script>
</head>
<body>
	<img src="https://devtools-paypal.com/image/bdg_payments_by_pp_2line.png" alt="PAYMENTS BY PayPal" />
	<div id="wrapper">
		<div id="header">
			<h3>MarkInvoiceAsPaid</h3>
			<div id="apidetails">It is used to mark an invoice as paid.</div>
		</div>
		<form method="POST">
			<div id="request_form">
				<div class="params">
					<div class="param_name">
						InvoiceID* (Get Invoice ID via <a href='CreateInvoice'>CreateInvoice</a>
						or <a href='CreateInvoice'>CreateAndSendInvoice</a>)
					</div>
					<div class="param_value">
						<input type="text" name="invoiceId" value="" size="50"
							maxlength="260" />
					</div>
				</div>
				<div class="section_header">Other Payment Details*</div>

				<!--Used for selecting methods of payment-->
				<div class="params">
					<div class="param_name">Payment Method used for offline
						payment</div>
					<div class="param_value">
						<select name="paymentMethod">
							<option value="BankTransfer">BankTransfer</option>
							<option value="Cash">Cash</option>
							<option value="Check">Check</option>
							<option value="CreditCard">CreditCard</option>
							<option value="DebitCard">DebitCard</option>
							<option value="PayPal">PayPal</option>
							<option value="WireTransfer">WireTransfer</option>
							<option value="Other">Other</option>
						</select>
					</div>
				</div>

				<!--Personal Note or Message-->
				<div class="params">
					<div class="param_name">Note</div>
					<div class="param_value">
						<input type="text" name="note" value="" size="50" maxlength="260" />
					</div>
				</div>

				<!--Date-->
				<div class="params">
					<div class="param_name">Date when the invoice as paid</div>
					<div class="param_value">
						<input type="text" name="date" size="50" maxlength="260"  value='<%= dateFormat.format(new java.util.Date()) %>' />
					</div>
				</div>
				<jsp:include page="permissions.jsp">
					<jsp:param name="source" value="MarkInvoiceAsPaid"/>
				</jsp:include>	

				<!--Submit-->			
				<div class="submit">
					<input type="submit" name="MarkInvoiceBtn"
						value="MarkInvoiceAsPaid" /> <br />
				</div>
				<a href="index.jsp">Home</a>
			</div>
		</form>
	</div>
</body>
</html>