package com.example.demo;

import java.util.Date;

public class Reservation {
	private String idReservation;
	private Service service;
	private Compte proprietaire;
	private Date dateDebut;
	private Date dateFin;
	private Date heureDebut;
	private Date heureFin;
	private boolean etat;
	
	
	public Reservation(Service service, Compte proprietaire, Date dateDebut, Date dateFin, Date heureDebut, Date heureFin, boolean etat) {
		this.service = service;
		this.proprietaire = proprietaire;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.etat = etat;
	}

	
	public Service getService() {
		return service;
	}


	public String getIdReservation() {
		return idReservation;
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
