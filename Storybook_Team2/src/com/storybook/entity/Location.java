package com.storybook.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Location
 *
 */
@Entity

public class Location implements Serializable {

	//Class Variables
	@Id
	private int locationId; 			//Location ID
	private String name;				//Location Name
	private String description;			//Location Details
	private int bookId;					//Book ID
	private static final long serialVersionUID = 1L;

	public Location() {
		super();
	}   
	
	public int getLocationId() {					//Method to Retrieve Location ID
		return this.locationId;
	}

	public void setLocationId(int locationId) {		//Method to Set Location ID
		this.locationId = locationId;
	}   
	public String getName() {						//Method to Retrieve Location Name
		return this.name;
	}

	public void setName(String name) {				//Method to Set Location Name
		this.name = name;
	}   

	public String getDescription() {				//Method to Retrieve Location Information
		return this.description;
	}

	public void setDescription(String description) {	//Method to Set Location Information
		this.description = description;
	}   

	public int getBookId() {						//Method to Retrieve Book ID
		return this.bookId;
	}

	public void setBookId(int bookId) {				//Method to Set Book ID
		this.bookId = bookId;
	}
   
}
