package com.storybook.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.storybook.entity.Story_BookCharacter;

class CharacterTest {

	private Story_BookCharacter ct;
	
	@Test
	void test() {
		ct = new Story_BookCharacter(); 
		ct.setCharacterId(1);
		ct.setStoryId(1);
	}

}
