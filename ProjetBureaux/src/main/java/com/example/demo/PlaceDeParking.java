package com.example.demo;

public class PlaceDeParking extends Service {
	private String numero;
	private String etage;
	
	
	public PlaceDeParking(String site, String etat, String numero, String etage) {
		super(site, etat);
		this.numero = numero;
		this.etage = etage;
	}

	
	public String getNumero() {
		return numero;
	}


	public String getEtage() {
		return etage;
	}
	
}
