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
import com.example.demo.Service;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClients;


class Glo {
	public static ArrayList<Bureau> liste = new ArrayList<Bureau>();
	public static Date debutGlo = new Date();
	public static Date finGlo = new Date();
}


@Controller
public class BureauFormController {
	
	private static final Log log = LogFactory.getLog(MongoApp.class);
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	ArrayList<Bureau> listeBureauDispo = new ArrayList<Bureau>();
	
	
	@GetMapping("/BureauFormP")
	public String receptionBureauForm(Model model, @RequestParam("choixSite") String site1, @RequestParam("capaciteBureau") String capacite1, @RequestParam("dateDebutR") String dateDebut1, @RequestParam("dateFinR") String dateFin1) {
		BureauForm bureauForm = new BureauForm();
		model.addAttribute("bureauForm", bureauForm);
		
		return "BureauFormP";
	}
	
	
	@PostMapping("/BureauFormP") 
	public String rechercheBureau(Model model, //
			@ModelAttribute("bureauForm") BureauForm bureauForm,  @RequestParam("choixSite") String site1, @RequestParam("capaciteBureau") String capacite1, @RequestParam("dateDebutR") String dateDebut1, @RequestParam("dateFinR") String dateFin1) {
		Bureau bureau = bureauForm.getBureau();
		String site = site1;
		String capacite = capacite1;
		String etat = "Disponible";
	    String etatBureau = "Disponible";
	    String etatAnnuleReservation = "Annulée";
		
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	    //simpleDateFormat.setTimeZone (TimeZone.getTimeZone ("UTC+02:00"));
	    
	    SimpleDateFormat simpleDateFormatt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	   // simpleDateFormatt.setTimeZone (TimeZone.getTimeZone ("UTC+02:00"));
	    
	    
		try {
			
			/*System.out.println(dateDebut1);
			Date dateDebut = simpleDateFormat.parse(dateDebut1);
			System.out.println(dateDebut);
			String dat = simpleDateFormatt.format(dateDebut);
			Date d = simpleDateFormatt.parse(dat);
			System.out.println(dat);
			System.out.println(d);
			System.out.println(simpleDateFormat.format(dateDebut));
			
			System.out.println(dateFin1);
			Date dateFin = simpleDateFormat.parse(dateFin1);
			System.out.println(simpleDateFormat.format(dateFin));
			*/
			System.out.println(dateDebut1);
			Date dateDebut = simpleDateFormat.parse(dateDebut1);
			System.out.println(dateDebut);
			String dat = simpleDateFormatt.format(dateDebut);
			Date debut = simpleDateFormatt.parse(dat);
			System.out.println(dat);
			System.out.println(debut);
			System.out.println(simpleDateFormat.format(dateDebut));
			
			Glo.debutGlo = debut;
			
			System.out.println(dateFin1);
			Date dateFin = simpleDateFormat.parse(dateFin1);
			String dat1 = simpleDateFormatt.format(dateFin);
			Date fin = simpleDateFormatt.parse(dat1);
			System.out.println(simpleDateFormat.format(dateFin));
			
			Glo.finGlo = fin;
					
			
			
			
			
			
			Query query0 = new Query();
	    	query0.addCriteria(Criteria.where("site").is(site));
	    	query0.addCriteria(Criteria.where("capacite").is(capacite));
	    	query0.addCriteria(Criteria.where("etat").is(etatBureau));

	    	if (mongoOps.exists(query0, Bureau.class)) {
	    		System.out.println("Yes");
	    		//Bureau b1 = mongoOps.findOne(query0, Bureau.class);
	    		//System.out.println(b1);
		    	//listeBureauDispo.add(b1);
	    		listeBureauDispo = (ArrayList<Bureau>) mongoOps.find(query0, Bureau.class);
		    	System.out.println("START"+listeBureauDispo);
		    	//listeBureauDispo.add(b);
		   
				Query query = new Query();
		    	query.addCriteria(Criteria.where("bureau.site").is(site));
		    	query.addCriteria(Criteria.where("bureau.capacite").is(capacite));
		    	//query.addCriteria(Criteria.where("dateDebut").is(debut));
		    	//query.addCriteria(Criteria.where("dateFin").is(fin));
		    	query.addCriteria(Criteria.where("bureau.etat").is(etat));

		    	//query.addCriteria(Criteria.where("dateFin").is(simpleDateFormat.format(dateFin)));
		    	
		    	
		    	if (mongoOps.exists(query, Reservation.class)) {
				    ArrayList<Reservation> listeR_Bureau =  (ArrayList<Reservation>) mongoOps.find(query, Reservation.class);
				    
				    System.out.println(listeR_Bureau);
				    //System.out.println(listeR_Bureau.get(0).getDateDebut());
				    
				    
				    for (int i = 0; i<listeR_Bureau.size(); i++) { 
				    	System.out.println("ETAT : "+listeR_Bureau.get(i).getBureau().isEtat());
				    	System.out.println("Position 0 "+listeR_Bureau.get(0));
				    	System.out.println("TAILLE1 "+listeR_Bureau.size());	
				    	System.out.println(listeR_Bureau.get(i).getBureau().getNom()+ " "+ listeR_Bureau.get(i).getBureau().getCapacite()+ " "+ listeR_Bureau.get(i).getBureau().getSite()+ " "+ listeR_Bureau.get(i).getBureau().isEtat()+ " "+ listeR_Bureau.get(i).getDateDebut()+ " "+ listeR_Bureau.get(i).getDateFin());
				    	Date start = listeR_Bureau.get(i).getDateDebut();
				    	Date end = listeR_Bureau.get(i).getDateFin();
				    	
				    	if(!listeR_Bureau.get(i).isEtat().toString().equals(etatAnnuleReservation)) {
					    	if ( (!(debut.after(start))) && ( (!(fin.before(start))) || (!(fin.before(end))) )  || (!(debut.before(start))) && (!(end.before(debut))) || (!(debut.before(start))) && (!(fin.after(end))) )    {
					    		System.out.println("Delete : "+ listeR_Bureau.get(i).getBureau().getNom());
					    		
					    		for (int k = 0; k<listeBureauDispo.size(); k++) { 
						    		if (listeBureauDispo.get(k).getNom().equals(listeR_Bureau.get(i).getBureau().getNom())) {
						    			listeBureauDispo.remove(k);
						    			k--;
						    			System.out.println("INTER"+listeBureauDispo);
						    		}
						    		System.out.println("iteration "+k);	
					    		}
					    		System.out.println("TAILLE2 "+listeR_Bureau.size());	
					    		System.out.println("iterationZ "+i);	
					    		listeR_Bureau.remove(i);
					    		i--;
					    		System.out.println("iterationY "+i);	
					    		System.out.println("TAILLE3 "+listeR_Bureau.size());	
					    	}			    				    	
					    	System.out.println("iteration "+i);	
				    	}
				    }

				    System.out.println("INTERFINALE"+listeBureauDispo);
				    System.out.println(listeR_Bureau);
				    
				    String keepBureau = "";
				    
				    
				    
				    for (int t = 0; t<listeR_Bureau.size(); t++) { 
				    	System.out.println("Reste" + listeR_Bureau.get(t).getBureau().getNom());
				    	
				    	keepBureau = listeR_Bureau.get(t).getBureau().getNom();
				    	System.out.println("AYAHHHHHHHH"+keepBureau);
				    	Query query1 = new Query();
				    	query1.addCriteria(Criteria.where("nom").is(keepBureau));
				    	query1.addCriteria(Criteria.where("etat").is(etatBureau));

				    	if (mongoOps.exists(query1, Bureau.class)) {
				    		System.out.println("HIIIIIIIIIIIIIIIIIIIIIII");
				    		Bureau b2 = mongoOps.findOne(query1, Bureau.class);
				    		System.out.println(b2);
					    	//listeBureauDispo.add(b2);
					    	System.out.println("FINAL"+listeBureauDispo);
					    	//listeBureauDispo.add(b);
				    	}
				    	
				    	else if (!mongoOps.exists(query1, Bureau.class)) {
				    		System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIFAUX");
				    		if (listeBureauDispo.contains(listeR_Bureau.get(t).getBureau())) {
				    			listeBureauDispo.remove((listeR_Bureau.get(t).getBureau()));

				    		}
				    		//Bureau b2 = mongoOps.findOne(query1, Bureau.class);
				    		//System.out.println(b2);
					    	//listeBureauDispo.add(b2);
					    	System.out.println("FINAL"+listeBureauDispo);
					    	//listeBureauDispo.add(b);
				    	}
				    
				    }

				    System.out.println("AH "+listeBureauDispo);
				    
				    for (int i = 0; i<listeBureauDispo.size(); i++) {
				    	System.out.println(listeBureauDispo.get(i).getNom()+" "+ listeBureauDispo.get(i).getSite()+" "+ listeBureauDispo.get(i).isEtat()+" "+ listeBureauDispo.get(i).getCapacite());
				    }
				    
				   
		    	}
		    	
		    	else if (!mongoOps.exists(query, Reservation.class)) {
			    	System.out.println(query);
			    	System.out.println("Aucune réservation existante sur ces bureaux");	    
		    	}
		    	
	    	}
	    	
	    	else if(!mongoOps.exists(query0, Bureau.class)) {
	    		System.out.println("Un bureau répondant à tous ces critères n'exite pas.");
	    		listeBureauDispo = null;
	    	}
			
			
			

	    	
	    	Glo.liste = listeBureauDispo;
	    	
	    	//System.out.println(r.getIdReservation()+"\n"+r.getDateDebut()+"\n"+r.getDateFin());
	    	
	    	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		

		
		
	    //Query query = new Query();
	    //query.addCriteria(Criteria.where("service").is(bureau));
	    //query.addCriteria(Criteria.where("site").is(site));
	    //query.addCriteria(Criteria.where("capacite").is(capacite));
	    //query.addCriteria(Criteria.where("dateDebut").is(dateDebut));
	    //query.addCriteria(Criteria.where("dateFin").is(dateFin));
	   /* query.addCriteria(Criteria.where("heureDebut").is(heureDebut));
	    query.addCriteria(Criteria.where("heureFin").is(heureFin));*/
	    //query.addCriteria(Criteria.where("etat").is(etat));
	    
	    /*Query query = new Query();
    	query.addCriteria(Criteria.where("service.site").is(site));
    	query.addCriteria(Criteria.where("service.capacite").is(capacite));
    	query.addCriteria(Criteria.where("dateDebut").is(capacite));
    	Reservation r = mongoOps.findOne(query, Reservation.class);*/
    	
    	
    	//System.out.println(r.getIdReservation()+"\n"+r.getDateDebut()+"\n" +"\n"+r.getDateFin());
    	//d = simpleDateFormat.format(d);
    	/*try {
			Date c = simpleDateFormat.parse(d);
			System.out.println(r.getIdReservation()+"\n"+r.getDateDebut()+"\n"+ c +"\n"+r.getDateFin());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	
	    
	  /*  if(mongoOps.exists(query, Reservation.class)) {
	    	//model.addAttribute("compteA", compteA);
	    	System.out.println(query.getFieldsObject());
	    	
		    mongoOps.findOne(query, Reservation.class);
		    log.info("Updated: " + query);
		    System.out.println("info " + query.getClass());
		    
	    	return "Accueil";
	    }
	    
	    else if (!mongoOps.exists(query, Reservation.class)) {
	    	//model.addAttribute("compteE", compteE);
	    	System.out.println(query);
	    	System.out.println("N'existe pas");
	    	
	    	//List<Reservation> listeR_Bureau =  mongoOps.findAll(Reservation.class);
	    	mongoOps.findOne(query, Reservation.class);
		   // log.info("Updated: " + query);
		   //System.out.println(people);
		    
	    	return "Accueil";
	    	
	    }	  */


		   
	    
	    return "redirect:R_ChoixBureau";
	}

	
}
