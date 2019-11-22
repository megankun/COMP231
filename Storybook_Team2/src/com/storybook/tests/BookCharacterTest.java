package com.storybook.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.storybook.entity.BookCharacter;

public class BookCharacterTest {

	private BookCharacter bChar;
	
	//Constructor Test for Book Character Object
	@Test
	public void bookCharacterTest() {
		bChar = new BookCharacter();
		bChar.setCharacterId(1);
		bChar.setName("Bames Jond");
		bChar.setAge(28);
		
		System.out.printf("The Name's Jond,%s", bChar.getName());
	}

}
