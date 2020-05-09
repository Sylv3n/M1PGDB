package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Bureau;
import com.example.demo.Reservation;
import com.mongodb.client.MongoClients;

@Controller
public class R_ChoixBureauController {
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	//BureauFormController b;
	
	//ArrayList<Bureau> listeBureauDispo = b.getListeBureauDispo();
	List<Bureau> listeBureauDispo = Glo.liste;
	
	@RequestMapping(value = {"/R_ChoixBureau"}, method = RequestMethod.GET)
	public String R_ChoixBureau(Model model) {
		
		model.addAttribute("listeBureauDispo", Glo.liste);
		System.out.println("++++++4584854545" + Glo.liste);
		System.out.println("x"+ GloCompte.userNameGlo);
		System.out.println("c"+ GloCompte.isAdminGlo);
		System.out.println("y"+ Glo.debutGlo);
		System.out.println("z"+ Glo.finGlo);
	    
		return "R_ChoixBureau";
	}
	
	@RequestMapping(value = {"/RetrievesNameBureau"}, method = RequestMethod.GET)
	public String RetrievesNameBureau(Model model, HttpServletRequest request) {
		
		String nomBureau = request.getParameter("getNom");
		System.out.println(nomBureau);
		
		for (int i = 0; i < Glo.liste.size(); i++) {
			if (Glo.liste.get(i).getNom().equals(nomBureau)) {
				System.out.println("1");
				Reservation r1 = new Reservation(Glo.liste.get(i), GloCompte.userNameGlo, Glo.debutGlo, Glo.finGlo, "Effectuée");
			    mongoOps.save(r1);
			}
			
			else if(!(Glo.liste.get(i).getNom().equals(nomBureau))) {
				System.out.println("2");
			}
		}
				
	    
	    
		return "R_ChoixBureau";
	}
	

	
	
	/*@ModelAttribute("listeBureauDispo")
	public List<Bureau> bureauList() {
		System.out.println("coucou2");
		return Glo.liste;
	}*/
	
	
}
