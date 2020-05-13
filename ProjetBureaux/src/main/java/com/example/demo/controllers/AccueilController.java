package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AccueilController {
	
	@GetMapping("/Services")
	public String reserver() {		
		if (!GloCompte.isAdminGlo) {
			return "Services";
		}
		
		else if (GloCompte.isAdminGlo) {
			return "ServicesAdmin";
		}
		
		return "Services";
	}
	
	
	@GetMapping("/storePage")
	public String mesReservations() {
		return "redirect:store";
	}
	
	
	@GetMapping("/About")
	public String aboutPage() {
		return "about";
	}
	
	
	@GetMapping("/Statistiques")
	public String statistiquesPage() {
		if (!GloCompte.isAdminGlo) {
			return "Statistiques";
		}
		
		else if (GloCompte.isAdminGlo) {
			return "StatistiquesAdmin";
		}
		
		return "Statistiques";
	}
	
	
	@GetMapping("/Accueil")
	public String accueilPage() {
		return "Accueil";
	}
	
}
