package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController {

	
	@GetMapping("/BureauForm")
	public String bureau() {
		return "BureauForm";
	}
	
	@GetMapping("/Salledereunion")
	public String salledereunion() {
		return "Salledereunion";
	}
	
	@GetMapping("/Placedeparking")
	public String placedeparking() {
		return "Placedeparking";
	}
	
	@GetMapping("/Plateaurepas")
	public String plateaurepas() {
		return "Plateaurepas";
	}
	
	@GetMapping("/Badgevisiteur")
	public String badgevisiteur() {
		return "Badgevisiteur";
	}
	
}
