package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AccueilController {
	
	@GetMapping("/Services")
	public String reserver() {
		return "Services";
	}
	
	
	@GetMapping("/storePage")
	public String mesReservations() {
		return "redirect:store";
	}
	
}
