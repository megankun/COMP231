package com.storybook;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Story
 *
 */
@Entity

public class Story implements Serializable {

	@Id
	private int storyId;
	private String chapterTitle;
	private String note;
	private int bookId;
	private String created_at;

	private static final long serialVersionUID = 1L;

	public Story() {
		super();
	}   
	public int getStoryId() {
		return this.storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}   
	public String getChapterTitle() {
		return this.chapterTitle;
	}

	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}   
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}   
	public int getBookId() {
		return this.bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
