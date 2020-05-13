package com.example.demo.controllers;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Bureau;
import com.example.demo.BureauForm;
import com.example.demo.CompteAdministrateur;
import com.example.demo.CompteEmploye;
import com.example.demo.MongoApp;
import com.example.demo.Reservation;
import com.mongodb.client.MongoClients;

@Controller
public class BureauController {
	private static final Log log = LogFactory.getLog(MongoApp.class);
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");

	@GetMapping("/BureauFormPAdd")
	public String receptionBureauForm(Model model, @RequestParam("choixSite") String site1, @RequestParam("capaciteBureau") String capacite1, @RequestParam("nameNewBureau") String nameNewBureau1) {	
		return "BureauFormPAdd";
	}
	
	
	
	@PostMapping("/BureauFormPAdd") 
	public String rechercheBureau(Model model, @RequestParam("choixSite") String site1, @RequestParam("capaciteBureau") String capacite1, @RequestParam("nameNewBureau") String nameNewBureau1) {
		String site = site1;
		String capacite = capacite1;
		String nameNewBureau = nameNewBureau1;
	    String etatBureau = "Disponible";
	    boolean used = false; 
	    
	    List<Bureau> people =  mongoOps.findAll(Bureau.class);
	    System.out.println("Number of people = : " + people.size());
	    
	    for (int i = 0; i < people.size(); i++) {
		    if(people.get(i).getNom().equals(nameNewBureau)) {
		    	used = true;
		    	System.out.println("Nom déjà utilisé");
		    	return "BureauFormPageAdd";
		    }
		    
		    else if(!people.get(i).getNom().equals(nameNewBureau)) {
		    	used = false;
		    }		    
	    }
	    
	    if (used == false) {
		    //mongoOps.remove(mongoOps.findOne(query(where("userName").is("Oh")), CompteAdministrateur.class));
		    //mongoOps.remove(mongoOps.findById("5e60d08153f2d77005e81166", CompteAdministrateur.class));
		    Bureau b1 = new Bureau(site, etatBureau, nameNewBureau, capacite);
		    mongoOps.save(b1);
		    
		    people =  mongoOps.findAll(Bureau.class);
		    System.out.println("Number of people = : " + people.size());
	

		    return "ServicesAdmin";
	    }
	    return "ServicesAdmin";
	}
	
	
	
	@PostMapping("/BureauFormPRemove") 
	public String rechercheRemoveBureau(Model model, @RequestParam("choixSite") String site1, @RequestParam("nameRemoveBureau") String nameRemoveBureau1) {
		String site = site1;
		String nameRemoveBureau = nameRemoveBureau1;
		System.out.println(site);
		System.out.println(nameRemoveBureau);
		
	    List<Bureau> people =  mongoOps.findAll(Bureau.class);
	    System.out.println("Number of people = : " + people.size());
	    	    	
		Query querySuppression = new Query();
		querySuppression.addCriteria(Criteria.where("site").is(site));
		querySuppression.addCriteria(Criteria.where("nom").is(nameRemoveBureau));
		    	
		if (mongoOps.exists(querySuppression, Bureau.class)) {
			mongoOps.remove(mongoOps.findOne(querySuppression, Bureau.class));
			System.out.println("Suppression");
			return "ServicesAdmin";
		}
		
		else if(!mongoOps.exists(querySuppression, Bureau.class)) {
	    	System.out.println("Aucun bureau existant ne possède ce nom");
	    	return "BureauFormPageRemove";
	    }
		
		
		return "ServicesAdmin";
	}
		   
	
}
