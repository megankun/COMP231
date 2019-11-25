package com.storybook.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.storybook.entity.User;

class AboutMeTest {

	private User u;
	
	@Test				//Adding and Printing User About Me
	void test() {
		u = new User();
		u.setUserId(1);
		u.setEmail("UserInfo@hotmail.com");
		u.setUserType("Writer");		
		u.setFirstName("John");
		u.setLastName("Smith");
		u.setAboutUser("I have been writing for 7 years of my life and published 5 successful novels of which 3 were awarded");
		
		
		System.out.printf("%s", u.getAboutUser());
	}

}
