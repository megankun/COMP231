package com.storybook;

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
	public int getStoryId() {
		return this.storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}   
	public int getCharacterId() {
		return this.characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
   
}

class Story_BookCharacter_Id {
	int storyId;
	int characterId;
}
