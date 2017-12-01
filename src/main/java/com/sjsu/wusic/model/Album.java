package com.sjsu.wusic.model;

public class Album {
	private String id;
	private String name;
	private int year;
	
	public Album(String id, String name, int year) {
		this.id = id;
		this.name = name;
		this.year = year;
	}
	public Album() {
		
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setId(String string) {
		this.id = string;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setYear(int year) {
		this.year = year;
	}

}
