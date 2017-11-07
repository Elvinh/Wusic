package com.sjsu.wusic.model;

public class Song {
	private int id;
	private String name;
	private int year;
	
	public Song(int id, String name, int year) {
		this.id = id;
		this.name = name;
		this.year = year;
	}
	
	public Song() {
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		return name;
	}
	
	public int getYear() {
		return year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	
}
