package com.storybook.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.storybook.entity.Story;

public class StoryTest {

	private Story s;
	
	//Constructor Test for Story Object
	@Test
	public void storyTest() {
		s = new Story();
		s.setBookId(1);
		s.setChapterTitle("The Plot");
		s.setNote("Stuff happens when it happens so it happened because it did.");
		System.out.printf("%s", s.getNote());
	}
}
