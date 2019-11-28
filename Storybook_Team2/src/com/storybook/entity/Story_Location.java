package com.storybook.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Story_Location
 *
 */
@Entity @IdClass(Story_Location_Id.class)
public class Story_Location implements Serializable {

	//Class Variables
	@Id
	private int storyId;							//ID Number for Story
	@Id
	private int locationId;							//ID Number for Location
	private static final long serialVersionUID = 1L;

	public Story_Location() {
		super();
	}   
	public int getStoryId() {						//Method to Retrieve Story ID
		return this.storyId;
	}

	public void setStoryId(int storyId) {			//Method to Set Story ID
		this.storyId = storyId;
	}   
	public int getLocationId() {					//Method to Retrieve Location ID
		return this.locationId;
	}

	public void setLocationId(int locationId) {		//Method to Set Location ID
		this.locationId = locationId;
	}
   
}

class Story_Location_Id {
	int storyId;
	int locationId;
}
