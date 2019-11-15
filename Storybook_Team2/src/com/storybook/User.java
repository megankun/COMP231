package com.storybook;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
public class User {

	//Class Variables
	@Id
	private int userId;				//User ID
	private String email;			//User Email Address
	private String password;		//User Password
	private String userType;		//User Type (Author/Editor)
	private String firstName;		//User First Name
	private String lastName;		//User Surname
	private String phoneNumber;		//User Phone Number

	public int getUserId() {					//Method to Retrieve User ID
		return this.userId;
	}

	public void setUserId(int userId) {			//Method to Set User ID
		this.userId = userId;
	}   
	public String getEmail() {					//Method to Retrieve User Email
		return this.email;
	}

	public void setEmail(String email) {		//Method to Set User Email
		this.email = email;
	}   
	public String getPassword() {				//Method to Retrieve User Password
		return this.password;
	}

	public void setPassword(String password) {	//Method to Set User Password
		this.password = password;
	}   

	public String getUserType() {				//Method to Retrieve User Type
		return this.userType;
	}

	public void setUserType(String userType) {		//Method to Set User Type
		this.userType = userType;
	}   
	public String getFirstName() {					//Method to Retrieve User First Name
		return this.firstName;
	}

	public void setFirstName(String firstName) {	//Method to Set User First Name
		this.firstName = firstName;
	}   
	public String getLastName() {					//Method to Retrieve User Last Name
		return this.lastName;
	}

	public void setLastName(String lastName) {		//Method to Set User Last Name
		this.lastName = lastName;
	}   
	public String getPhoneNumber() {				//Method to Retrieve User Phune No.
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {	//Method to Set User Phone No.
		this.phoneNumber = phoneNumber;
	}
   
}