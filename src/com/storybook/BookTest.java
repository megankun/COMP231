package com.storybook;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {

	private Book b;
	
	//Constructor Test for Book Object
	@Test
	public void bookTest() {
		b = new Book();
		b.setBookId(1);
		b.setTitle("The Little Engine That Really Couldn't Be Bothered");
		b.setGenre("Autobiography");
	}

}
