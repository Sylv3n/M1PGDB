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
import com.example.demo.PlaceDeParking;
import com.example.demo.SalleDeReunion;
import com.example.demo.PlaceDeParking;
import com.mongodb.client.MongoClients;

@Controller
public class PlaceParkingController {
	private static final Log log = LogFactory.getLog(MongoApp.class);
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	  
	@GetMapping("/ParkingFormPAdd")
	public String receptionParkingForm(Model model, @RequestParam("choixSite") String site1, @RequestParam("capaciteSalle") String capacite1, @RequestParam("nameNewPlace") String nameNewPlace1) {	
		return "ParkingFormPageAdd";
	}
	
	
	
	@PostMapping("/ParkingFormPAdd") 
	public String rechercheParking(Model model, @RequestParam("choixSite") String site1, @RequestParam("etagePlaceParking") String etage1, @RequestParam("nameNewPlace") String nameNewPlace1) {
		String site = site1;
		String etage = etage1;
		String nameNewPlace = nameNewPlace1;
	    String etatSalle = "Disponible";
	    boolean used = false; 
	    
	    List<PlaceDeParking> people =  mongoOps.findAll(PlaceDeParking.class);
	    System.out.println("Number of people = : " + people.size());
	    
	    for (int i = 0; i < people.size(); i++) {
		    if(people.get(i).getNumero().equals(nameNewPlace1)) {
		    	used = true;
		    	System.out.println("Nom déjà utilisé");
		    	return "ParkingFormPageAdd";
		    }
		    
		    else if(!people.get(i).getNumero().equals(nameNewPlace1)) {
		    	used = false;
		    }		    
	    }
	    
	    if (used == false) {
		    //mongoOps.remove(mongoOps.findOne(query(where("userName").is("Oh")), CompteAdministrateur.class));
		    //mongoOps.remove(mongoOps.findById("5e60d08153f2d77005e81166", CompteAdministrateur.class));
	    	PlaceDeParking p1 = new PlaceDeParking(site, etatSalle, nameNewPlace, etage);
		    mongoOps.save(p1);
		    
		    people =  mongoOps.findAll(PlaceDeParking.class);
		    System.out.println("Number of people = : " + people.size());

		    return "ServicesAdmin";
	    }
	    return "ServicesAdmin";
	}
	
	
	
	@PostMapping("/ParkingFormPRemove") 
	public String rechercheRemoveParking(Model model, @RequestParam("choixSite") String site1,  @RequestParam("nameRemovePlace") String nameRemovePlace1) {
		String site = site1;
		String nameRemovePlace = nameRemovePlace1;
		
	    List<PlaceDeParking> people =  mongoOps.findAll(PlaceDeParking.class);
	    System.out.println("Number of people = : " + people.size());
	    	    	
		Query querySuppression = new Query();
		querySuppression.addCriteria(Criteria.where("site").is(site));
		querySuppression.addCriteria(Criteria.where("numero").is(nameRemovePlace));
		    	
		if (mongoOps.exists(querySuppression, PlaceDeParking.class)) {
			mongoOps.remove(mongoOps.findOne(querySuppression, PlaceDeParking.class));
			System.out.println("Suppression");
			return "ServicesAdmin";
		}
		
		else if(!mongoOps.exists(querySuppression, PlaceDeParking.class)) {
	    	System.out.println("Aucune place existante ne possède ce nom");
	    	return "ParkingFormPageRemove";
	    }
		
		
		return "ServicesAdmin";
	}
	
}


