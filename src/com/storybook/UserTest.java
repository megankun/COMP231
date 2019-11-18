package com.storybook;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	private User u;
	
	//Constructor Test for User Object
	@Test
	public void userTest() {
		u = new User();
		u.setUserId(1);
		u.setEmail("Iamtheone@hotmail.com");
		u.setUserType("Writer");		
		u.setFirstName("John");
		u.setLastName("Smith");
	}
}
