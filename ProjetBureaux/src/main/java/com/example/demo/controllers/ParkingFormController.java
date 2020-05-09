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
import com.example.demo.PlaceDeParking;
import com.example.demo.PlaceDeParkingForm;
import com.example.demo.Reservation;
import com.example.demo.Service;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClients;


class GloParking {
	public static ArrayList<PlaceDeParking> liste = new ArrayList<PlaceDeParking>();
	public static Date debutGlo = new Date();
	public static Date finGlo = new Date();
}


@Controller
public class ParkingFormController {

	
	private static final Log log = LogFactory.getLog(MongoApp.class);
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	ArrayList<PlaceDeParking> listePlaceDispo = new ArrayList<PlaceDeParking>();
	
	
	@GetMapping("/ParkingFormP")
	public String receptionBureauForm(Model model, @RequestParam("choixSite") String site1, @RequestParam("etagePlaceParking") String etage1, @RequestParam("dateDebutR") String dateDebut1, @RequestParam("dateFinR") String dateFin1) {
		PlaceDeParkingForm placeDeParkingForm = new PlaceDeParkingForm();
		model.addAttribute("placeDeParkingForm", placeDeParkingForm);
		
		return "ParkingFormP";
	}
	
	
	@PostMapping("/ParkingFormP") 
	public String rechercheBureau(Model model, //
			@ModelAttribute("bureauForm") PlaceDeParkingForm placeDeParkingForm,  @RequestParam("choixSite") String site1, @RequestParam("etagePlaceParking") String etage1, @RequestParam("dateDebutR") String dateDebut1, @RequestParam("dateFinR") String dateFin1) {
		PlaceDeParking placeDeParking = placeDeParkingForm.getPlaceDeParking();
		String site = site1;
		String etage = etage1;
		String etat = "Disponible";
	    String etatPlace = "Disponible";
	    String etatAnnuleReservation = "Annulée";
		
		
		
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
			
			GloParking.debutGlo = debut;
			
			System.out.println(dateFin1);
			Date dateFin = simpleDateFormat.parse(dateFin1);
			String dat1 = simpleDateFormatt.format(dateFin);
			Date fin = simpleDateFormatt.parse(dat1);
			System.out.println(simpleDateFormat.format(dateFin));
			
			GloParking.finGlo = fin;
					

			
			Query query0 = new Query();
	    	query0.addCriteria(Criteria.where("site").is(site));
	    	query0.addCriteria(Criteria.where("etage").is(etage));
	    	query0.addCriteria(Criteria.where("etat").is(etatPlace));

	    	if (mongoOps.exists(query0, PlaceDeParking.class)) {
	    		System.out.println("Yes");
	    		//Bureau b1 = mongoOps.findOne(query0, Bureau.class);
	    		//System.out.println(b1);
		    	//listePlaceDispo.add(b1);
	    		listePlaceDispo = (ArrayList<PlaceDeParking>) mongoOps.find(query0, PlaceDeParking.class);
		    	System.out.println("START"+listePlaceDispo);
		    	//listePlaceDispo.add(b);
		   
				Query query = new Query();
		    	query.addCriteria(Criteria.where("placeParking.site").is(site));
		    	query.addCriteria(Criteria.where("placeParking.etage").is(etage));
		    	//query.addCriteria(Criteria.where("dateDebut").is(debut));
		    	//query.addCriteria(Criteria.where("dateFin").is(fin));
		    	query.addCriteria(Criteria.where("placeParking.etat").is(etat));

		    	//query.addCriteria(Criteria.where("dateFin").is(simpleDateFormat.format(dateFin)));
		    	
		    	
		    	if (mongoOps.exists(query, Reservation.class)) {
				    ArrayList<Reservation> listeR_PlaceParking =  (ArrayList<Reservation>) mongoOps.find(query, Reservation.class);
				    
				    System.out.println(listeR_PlaceParking);
				    //System.out.println(listeR_PlaceParking.get(0).getDateDebut());
				    
				    
				    for (int i = 0; i<listeR_PlaceParking.size(); i++) { 
				    	System.out.println("ETAT : "+listeR_PlaceParking.get(i).getPlaceDeParking().isEtat());
				    	System.out.println("Position 0 "+listeR_PlaceParking.get(0));
				    	System.out.println("TAILLE1 "+listeR_PlaceParking.size());	
				    	System.out.println(listeR_PlaceParking.get(i).getPlaceDeParking().getNumero()+ " "+ listeR_PlaceParking.get(i).getPlaceDeParking().getEtage()+ " "+ listeR_PlaceParking.get(i).getPlaceDeParking().getSite()+ " "+ listeR_PlaceParking.get(i).getPlaceDeParking().isEtat()+ " "+ listeR_PlaceParking.get(i).getDateDebut()+ " "+ listeR_PlaceParking.get(i).getDateFin());
				    	Date start = listeR_PlaceParking.get(i).getDateDebut();
				    	Date end = listeR_PlaceParking.get(i).getDateFin();
				    	
				    	if(!listeR_PlaceParking.get(i).isEtat().toString().equals(etatAnnuleReservation)) {
					    	if ( (!(debut.after(start))) && ( (!(fin.before(start))) || (!(fin.before(end))) )  || (!(debut.before(start))) && (!(end.before(debut))) || (!(debut.before(start))) && (!(fin.after(end))) )    {
					    						    		
					    		System.out.println("Delete : "+ listeR_PlaceParking.get(i).getPlaceDeParking().getNumero());
					    		
					    		for (int k = 0; k<listePlaceDispo.size(); k++) { 
						    		if (listePlaceDispo.get(k).getNumero().equals(listeR_PlaceParking.get(i).getPlaceDeParking().getNumero())) {
						    			listePlaceDispo.remove(k);
						    			k--;
						    			System.out.println("INTER"+listePlaceDispo);
						    		}
						    		System.out.println("iteration "+k);	
					    		}
					    		System.out.println("TAILLE2 "+listeR_PlaceParking.size());	
					    		System.out.println("iterationZ "+i);	
					    		listeR_PlaceParking.remove(i);
					    		i--;
					    		System.out.println("iterationY "+i);	
					    		System.out.println("TAILLE3 "+listeR_PlaceParking.size());	
					    	}			    				    	
					    	System.out.println("iteration "+i);
				    	}
				    }

				    System.out.println("INTERFINALE"+listePlaceDispo);
				    System.out.println(listeR_PlaceParking);
				    
				    String keepBureau = "";
				    
				    
				    
				    for (int t = 0; t<listeR_PlaceParking.size(); t++) { 
				    	System.out.println("Reste" + listeR_PlaceParking.get(t).getPlaceDeParking().getNumero());
				    	
				    	keepBureau = listeR_PlaceParking.get(t).getPlaceDeParking().getNumero();
				    	System.out.println("AYAHHHHHHHH"+keepBureau);
				    	Query query1 = new Query();
				    	query1.addCriteria(Criteria.where("nom").is(keepBureau));
				    	query1.addCriteria(Criteria.where("etat").is(etatPlace));

				    	if (mongoOps.exists(query1, PlaceDeParking.class)) {
				    		System.out.println("HIIIIIIIIIIIIIIIIIIIIIII");
				    		PlaceDeParking b2 = mongoOps.findOne(query1, PlaceDeParking.class);
				    		System.out.println(b2);
					    	//listePlaceDispo.add(b2);
					    	System.out.println("FINAL"+listePlaceDispo);
					    	//listePlaceDispo.add(b);
				    	}
				    	
				    	else if (!mongoOps.exists(query1, PlaceDeParking.class)) {
				    		System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIFAUX");
				    		if (listePlaceDispo.contains(listeR_PlaceParking.get(t).getPlaceDeParking())) {
				    			listePlaceDispo.remove((listeR_PlaceParking.get(t).getPlaceDeParking()));

				    		}
				    		//PlaceDeParking b2 = mongoOps.findOne(query1, PlaceDeParking.class);
				    		//System.out.println(b2);
					    	//listePlaceDispo.add(b2);
					    	System.out.println("FINAL"+listePlaceDispo);
					    	//listePlaceDispo.add(b);
				    	}
				    
				    }

				    System.out.println("AH "+listePlaceDispo);
				    
				    for (int i = 0; i<listePlaceDispo.size(); i++) {
				    	System.out.println(listePlaceDispo.get(i).getNumero()+" "+ listePlaceDispo.get(i).getSite()+" "+ listePlaceDispo.get(i).isEtat()+" "+ listePlaceDispo.get(i).getEtage());
				    }
				    
				   
		    	}
		    	
		    	else if (!mongoOps.exists(query, Reservation.class)) {
			    	System.out.println(query);
			    	System.out.println("Aucune réservation existante sur ces bureaux");	    
		    	}
		    	
	    	}
	    	
	    	else if(!mongoOps.exists(query0, PlaceDeParking.class)) {
	    		System.out.println("Une place de parking répondant à tous ces critères n'existe pas.");
	    		listePlaceDispo = null;
	    	}
			
			
			

	    	
	    	GloParking.liste = listePlaceDispo;
	    	
	    	//System.out.println(r.getIdReservation()+"\n"+r.getDateDebut()+"\n"+r.getDateFin());
	    	
	    	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
  
	    return "redirect:R_ChoixPlaceParking";
	}
	
}
