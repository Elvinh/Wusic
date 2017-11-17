package com.sjsu.wusic.model;

import java.sql.Date;

public class Artist {
	
	private int id;
	private String name;
	private Date birthdate;
	private String hometown;
	
	public Artist(int id, String name, Date birthdate, String hometown) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		this.hometown = hometown;
	}

	public Artist() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	
}
