package com.example.demo;

import java.util.Date;

public class BureauForm {
	private Service service;
	private Compte proprietaire;
	private Date dateDebut;
	private Date dateFin;
	private Date heureDebut;
	private Date heureFin;
	private boolean etat;
	
	
	public Service getService() {
		return service;
	}
	
	
	public Compte getProprietaire() {
		return proprietaire;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public Date getDateFin() {
		return dateFin;
	}


	public Date getHeureDebut() {
		return heureDebut;
	}


	public Date getHeureFin() {
		return heureFin;
	}


	public boolean isEtat() {
		return etat;
	}

}
