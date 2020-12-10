package com.eshop.entity;

import org.springframework.stereotype.Component;

@Component
public class User{
	private String username;
	private String password;
	private Integer usertype;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public User(String username, String password, Integer usertype) {
		super();
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", usertype=" + usertype + "]";
	}
	
	
	
}
