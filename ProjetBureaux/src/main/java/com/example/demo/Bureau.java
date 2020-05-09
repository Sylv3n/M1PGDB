package com.example.demo;

public class Bureau extends Service {
	private String nom;
	private String capacite;
	private String site2;
	private String etat2;
	
	public Bureau(String site, String etat, String nom, String capacite) {
		super(site, etat);
		this.nom = nom;
		this.capacite = capacite;

	}


	public String getNom() {
		return nom;
	}


	public String getCapacite() {
		return capacite;
	}
	

}
