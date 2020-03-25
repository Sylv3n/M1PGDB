package com.example.demo.controllers;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.BureauForm;
import com.example.demo.CompteAdministrateur;
import com.example.demo.CompteEmploye;
import com.example.demo.CompteForm;
import com.mongodb.client.MongoClients;

@Controller
public class BureauFormController {
	
	
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	
	
	@GetMapping("/BureauForm")
	public String receptionBureauForm(Model model) {
		BureauForm bureauForm = new BureauForm();
		model.addAttribute("bureauForm", bureauForm);
		
		return "index";
	}
	
	
	@PostMapping("/BureauForm") 
	public String rechercheBureau(Model model, //
			@ModelAttribute("bureauForm") BureauForm bureauForm) {
		String userName = bureauForm.getUserName();
		String password = bureauForm.getPassword();
		
	    Query query = new Query();
	    query.addCriteria(Criteria.where("userName").is(userName));
	    query.addCriteria(Criteria.where("password").is(password));
	    
	    if(mongoOps.exists(query, BureauForm.class)) {
	    	//model.addAttribute("compteA", compteA);
	    	System.out.println("Vous êtes connecté en tant que Administrateur");
	    	return "Accueil";
	    }
	    
	    else if (mongoOps.exists(query, BureauForm.class)) {
	    	//model.addAttribute("compteE", compteE);
	    	System.out.println("Vous êtes connecté en tant que Employé");
	    	return "Accueil";
	    }
	    
	    else {
	    	//model.addAttribute("erreurAuthentification", erreurAuthentification);
	    	System.out.println("Votre identifiant et/ou votre mot de passe est incorrect");
	    }
	    
	    /*mongoOps.findOne(query, CompteAdministrateur.class);
	    log.info("Updated: " + query);
	    System.out.println("Compte Administrateur " + query);

	    
	    mongoOps.findOne(query, CompteEmploye.class);;
		log.info("Updated: " + query);
		System.out.println("Compte Employe " + query);*/
		   
	    
	    return "index";
	}
	
	
}
