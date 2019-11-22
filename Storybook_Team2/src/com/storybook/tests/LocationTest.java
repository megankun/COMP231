package com.storybook.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.storybook.entity.Location;

public class LocationTest {

	private Location l;
	
	//Constructor Test for Location Object
	@Test
	public void locationTest() {
		l = new Location();
		l.setLocationId(1);
		l.setName("Le Petit Poisson, Paris");
		l.setDescription("Small Bistro with View");
	}
}
