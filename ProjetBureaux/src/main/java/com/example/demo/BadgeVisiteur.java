package com.example.demo;

public class BadgeVisiteur extends Service {
	private String nomVisiteur;
	private String prenomVisiteur;
	
	
	public BadgeVisiteur(String site, String etat, String nomVisiteur, String prenomVisiteur) {
		super(site, etat);
		this.nomVisiteur = nomVisiteur;
		this.prenomVisiteur = prenomVisiteur;
	}

	
	public String getNomVisiteur() {
		return nomVisiteur;
	}


	public String getPrenomVisiteur() {
		return prenomVisiteur;
	}
	
}
