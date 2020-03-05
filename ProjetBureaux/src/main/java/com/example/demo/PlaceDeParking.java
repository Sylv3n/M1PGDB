package com.example.demo;

public class PlaceDeParking extends Service {
	private int numero;
	
	
	public PlaceDeParking(String site, boolean etat, int numero) {
		super(site, etat);
		this.numero = numero;
	}

	
}
