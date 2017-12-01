package com.sjsu.wusic.model;

public class Album {
	private String name;
	private int year;
	private String artwork;
	
	public Album(String name, int year) {
		this.name = name;
		this.year = year;
	}
	public Album() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getYear() {
		return year;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	public String getArtwork() {
		return artwork;
	}
	public void setArtwork(String artwork) {
		this.artwork = artwork;
	}
	

}
