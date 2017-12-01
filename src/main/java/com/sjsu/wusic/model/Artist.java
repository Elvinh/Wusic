package com.sjsu.wusic.model;

import java.sql.Date;

public class Artist {
	
	private String id;
	private String name;

	
	public Artist(String id, String name) {
		super();
		this.id = id;
		this.name = name;

	}

	public Artist() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
