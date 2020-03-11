package com.example.demo.controllers;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.Compte;
import com.example.demo.CompteAdministrateur;
import com.example.demo.CompteEmploye;
import com.example.demo.CompteForm;
import com.example.demo.MongoApp;
import com.mongodb.client.MongoClients;


@Controller
public class indexController {
	
/*    @Value("${welcomeAdmin.message}")
    private String compteA;
 
    @Value("${welcomeEmploye.message}")
    private String compteE;
    
    @Value("${erreurAuthentification.message}")
    private String erreurAuthentification;	*/
    
    
	//private static final Log log = LogFactory.getLog(MongoApp.class);
		
		
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	
	
	@GetMapping("/index")
	public String verifCompte2(Model model) {
		CompteForm compteForm = new CompteForm();
		model.addAttribute("compteForm", compteForm);
		
		return "index";
	}
	
	
	@PostMapping("/index") 
	public String verifCompte(Model model, //
			@ModelAttribute("compteForm") CompteForm compteForm) {
		String userName = compteForm.getUserName();
		String password = compteForm.getPassword();
		
	    Query query = new Query();
	    query.addCriteria(Criteria.where("userName").is(userName));
	    query.addCriteria(Criteria.where("password").is(password));
	    
	    if(mongoOps.exists(query, CompteAdministrateur.class)) {
	    	//model.addAttribute("compteA", compteA);
	    	System.out.println("Vous êtes connecté en tant que Administrateur");
	    }
	    
	    else if (mongoOps.exists(query, CompteEmploye.class)) {
	    	//model.addAttribute("compteE", compteE);
	    	System.out.println("Vous êtes connecté en tant que Employé");
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
