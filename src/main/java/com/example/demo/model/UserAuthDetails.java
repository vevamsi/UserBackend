package com.example.demo.model;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_details")
public class UserAuthDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message="password must not be blank")
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
	public UserAuthDetails() {
		super();
	}
	public UserAuthDetails(int id, @NotBlank(message = "password must not be blank") String password, User user) {
		super();
		this.id = id;
		this.password = password;
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "UserAuthDetails [id=" + id + ", password=" + password + ", user=" + user + "]";
	}
}
