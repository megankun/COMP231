package com.storybook;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.eclipse.persistence.annotations.HashPartitioning;

/**
 * Entity implementation class for Entity: Book
 *
 */
@Entity
public class Book implements Serializable {
	
	//Class Variables
	@Id
	private int bookId;				//ID Number for Books
	private int userId;				//ID Number for Users
	private String title;			//Book Title
	private String genre;			//Genre of Book
	private static final long serialVersionUID = 1L;
	
	@OneToMany(targetEntity=Story.class, mappedBy="book",cascade=CascadeType.ALL, fetch = FetchType.LAZY) 
	private List<Story> storyList = new ArrayList<Story>();		//Array Holding List of Story Objects

	public Book() {							//Class Constructor
		super();
	}   
	
	public int getBookId() {				//Method to Retrieve Book ID		
		return this.bookId;
	}

	public void setBookId(int bookId) {		//Method to Set Book ID
		this.bookId = bookId;
	}   
	public int getUserId() {				//Method to Retrieve User ID
		return this.userId;
	}

	public void setUserId(int userId) {		//Method to Set User ID
		this.userId = userId;
	}   
	public String getTitle() {				//Method to Retrieve Book Title
		return this.title;
	}

	public void setTitle(String title) {	//Method to Set Book Title
		this.title = title;
	}   
	public String getGenre() {				//Method to Retrieve Book Genre
		return this.genre;
	}
	
	public void setGenre(String genre) {	//Method to Set Book Genre
		this.genre = genre;
	}
	
	public List<Story> getStoryList() {		//Method to Retrieve List of Stories
		return storyList;
	}
	
	public void setStoryList(List<Story> storyList) {	//Method to Set List of Stories
		this.storyList = storyList;
	}
   
}
