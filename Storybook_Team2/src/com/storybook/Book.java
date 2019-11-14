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
	@Id
	private int bookId;
	private int userId;
	private String title;
	private String genre;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(targetEntity=Story.class, mappedBy="book",cascade=CascadeType.ALL, fetch = FetchType.LAZY) 
	private List<Story> storyList = new ArrayList<Story>();

	public Book() {
		super();
	}   
	public int getBookId() {
		return this.bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}   
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public List<Story> getStoryList() {
		return storyList;
	}
	public void setStoryList(List<Story> storyList) {
		this.storyList = storyList;
	}
   
}
