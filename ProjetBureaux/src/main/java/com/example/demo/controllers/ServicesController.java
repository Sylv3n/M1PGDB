package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController {

	
	@GetMapping("/Bureau")
	public String bureau() {
		return "BureauFormPage";
	}
	
	@GetMapping("/Salledereunion")
	public String salledereunion() {
		return "SalleDeReunionFormPage";
	}
	
	@GetMapping("/Placedeparking")
	public String placedeparking() {
		return "ParkingFormPage";
	}
	
	@GetMapping("/Plateaurepas")
	public String plateaurepas() {
		return "PlateauRepasFormPage";
	}
	
	@GetMapping("/Badgevisiteur")
	public String badgevisiteur() {
		return "BadgeVisiteurFormPage";
	}
	
	
	
	
	
	@GetMapping("/BureauAdd")
	public String bureauAdd() {
		return "BureauFormPageAdd";
	}
	
	@GetMapping("/SalledereunionAdd")
	public String salledereunioAdd() {
		return "SalleDeReunionFormPageAdd";
	}
	
	@GetMapping("/PlacedeparkingAdd")
	public String placedeparkingAdd() {
		return "ParkingFormPageAdd";
	}
	
	@GetMapping("/BureauRemove")
	public String bureauRemove() {
		return "BureauFormPageRemove";
	}
	
	@GetMapping("/SalledereunionRemove")
	public String salledereunionRemove() {
		return "SalleDeReunionFormPageRemove";
	}
	
	@GetMapping("/PlacedeparkingRemove")
	public String placedeparkingRemove() {
		return "ParkingFormPageRemove";
	}
}
