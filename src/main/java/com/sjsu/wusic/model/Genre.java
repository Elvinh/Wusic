package com.sjsu.wusic.model;

public class Genre {
	
	private String name;
	
	public Genre() {
		/* empty constructor */ 
	}
	
	public Genre(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}