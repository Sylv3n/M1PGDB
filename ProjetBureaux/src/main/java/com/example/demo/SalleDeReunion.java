package com.example.demo;

public class SalleDeReunion extends Service {
	private String nom;
	private int capacite;
	
	
	public SalleDeReunion(String site, boolean etat, String nom, int capacite) {
		super(site, etat);
		this.nom = nom;
		this.capacite = capacite;
	}
	
	
}
