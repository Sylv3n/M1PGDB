package com.example.demo;

public abstract class Service {
	private String _id;
	public String site;
	public String etat;
	
	
	public Service(String site, String etat) {
		super();
		this.site = site;
		this.etat = etat;
	}


	public String getIdService() {
		return _id;
	}


	public String getSite() {
		return site;
	}


	public String isEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
}
