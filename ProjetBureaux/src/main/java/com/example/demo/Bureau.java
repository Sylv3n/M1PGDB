package com.example.demo;

public class Bureau extends Service {
	private int capacite;
	
	
	public Bureau(String site, boolean etat, int capacite) {
		super(site, etat);
		this.capacite = capacite;
	}
	

}
