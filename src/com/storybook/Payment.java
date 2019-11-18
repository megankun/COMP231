package com.storybook;

import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Payment
 *
 */
@Entity
public class Payment{

	//Class Variables
	@Id
	private int paymentId;							//Payment ID Number
	private int userId;								//User ID Number
	private int bookId;								//Book ID Number
	private String cardType;						//Card Type (Debit/Credit)
	private String nameOnCard;						//Name of Card Holder
	private String cardNumber;						//Card Number
	private String expiryDate;						//Credit Card Expiry Date
	private String cvc;								//Card Verification Number
	private String country;							//Card Country
	private String zipCode;							//Zip Code
	private String totalPrice;						//Price

	public Payment() {
		super();
	}   
	public int getPaymentId() {						//Method to Retrieve Payment ID
		return this.paymentId;
	}

	public void setPaymentId(int paymentId) {		//Method to Set Payment ID
		this.paymentId = paymentId;
	}   

	public int getUserId() {						//Method to Retrieve User ID
		return this.userId;
	}

	public void setUserId(int userId) {				//Mmethod to Set User ID
		this.userId = userId;
	}   

	public String getCardType() {					//Method to Retrieve Card Type
		return this.cardType;
	}

	public void setCardType(String cardType) {		//Method to Set Card Type
		this.cardType = cardType;
	}   

	public String getNameOnCard() {					//Method to Retrieve Card Name
		return this.nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {	//Method to Set Card Name
		this.nameOnCard = nameOnCard;
	}   

	public String getCardNumber() {					//Method to Retrieve Card Number
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {	//Method to Set Card Number
		this.cardNumber = cardNumber;
	}   
	public String getExpiryDate() {					//Method to Retrieve Card Expiry Date
		return this.expiryDate;
	}

	public void setExpiryDate(String expiryDate) {	//Method to Set Expiry Date
		this.expiryDate = expiryDate;
	}   

	public String getCvc() {						//Method to Retrieve Card Verification Code
		return this.cvc;
	}

	public void setCvc(String cvc) {				//Method to Set Card Verification Code
		this.cvc = cvc;
	}   

	public String getCountry() {					//Method to Retrieve Card Country
		return this.country;
	}

	public void setCountry(String country) {		//Method to Set Card Country
		this.country = country;
	}   

	public String getZipCode() {					//Method to Retrieve Zip Code
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {		//Method to Set Zip Code
		this.zipCode = zipCode;
	}   

	public String getTotalPrice() {					//Method to Retrieve Total Invoice Price
		return this.totalPrice;
	}

	public void setTotalPrice(String totalPrice) {	//Method to Set Invoice Total
		this.totalPrice = totalPrice;
	}

	public int getBookId() {						//Method to Retrieve Book ID
		return bookId;
	}

	public void setBookId(int bookId) {				//Method to Set Book ID
		this.bookId = bookId;
	}
   
}
