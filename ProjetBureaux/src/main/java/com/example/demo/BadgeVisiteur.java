package com.example.demo;

public class BadgeVisiteur extends Service {
	private String nom;
	private String prenom;
	
	
	public BadgeVisiteur(String site, boolean etat, String nom, String prenom) {
		super(site, etat);
		this.nom = nom;
		this.prenom = prenom;
	}

	
}
