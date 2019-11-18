<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>PayPal</title>
	<link rel="stylesheet" type="text/css" href="sdk.css"/> 
	<script type="text/javascript" src="sdk.js"></script>
</head>
<body>
	<img src="https://devtools-paypal.com/image/bdg_payments_by_pp_2line.png" alt="PAYMENTS BY PayPal" />
	<div id="wrapper">
		<div id="header">
			<h3>CreateInvoice</h3>

		</div>
		<form method="POST">
			<div id="request_form">

				<div class="params">

					<!--Email Address of Seller-->
					<div class="param_name">Merchant Email</div>
					<div class="param_value">
						<input type="text" name="merchantEmail"
							value="jb-us-seller@paypal.com" size="50" maxlength="260" />
					</div>

					<!--Email Address of Buyer-->
					<div class="param_name">Payer Email</div>
					<div class="param_value">
						<input type="text" name="payerEmail"
							value="jbui-us-personal1@paypal.com" size="50" maxlength="260" />
					</div>

					<!--List of Item Names, Quantity and Unit Price-->
					<div class="param_name">Item1 Name</div>
					<div class="param_value">
						<input type="text" name="item_name1" value="item1" size="30"
							maxlength="30" />
					</div>
					<div class="param_name">Item1 Quantity</div>
					<div class="param_value">
						<input type="text" name="item_quantity1" value="1" size="3"
							maxlength="5" />
					</div>
					<div class="param_name">Item1 UnitPrice</div>
					<div class="param_value">
						<input type="text" name="item_unitPrice1" value="1.00" size="10"
							maxlength="19" />
					</div>
					<div class="param_name">Item2 Name</div>
					<div class="param_value">
						<input type="text" name="item_name2" value="item2" size="30"
							maxlength="30" />
					</div>
					<div class="param_name">Item2 Quantity</div>
					<div class="param_value">
						<input type="text" name="item_quantity2" value="2" size="3"
							maxlength="5" />
					</div>
					<div class="param_name">Item2 UnitPrice</div>
					<div class="param_value">
						<input type="text" name="item_unitPrice2" value="2.00" size="10"
							maxlength="19" />
					</div>

					<!--Code of the Currency being used for transaction relative to US Dollar-->
					<div class="param_name">Currency Code</div>
					<div class="param_value">
						<input type="text" name="currencyCode" value="USD" size="50"
							maxlength="260" />
					</div>

					<!--Terms of Customer Payments-->
					<div class="param_name">Payment Terms</div>
					<div class="param_value">
						<select name="paymentTerms">
							<option value="DueOnReceipt">DueOnReceipt</option>
							<option value="DueOnDateSpecified">DueOnDateSpecified</option>
							<option value="1Month">1Month</option>
							<option value="2Month">2Month</option>
							<option value="3Month">3Month</option>
							<option value="4Month">4Month</option>
						</select>
					</div>
				</div>
				<jsp:include page="permissions.jsp">
					<jsp:param name="source" value="CreateInvoice"/>
				</jsp:include>
				<div class="submit">
					<input type="submit" name="CreateBtn" value="CreateInvoice" /> <input
						type="submit" name="CreateBtn" value="CreateAndSendInvoice" /> <br />
				</div>
				<a href="index.jsp">Home</a>
			</div>
		</form>
	</div>
</body>
</html>