package com.example.demo;

public class SalleDeReunion extends Service {
	private String nom;
	private String capacite;
	
	
	public SalleDeReunion(String site, String etat, String nom, String capacite) {
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
