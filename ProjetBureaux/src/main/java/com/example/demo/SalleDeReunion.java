package com.example.demo;

public class SalleDeReunion extends Service {
	private int capacite;
	
	
	public SalleDeReunion(String site, boolean etat, int capacite) {
		super(site, etat);
		this.capacite = capacite;
	}
	
	
}
