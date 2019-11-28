package com.storybook.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Character
 *
 */
@Entity

public class BookCharacter implements Serializable {

	//class variables
	@Id
	private int characterId; 	//Character ID
	private String name;		//Character Name
	private int age;			//Character Age
	private String appereance;	//Details Related to Character
	private int bookId;			//ID Number for Book
	private static final long serialVersionUID = 1L;

	public BookCharacter() {
		super();
	}   
	
	public int getCharacterId() {					//Method to Retrieve Character ID
		return this.characterId;
	}

	public void setCharacterId(int characterId) { 	//Method to Set Character  ID
		this.characterId = characterId;
	}   
	public String getName() {						//Method to Retrieve Character Name
		return this.name;
	}

	public void setName(String name) {				//Method to Set Character Name
		this.name = name;
	}   
	
	public int getAge() {							//Method to Retrieve Character Age
		return this.age;
	}

	public void setAge(int age) {					//Method to Set Character Age
		this.age = age;
	}   
	
	public String getAppereance() {					//Method to Retrieve Character Details
		return this.appereance;
	}

	public void setAppereance(String appereance) {	//Method to Set Character Appearance
		this.appereance = appereance;
	}   
	
	public int getBookId() {						//Method to Retrieve Book ID
		return this.bookId;
	}

	public void setBookId(int bookId) {				//Method to Set Book ID
		this.bookId = bookId;
	}
}
