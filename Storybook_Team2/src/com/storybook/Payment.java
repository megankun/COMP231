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
	public int getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}   
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}   
	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}   
	public String getNameOnCard() {
		return this.nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}   
	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}   
	public String getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}   
	public String getCvc() {
		return this.cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}   
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}   
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}   
	public String getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
   
}
