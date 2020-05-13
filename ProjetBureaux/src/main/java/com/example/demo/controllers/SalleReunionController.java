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
import com.example.demo.CompteAdministrateur;
import com.example.demo.CompteEmploye;
import com.example.demo.MongoApp;
import com.example.demo.SalleDeReunion;
import com.mongodb.client.MongoClients;

@Controller
public class SalleReunionController {

	private static final Log log = LogFactory.getLog(MongoApp.class);
    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	    
	@GetMapping("/SalleDeReunionFormPAdd")
	public String receptionSalleForm(Model model, @RequestParam("choixSite") String site1, @RequestParam("capaciteSalle") String capacite1, @RequestParam("nameNewSalle") String nameSalle1) {	
		return "SalleDeReunionFormPageAdd";
	}
	
	
	
	@PostMapping("/SalleDeReunionFormPAdd") 
	public String rechercheSalle(Model model, @RequestParam("choixSite") String site1, @RequestParam("capaciteSalle") String capacite1, @RequestParam("nameNewSalle") String nameNewSalle1) {
		String site = site1;
		String capacite = capacite1;
		String nameNewSalle = nameNewSalle1;
	    String etatSalle = "Disponible";
	    boolean used = false; 
	    
	    List<SalleDeReunion> people =  mongoOps.findAll(SalleDeReunion.class);
	    System.out.println("Number of people = : " + people.size());
	    
	    for (int i = 0; i < people.size(); i++) {
		    if(people.get(i).getNom().equals(nameNewSalle1)) {
		    	used = true;
		    	System.out.println("Nom déjà utilisé");
		    	return "SalleDeReunionFormPageAdd";
		    }
		    
		    else if(!people.get(i).getNom().equals(nameNewSalle1)) {
		    	used = false;
		    }		    
	    }
	    
	    if (used == false) {
		    //mongoOps.remove(mongoOps.findOne(query(where("userName").is("Oh")), CompteAdministrateur.class));
		    //mongoOps.remove(mongoOps.findById("5e60d08153f2d77005e81166", CompteAdministrateur.class));
	    	SalleDeReunion s1 = new SalleDeReunion(site, etatSalle, nameNewSalle, capacite);
		    mongoOps.save(s1);
		    
		    people =  mongoOps.findAll(SalleDeReunion.class);
		    System.out.println("Number of people = : " + people.size());

		    return "ServicesAdmin";
	    }
	    return "ServicesAdmin";
	}
	
	
	
	@PostMapping("/SalleDeReunionFormPRemove") 
	public String rechercheRemoveSalle(Model model, @RequestParam("choixSite") String site1, @RequestParam("nameRemoveSalle") String nameRemoveSalle1) {
		String site = site1;
		String nameRemoveSalle = nameRemoveSalle1;
		
	    List<SalleDeReunion> people =  mongoOps.findAll(SalleDeReunion.class);
	    System.out.println("Number of people = : " + people.size());
	    	    	
		Query querySuppression = new Query();
		querySuppression.addCriteria(Criteria.where("site").is(site));
		querySuppression.addCriteria(Criteria.where("nom").is(nameRemoveSalle));
		    	
		if (mongoOps.exists(querySuppression, SalleDeReunion.class)) {
			mongoOps.remove(mongoOps.findOne(querySuppression, SalleDeReunion.class));
			System.out.println("Suppression");
			return "ServicesAdmin";
		}
		
		else if(!mongoOps.exists(querySuppression, SalleDeReunion.class)) {
	    	System.out.println("Aucune salle existante ne possède ce nom");
	    	return "SalleDeReunionFormPageRemove";
	    }
		
		
		return "ServicesAdmin";
	}
	
	
}

