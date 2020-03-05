package com.example.demo;

public class Compte {
	private String id;
	protected String userName;
	protected String password;


	public Compte (String userName, String password) {
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
