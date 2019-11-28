package com.storybook.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Story
 *
 */
@Entity

public class Story implements Serializable {

	//Class Variables
	@Id
	private int storyId;			//Story ID Number
	private String chapterTitle;	//Title of Chapter
	private String note;			//Quick Notes
	private int bookId;				//Book ID Number
	private String created_at;		//Date Created

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name="bookId")
	private Book book;
	
	public Story() {
		super();
	}   
	public int getStoryId() {							//Method to Retrieve Story ID
		return this.storyId;
	}

	public void setStoryId(int storyId) {				//Method to Set Story ID
		this.storyId = storyId;
	}   
	public String getChapterTitle() {					//Method to Retrieve Chapter Title
		return this.chapterTitle;
	}

	public void setChapterTitle(String chapterTitle) {	//Method to Set Chapter Title
		this.chapterTitle = chapterTitle;
	}   
	public String getNote() {							//Method to Retrieve Story Notes
		return this.note;
	}

	public void setNote(String note) {					//Method to Set Story Notes
		this.note = note;
	}   
	public int getBookId() {							//Method to Retrieve Book ID
		return this.bookId;
	}

	public void setBookId(int bookId) {					//Method to Set Book ID
		this.bookId = bookId;
	}

	public String getCreated_at() {						//Method to Retrieve Date Created
		return created_at;
	}
	public void setCreated_at(String created_at) {		//Method to Set Date Created
		this.created_at = created_at;
	}
	
	public Book getBook() {								//Method to Retrieve Book Object
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
}
