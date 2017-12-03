package com.sjsu.wusic.model;

public class User {

	private String username;
	private String name;
	
	public User() {
		/* empty constructor */
	}
	
	public User(String username, String name) {
		super();
		this.username = username;
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
