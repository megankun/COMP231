package com.storybook;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Character
 *
 */
@Entity

public class BookCharacter implements Serializable {

	@Id
	private int characterId;
	private String name;
	private int age;
	private String appereance;
	private int bookId;
	private static final long serialVersionUID = 1L;

	public BookCharacter() {
		super();
	}   
	public int getCharacterId() {
		return this.characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}   
	public String getAppereance() {
		return this.appereance;
	}

	public void setAppereance(String appereance) {
		this.appereance = appereance;
	}   
	public int getBookId() {
		return this.bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
   
}
