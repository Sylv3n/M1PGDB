package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.Bureau;
import com.example.demo.PlaceDeParking;
import com.example.demo.Reservation;
import com.example.demo.SalleDeReunion;
import com.mongodb.client.MongoClients;

@Controller
public class R_ChoixPlaceParkingController {
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	//BureauFormController b;
	
	//ArrayList<Bureau> listeBureauDispo = b.getListeBureauDispo();
	List<PlaceDeParking> listePlaceDispo = GloParking.liste;
	
	@RequestMapping(value = {"/R_ChoixPlaceParking"}, method = RequestMethod.GET)
	public String R_ChoixPlaceParking(Model model) {
		
		model.addAttribute("listePlaceDispo", GloParking.liste);
		System.out.println("++++++4584854545" + GloParking.liste);
		System.out.println("x"+ GloCompte.userNameGlo);
		System.out.println("c"+ GloCompte.isAdminGlo);
		System.out.println("y"+ GloParking.debutGlo);
		System.out.println("z"+ GloParking.finGlo);
	    
		return "R_ChoixPlaceParking";
	}
	
	@RequestMapping(value = {"/RetrievesNamePlaceParkingReservation"}, method = RequestMethod.GET)
	public String RetrievesNameBureau(Model model, HttpServletRequest request) {
		
		String numeroPlaceParking = request.getParameter("getNumero");
		System.out.println(numeroPlaceParking);
		
		for (int i = 0; i < GloParking.liste.size(); i++) {
			if (GloParking.liste.get(i).getNumero().equals(numeroPlaceParking)) {
				System.out.println("1");
				Reservation r1 = new Reservation(GloParking.liste.get(i), GloCompte.userNameGlo, GloParking.debutGlo, GloParking.finGlo, "Effectuée");
			    mongoOps.save(r1);
			}
			
			else if(!(GloParking.liste.get(i).getNumero().equals(numeroPlaceParking))) {
				System.out.println("2");
			}
		}
	    
	    
		return "R_ChoixPlaceParking";
	}
	
	
	/*@ModelAttribute("listeBureauDispo")
	public List<Bureau> bureauList() {
		System.out.println("coucou2");
		return Glo.liste;
	}*/
}
