package com.example.demo.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.BadgeVisiteur;
import com.example.demo.BadgeVisiteurForm;
import com.example.demo.Bureau;
import com.example.demo.BureauForm;
import com.example.demo.CompteEmploye;
import com.example.demo.MongoApp;
import com.example.demo.PlateauRepas;
import com.example.demo.PlateauRepasForm;
import com.example.demo.Reservation;
import com.example.demo.Service;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClients;
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

import com.example.demo.Bureau;
import com.example.demo.Compte;
import com.example.demo.CompteAdministrateur;
import com.example.demo.CompteEmploye;
import com.example.demo.CompteForm;
import com.example.demo.MongoApp;
import com.mongodb.client.MongoClients;

@Controller
public class BadgeVisiteurFormController {
	private static final Log log = LogFactory.getLog(MongoApp.class);
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	BadgeVisiteur badgeVisiteurDispo;
	
	
	@GetMapping("/BadgeVisiteurFormP")
	public String receptionBadgeVisiteurFormP(Model model, @RequestParam("choixSite") String site1, @RequestParam("dateDebutR") String dateDebut1, @RequestParam("dateFinR") String dateFin1, @RequestParam("nomVisiteur") String nomVisiteur1, @RequestParam("prenomVisiteur") String prenomVisiteur1) {
		BadgeVisiteurForm badgeVisiteurForm = new BadgeVisiteurForm();
		model.addAttribute("badgeVisiteurForm", badgeVisiteurForm);
		
		return "BadgeVisiteurFormP";
	}
	
	
	@PostMapping("/BadgeVisiteurFormP") 
	public String rechercheBadgeVisiteur(Model model, //
			@ModelAttribute("badgeVisiteurForm") BadgeVisiteurForm badgeVisiteurForm,  @RequestParam("choixSite") String site1, @RequestParam("dateDebutR") String dateDebut1, @RequestParam("dateFinR") String dateFin1, @RequestParam("nomVisiteur") String nomVisiteur1, @RequestParam("prenomVisiteur") String prenomVisiteur1) {
		BadgeVisiteur badgeVisiteur = badgeVisiteurForm.getBadgeVisiteur();
		String site = site1;
		String nomVisiteur = nomVisiteur1;
		String prenomVisiteur = prenomVisiteur1;
		String etat = "Disponible";
	    String etatBadge = "Disponible";
		
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	    //simpleDateFormat.setTimeZone (TimeZone.getTimeZone ("UTC+02:00"));
	    
	    SimpleDateFormat simpleDateFormatt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	   // simpleDateFormatt.setTimeZone (TimeZone.getTimeZone ("UTC+02:00"));
	    
	    
		try {
			
			System.out.println(dateDebut1);
			Date dateDebut = simpleDateFormat.parse(dateDebut1);
			System.out.println(dateDebut);
			String dat = simpleDateFormatt.format(dateDebut);
			Date debut = simpleDateFormatt.parse(dat);
			System.out.println(dat);
			System.out.println(debut);
			System.out.println(simpleDateFormat.format(dateDebut));
			
			System.out.println(dateFin1);
			Date dateFin = simpleDateFormat.parse(dateFin1);
			String dat1 = simpleDateFormatt.format(dateFin);
			Date fin = simpleDateFormatt.parse(dat1);
			System.out.println(simpleDateFormat.format(dateFin));
			
			System.out.println(site);
			System.out.println(nomVisiteur);
			System.out.println(prenomVisiteur);
			System.out.println(etatBadge);
			
			
			BadgeVisiteur badge1 = new BadgeVisiteur(site, etatBadge, nomVisiteur, prenomVisiteur);
		    mongoOps.save(badge1);
		    
			Query query0 = new Query();
	    	query0.addCriteria(Criteria.where("site").is(site));
	    	query0.addCriteria(Criteria.where("etat").is(etatBadge));
	    	query0.addCriteria(Criteria.where("nomVisiteur").is(nomVisiteur));
	    	query0.addCriteria(Criteria.where("prenomVisiteur").is(prenomVisiteur));


	    	if (mongoOps.exists(query0, BadgeVisiteur.class)) {
	    		System.out.println("Yes");
	    		//PlateauRepas b1 = mongoOps.findOne(query0, PlateauRepas.class);
	    		//System.out.println(b1);
		    	//listePlateauDispo.add(b1);
	    		badgeVisiteurDispo = mongoOps.findOne(query0, BadgeVisiteur.class);
		    	System.out.println("START"+badgeVisiteurDispo);
		    	
		    	Reservation r1 = new Reservation(badgeVisiteurDispo, GloCompte.userNameGlo, debut, fin, "Effectuée");
		    	mongoOps.save(r1);
	    		System.out.println("La réservation du badge pour "+badgeVisiteurDispo.getNomVisiteur()+" "+badgeVisiteurDispo.getPrenomVisiteur()+" a bien été effectuée");

		    }
		    	
	    	
	    	else if(!mongoOps.exists(query0, BadgeVisiteur.class)){
	    		System.out.println("La création du badge pour "+badgeVisiteurDispo.getNomVisiteur()+" "+badgeVisiteurDispo.getPrenomVisiteur()+" a échoué");
	    	}
		   
				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	    
	    return "redirect:store";
	}
}
