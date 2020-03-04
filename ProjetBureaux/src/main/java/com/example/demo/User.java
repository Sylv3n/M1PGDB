package com.example.demo;

public class User {
	private String id;
	private String userName;
	private String password;


	public User (String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	public String getId() {
		return id;
	}


	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}
	
}
