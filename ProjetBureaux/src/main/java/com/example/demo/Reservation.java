package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.TimeZone;

public class Reservation {
	public String _id;
	private Bureau bureau;
	public PlaceDeParking placeParking;
	private PlateauRepas plateauRepas;
	private SalleDeReunion salleReunion;
	private BadgeVisiteur badgeVisiteur;
	//private Compte proprietaire;
	private String proprietaire;
	private Date dateDebut;
	private Date dateFin;
	public String etat;
	
	
	public Reservation() {

	}
	
	
	public Reservation(Bureau bureau, String proprietaire, Date dateDebut, Date dateFin, /*Date heureDebut, Date heureFin,*/ String etat) {
		this.bureau = bureau;
		this.proprietaire = proprietaire;
		
		/*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		//simpleDateFormat.setTimeZone (TimeZone.getTimeZone ("UTC+02:00"));
		SimpleDateFormat simpleDateFormatt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		simpleDateFormatt.setTimeZone (TimeZone.getTimeZone ("UTC-02:00"));

	    
		String dat = simpleDateFormat.format(dateDebut);
		System.out.println("TEST1 : "+dat);
		
		 try {
			 Date date = simpleDateFormat.parse(dat);
			 System.out.println("TEST2 : "+date);
				String da = simpleDateFormatt.format(date);
				System.out.println("TEST3 : "+da);
			this.dateDebut = simpleDateFormat.parse(da);
			System.out.println("TEST4 : "+this.dateDebut);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.etat = etat;
	}
	
	
	public Reservation(SalleDeReunion salleReunion, String proprietaire, Date dateDebut, Date dateFin, /*Date heureDebut, Date heureFin,*/ String etat) {
		this.salleReunion = salleReunion;
		this.proprietaire = proprietaire;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.etat = etat;
	}
	
	
	public Reservation(PlaceDeParking placeParking, String proprietaire, Date dateDebut, Date dateFin, /*Date heureDebut, Date heureFin,*/ String etat) {
		this.placeParking = placeParking;
		this.proprietaire = proprietaire;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.etat = etat;
	}
	
	
	public Reservation(PlateauRepas plateauRepas, String proprietaire, Date dateDebut, String etat) {
		this.plateauRepas = plateauRepas;
		this.proprietaire = proprietaire;
		this.dateDebut = dateDebut;
		this.etat = etat;
	}
	
	
	public Reservation(BadgeVisiteur badgeVisiteur, String proprietaire, Date dateDebut, Date dateFin, String etat) {
		this.badgeVisiteur = badgeVisiteur;
		this.proprietaire = proprietaire;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.etat = etat;
	}

	


	public Bureau getBureau() {
		return bureau;
	}
	
	
	public SalleDeReunion getSalleReunion() {
		return salleReunion;
	}
	
	
	public PlaceDeParking getPlaceDeParking() {
		return placeParking;
	}
	
	
	public PlateauRepas getPlateauRepas() {
		return plateauRepas;
	}


	public BadgeVisiteur getBadgeVisiteur() {
		return badgeVisiteur;
	}


	public String getIdReservation() {
		return _id;
	}
	
	
	public String getProprietaire() {
		return proprietaire;
	}


	public Date getDateDebut() {
		return dateDebut;
	}


	public Date getDateFin() {
		return dateFin;
	}


	public String isEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}
	

		
}
