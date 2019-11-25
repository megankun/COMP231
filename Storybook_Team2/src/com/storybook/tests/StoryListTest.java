package com.storybook.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.storybook.entity.Book;
import com.storybook.entity.Story;

class StoryListTest {

	private Book b;
	private Story c;
	private Story d;
	
	@Test
	void test() {
		b = new Book();
		b.setBookId(1);
		
		c = new Story();
		c.setStoryId(2);
		
		d = new Story();
		d.setStoryId(3);
		

		System.out.printf("%s", b.getStoryList()); //List All Stories
	}

}
