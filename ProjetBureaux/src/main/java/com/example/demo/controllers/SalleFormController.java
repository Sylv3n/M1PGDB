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
import com.example.demo.Reservation;
import com.example.demo.SalleDeReunion;
import com.example.demo.SalleDeReunionForm;
import com.example.demo.Service;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClients;


class GloReunion {
	public static ArrayList<SalleDeReunion> liste = new ArrayList<SalleDeReunion>();
	public static Date debutGlo = new Date();
	public static Date finGlo = new Date();
}


@Controller
public class SalleFormController {
	private static final Log log = LogFactory.getLog(MongoApp.class);
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	ArrayList<SalleDeReunion> listeSalleDispo = new ArrayList<SalleDeReunion>();
	
	
	@GetMapping("/SalleDeReunionFormP")
	public String receptionBureauForm(Model model, @RequestParam("choixSite") String site1, @RequestParam("capaciteSalle") String capacite1, @RequestParam("dateDebutR") String dateDebut1, @RequestParam("dateFinR") String dateFin1) {
		SalleDeReunionForm salleDeReunionForm = new SalleDeReunionForm();
		model.addAttribute("salleDeReunionForm", salleDeReunionForm);
			
		return "SalleDeReunionFormP";
	}
	
	
	@PostMapping("/SalleDeReunionFormP") 
	public String rechercheBureau(Model model, //
			@ModelAttribute("salleDeReunionForm") SalleDeReunionForm salleDeReunionForm,  @RequestParam("choixSite") String site1, @RequestParam("capaciteSalle") String capacite1, @RequestParam("dateDebutR") String dateDebut1, @RequestParam("dateFinR") String dateFin1) {
		SalleDeReunion salleDeReunion = salleDeReunionForm.getSalleReunion();
		String site = site1;
		String capacite = capacite1;
		String etat = "Disponible";
	    String etatSalleReunion = "Disponible";
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
			
			GloReunion.debutGlo = debut;
			
			System.out.println(dateFin1);
			Date dateFin = simpleDateFormat.parse(dateFin1);
			String dat1 = simpleDateFormatt.format(dateFin);
			Date fin = simpleDateFormatt.parse(dat1);
			System.out.println(simpleDateFormat.format(dateFin));
			
			GloReunion.finGlo = fin;
					
			
			System.out.println(site);
			System.out.println(capacite);
			System.out.println(etat);
			
			
			Query query0 = new Query();
	    	query0.addCriteria(Criteria.where("site").is(site));
	    	query0.addCriteria(Criteria.where("capacite").is(capacite));
	    	query0.addCriteria(Criteria.where("etat").is(etatSalleReunion));

	    	if (mongoOps.exists(query0, SalleDeReunion.class)) {
	    		System.out.println("Yes");
	    		//Bureau b1 = mongoOps.findOne(query0, Bureau.class);
	    		//System.out.println(b1);
		    	//listeSalleDispo.add(b1);
	    		listeSalleDispo = (ArrayList<SalleDeReunion>) mongoOps.find(query0, SalleDeReunion.class);
		    	System.out.println("START"+listeSalleDispo);
		    	//listeSalleDispo.add(b);
		   
				Query query = new Query();
		    	query.addCriteria(Criteria.where("salleReunion.site").is(site));
		    	query.addCriteria(Criteria.where("salleReunion.capacite").is(capacite));
		    	//query.addCriteria(Criteria.where("dateDebut").is(debut));
		    	//query.addCriteria(Criteria.where("dateFin").is(fin));
		    	query.addCriteria(Criteria.where("salleReunion.etat").is(etat));
		    	//query.addCriteria(Criteria.where("dateFin").is(simpleDateFormat.format(dateFin)));
		    	
		    	
		    	if (mongoOps.exists(query, Reservation.class)) {
				    ArrayList<Reservation> listeR_SalleReunion =  (ArrayList<Reservation>) mongoOps.find(query, Reservation.class);
				    
				    System.out.println(listeR_SalleReunion);
				    //System.out.println(listeR_SalleReunion.get(0).getDateDebut());
				    
				    
				    for (int i = 0; i<listeR_SalleReunion.size(); i++) { 
				    	System.out.println("ETAT : "+listeR_SalleReunion.get(i).getSalleReunion().isEtat());
				    	System.out.println("Position 0 "+listeR_SalleReunion.get(0));
				    	System.out.println("TAILLE1 "+listeR_SalleReunion.size());	
				    	System.out.println(listeR_SalleReunion.get(i).getSalleReunion().getNom()+ " "+ listeR_SalleReunion.get(i).getSalleReunion().getCapacite()+ " "+ listeR_SalleReunion.get(i).getSalleReunion().getSite()+ " "+ listeR_SalleReunion.get(i).getSalleReunion().isEtat()+ " "+ listeR_SalleReunion.get(i).getDateDebut()+ " "+ listeR_SalleReunion.get(i).getDateFin());
				    	Date start = listeR_SalleReunion.get(i).getDateDebut();
				    	Date end = listeR_SalleReunion.get(i).getDateFin();
				    	
				    	if(!listeR_SalleReunion.get(i).isEtat().toString().equals(etatAnnuleReservation)) {
					    	if ( (!(debut.after(start))) && ( (!(fin.before(start))) || (!(fin.before(end))) )  || (!(debut.before(start))) && (!(end.before(debut))) || (!(debut.before(start))) && (!(fin.after(end))) )    {
					    		System.out.println("Delete : "+ listeR_SalleReunion.get(i).getSalleReunion().getNom());
					    		
					    		for (int k = 0; k<listeSalleDispo.size(); k++) { 
						    		if (listeSalleDispo.get(k).getNom().equals(listeR_SalleReunion.get(i).getSalleReunion().getNom())) {
						    			listeSalleDispo.remove(k);
						    			k--;
						    			System.out.println("INTER"+listeSalleDispo);
						    		}
						    		System.out.println("iteration "+k);	
					    		}
					    		System.out.println("TAILLE2 "+listeR_SalleReunion.size());	
					    		System.out.println("iterationZ "+i);	
					    		listeR_SalleReunion.remove(i);
					    		i--;
					    		System.out.println("iterationY "+i);	
					    		System.out.println("TAILLE3 "+listeR_SalleReunion.size());	
					    	}			    				    	
					    	System.out.println("iteration "+i);
				    	}
				    }

				    System.out.println("INTERFINALE"+listeSalleDispo);
				    System.out.println(listeR_SalleReunion);
				    
				    String keepSalleReunion = "";
				    
				    
				    
				    for (int t = 0; t<listeR_SalleReunion.size(); t++) { 
				    	System.out.println("Reste" + listeR_SalleReunion.get(t).getSalleReunion().getNom());
				    	
				    	keepSalleReunion = listeR_SalleReunion.get(t).getSalleReunion().getNom();
				    	System.out.println("AYAHHHHHHHH"+keepSalleReunion);
				    	Query query1 = new Query();
				    	query1.addCriteria(Criteria.where("nom").is(keepSalleReunion));
				    	query1.addCriteria(Criteria.where("etat").is(etatSalleReunion));

				    	if (mongoOps.exists(query1, SalleDeReunion.class)) {
				    		System.out.println("HIIIIIIIIIIIIIIIIIIIIIII");
				    		SalleDeReunion s2 = mongoOps.findOne(query1, SalleDeReunion.class);
				    		System.out.println(s2);
					    	//listeSalleDispo.add(s2);
					    	System.out.println("FINAL"+listeSalleDispo);
					    	//listeSalleDispo.add(b);
				    	}
				    	
				    	else if (!mongoOps.exists(query1, SalleDeReunion.class)) {
				    		System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIFAUX");
				    		if (listeSalleDispo.contains(listeR_SalleReunion.get(t).getSalleReunion())) {
				    			listeSalleDispo.remove((listeR_SalleReunion.get(t).getSalleReunion()));

				    		}
				    		//SalleDeReunion b2 = mongoOps.findOne(query1, SalleDeReunion.class);
				    		//System.out.println(b2);
					    	//listeSalleDispo.add(b2);
					    	System.out.println("FINAL"+listeSalleDispo);
					    	//listeSalleDispo.add(b);
				    	}
				    
				    }

				    System.out.println("AH "+listeSalleDispo);
				    
				    for (int i = 0; i<listeSalleDispo.size(); i++) {
				    	System.out.println(listeSalleDispo.get(i).getNom()+" "+ listeSalleDispo.get(i).getSite()+" "+ listeSalleDispo.get(i).isEtat()+" "+ listeSalleDispo.get(i).getCapacite());
				    }
				    
				   
		    	}
		    	
		    	else if (!mongoOps.exists(query, Reservation.class)) {
			    	System.out.println(query);
			    	System.out.println("Aucune réservation existante sur ces salles de réunion");	    
		    	}
		    	
	    	}
	    	
	    	else if(!mongoOps.exists(query0, SalleDeReunion.class)) {
	    		System.out.println("Une salle de réunion répondant à tous ces critères n'exite pas.");
	    		listeSalleDispo = null;
	    	}
			
			
			

	    	
	    	GloReunion.liste = listeSalleDispo;
	    	
	    	//System.out.println(r.getIdReservation()+"\n"+r.getDateDebut()+"\n"+r.getDateFin());
	    	
	    	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	    
	    return "redirect:R_ChoixSalleReunion";
	}
}
