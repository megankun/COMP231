package com.storybook;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Story_Location
 *
 */
@Entity @IdClass(Story_Location_Id.class)
public class Story_Location implements Serializable {

	@Id
	private int storyId;
	@Id
	private int locationId;
	private static final long serialVersionUID = 1L;

	public Story_Location() {
		super();
	}   
	public int getStoryId() {
		return this.storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}   
	public int getLocationId() {
		return this.locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
   
}

class Story_Location_Id {
	int storyId;
	int locationId;
}
