package com.sjsu.wusic.model;

public class Song {
	private String id;
	private String title;
	private int year;
	private float duration;
	
	public Song(String id, String title, int year, float duration) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.duration = duration;
	}
	
	public Song() {
		// TODO Auto-generated constructor stub
	}


	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	
	
	
}
