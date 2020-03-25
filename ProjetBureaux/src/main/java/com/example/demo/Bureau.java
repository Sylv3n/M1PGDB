package com.example.demo;

public class Bureau extends Service {
	private String nom;
	private int capacite;
	
	
	public Bureau(String site, boolean etat, String nom, int capacite) {
		super(site, etat);
		this.nom = nom;
		this.capacite = capacite;
	}
	

}
