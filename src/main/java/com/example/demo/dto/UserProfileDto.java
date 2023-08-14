package com.example.demo.dto;

import com.example.demo.model.UserAuthDetails;

public class UserProfileDto {
	private int id;
	 private String fname;
	 private String lname;
	 private String email;
	 private UserAuthDetails user_details;
	public UserProfileDto() {
		super();
	}
	public UserProfileDto(int id, String fname, String lname, String email, UserAuthDetails user_details) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.user_details = user_details;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserAuthDetails getUser_details() {
		return user_details;
	}
	public void setUser_details(UserAuthDetails user_details) {
		this.user_details = user_details;
	}
	 
}
	