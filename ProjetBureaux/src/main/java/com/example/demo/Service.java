package com.example.demo;

public abstract class Service {
	private String idService;
	private String site;
	private boolean etat;
	
	
	public Service(String site, boolean etat) {
		super();
		this.site = site;
		this.etat = etat;
	}


	public String getIdService() {
		return idService;
	}


	public String getSite() {
		return site;
	}


	public boolean isEtat() {
		return etat;
	}
	
	
}
