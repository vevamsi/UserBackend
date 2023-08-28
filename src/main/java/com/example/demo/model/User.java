package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;


@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private String first_name;
	private String last_name;
	@Email(message="Not a valid Email Format")
	private String email;
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserAuthDetails userAuthDetails;
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Address address;
	private String departmentCode;
	@Column(name = "role")
    private String role;
	public User() {
		super();
	}
	public User(int user_id, String first_name, String last_name,
			@Email(message = "Not a valid Email Format") String email, UserAuthDetails userAuthDetails, Address address,
			String departmentCode, String role) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.userAuthDetails = userAuthDetails;
		this.address = address;
		this.departmentCode = departmentCode;
		this.role = role;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public UserAuthDetails getUserAuthDetails() {
		return userAuthDetails;
	}
	public void setUserAuthDetails(UserAuthDetails userAuthDetails) {
		this.userAuthDetails = userAuthDetails;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", email="
				+ email + ", userAuthDetails=" + userAuthDetails + ", address=" + address + ", departmentCode="+ departmentCode +", role=" + role + "]";
	}
	
}