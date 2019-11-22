package com.storybook.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Story_BookCharacter
 *
 */
@Entity @IdClass(Story_BookCharacter_Id.class)
public class Story_BookCharacter implements Serializable {

	@Id
	private int storyId;
	@Id
	private int characterId;
	private static final long serialVersionUID = 1L;

	public Story_BookCharacter() {
		super();
	}   

	public int getStoryId() {				//Method to Retrieve Story ID
		return this.storyId;
	}

	public void setStoryId(int storyId) {	//Method to Set Story ID
		this.storyId = storyId;
	}   		

	public int getCharacterId() {			//Method to Retrieve Character ID
		return this.characterId;
	}

	public void setCharacterId(int characterId) {	//Method to Set Character ID
		this.characterId = characterId;
	}
   
}

class Story_BookCharacter_Id {
	int storyId;
	int characterId;
}
