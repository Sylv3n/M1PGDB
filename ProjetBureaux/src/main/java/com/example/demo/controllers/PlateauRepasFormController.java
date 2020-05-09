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


@Controller
public class PlateauRepasFormController {
	private static final Log log = LogFactory.getLog(MongoApp.class);
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	PlateauRepas plateauDispo;
	
	
	@GetMapping("/PlateauRepasFormP")
	public String receptionBureauForm(Model model, @RequestParam("choixSite") String site1, @RequestParam("choixRepas") String type1, @RequestParam("dateDebutR") String dateDebut1) {
		PlateauRepasForm plateauRepasForm = new PlateauRepasForm();
		model.addAttribute("plateauRepasForm", plateauRepasForm);
		
		return "PlateauRepasFormP";
	}
	
	
	@PostMapping("/PlateauRepasFormP") 
	public String rechercheBureau(Model model, //
			@ModelAttribute("plateauRepasForm") PlateauRepasForm plateauRepasForm,  @RequestParam("choixSite") String site1, @RequestParam("choixRepas") String type1, @RequestParam("dateDebutR") String dateDebut1) {
		PlateauRepas plateauRepas = plateauRepasForm.getPlateauRepas();
		String site = site1;
		String type = type1;
		String etat = "Disponible";
	    String etatPlateau = "Disponible";
		
		
		
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
			
			System.out.println(site);
			System.out.println(type);
			System.out.println(etatPlateau);
			
			//Création de la requête à destination de la base de données
			Query query0 = new Query();
	    	query0.addCriteria(Criteria.where("site").is(site));
	    	query0.addCriteria(Criteria.where("type").is(type));
	    	query0.addCriteria(Criteria.where("etat").is(etatPlateau));

	    	//Vérifie si le plateau repas correspondant aux critères de l'acteur existe ou non
	    	if (mongoOps.exists(query0, PlateauRepas.class)) {

	    		//Récupère le plateau repas correspondant au critères de l'acteur s'il existe
	    		plateauDispo = mongoOps.findOne(query0, PlateauRepas.class);
		    	
		    	//Récupère le stock restant du plateau repas souhaité par l'acteur
		    	int stockPlateauRepas = plateauDispo.getStock();

		    	//S'il reste du stock, création de la réservation, décrémentation du stock disponible, modification de la valeur du stock dans la base de données
		    	if(stockPlateauRepas > 0) {
		    		Reservation r1 = new Reservation(plateauDispo, GloCompte.userNameGlo, debut, "Effectuée");
			    	mongoOps.save(r1);

		    		stockPlateauRepas--;

		    		plateauDispo.setStock(stockPlateauRepas);
		    		mongoOps.save(plateauDispo);

		    	}
		    	
		    	//S'il n'y a plus de stock 
		    	else if(!(plateauDispo.getStock() > 0)){
		    		//Changement de l'état du plateau repas
		    		plateauDispo.setEtat("Indisponible");
		    		mongoOps.save(plateauDispo);
		    		System.out.println("Le plateau "+plateauDispo.getNom()+": "+plateauDispo.getType()+" n'est plus disponible. Veuillez choisir un autre type de plateau repas");
		    		//ALERTE PLATEAU INDISPONIBLE
		    	}
		    	
	    	}
	    	
	    	else if(!mongoOps.exists(query0, PlateauRepas.class)){
	    		System.out.println("Le plateau repas répondant à tous ces critères n'est malheuresement pas disponible.");
	    	}
		   
				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	    
	    return "redirect:PlateauRepasFormPage";
	}
}
