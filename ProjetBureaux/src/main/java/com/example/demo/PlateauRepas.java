package com.example.demo;

public class PlateauRepas extends Service{
	private String type; 
	
	
	public PlateauRepas(String site, boolean etat, String type) {
		super(site, etat);
		this.type = type;
	}

	
}
