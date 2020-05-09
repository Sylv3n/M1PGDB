package com.example.demo;

public class PlateauRepas extends Service{
	private String nom; 
	private String type;
	private int stock;
	
	
	public PlateauRepas(String site, String etat, String nom, String type, int stock) {
		super(site, etat);
		this.nom = nom;
		this.type = type;
		this.stock = stock;
	}

	
	public String getNom() {
		return nom;
	}


	public String getType() {
		return type;
	}
	
	
	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
