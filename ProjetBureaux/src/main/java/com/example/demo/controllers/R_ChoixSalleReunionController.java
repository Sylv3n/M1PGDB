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
import com.example.demo.Reservation;
import com.example.demo.SalleDeReunion;
import com.mongodb.client.MongoClients;

@Controller
public class R_ChoixSalleReunionController {
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	//BureauFormController b;
	
	//ArrayList<Bureau> listeBureauDispo = b.getListeBureauDispo();
	List<SalleDeReunion> listeSalleDispo = GloReunion.liste;
	
	@RequestMapping(value = {"/R_ChoixSalleReunion"}, method = RequestMethod.GET)
	public String R_ChoixSalleReunion(Model model) {
		
		model.addAttribute("listeSalleDispo", GloReunion.liste);
		System.out.println("++++++4584854545" + GloReunion.liste);
		System.out.println("x"+ GloCompte.userNameGlo);
		System.out.println("c"+ GloCompte.isAdminGlo);
		System.out.println("y"+ GloReunion.debutGlo);
		System.out.println("z"+ GloReunion.finGlo);
	    
		return "R_ChoixSalleReunion";
	}
	
	@RequestMapping(value = {"/RetrievesNameSalleReservation"}, method = RequestMethod.GET)
	public String RetrievesNameSalle(Model model, HttpServletRequest request) {
		
		String nomSalleReunion = request.getParameter("getNom");
		System.out.println(nomSalleReunion);
		
		for (int i = 0; i < GloReunion.liste.size(); i++) {
			if (GloReunion.liste.get(i).getNom().equals(nomSalleReunion)) {
				System.out.println("1");
				Reservation r1 = new Reservation(GloReunion.liste.get(i), GloCompte.userNameGlo, GloReunion.debutGlo, GloReunion.finGlo, "Effectuée");
			    mongoOps.save(r1);
			}
			
			else if(!(GloReunion.liste.get(i).getNom().equals(nomSalleReunion))) {
				System.out.println("2");
			}
		}
	    
	    
		return "redirect:store";
	}
	
	
	/*@ModelAttribute("listeBureauDispo")
	public List<Bureau> bureauList() {
		System.out.println("coucou2");
		return Glo.liste;
	}*/
}
