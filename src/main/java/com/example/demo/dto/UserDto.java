package com.example.demo.dto;

public class UserDto {
    private int user_id;
    private String first_name;
    private String last_name;
    private String email;
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int userId) {
		this.user_id = userId;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserDto(int userId, String first_name, String last_name, String email) {
		super();
		this.user_id = userId;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
}