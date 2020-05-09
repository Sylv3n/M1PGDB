package com.example.demo.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.BadgeVisiteur;
import com.example.demo.Bureau;
import com.example.demo.MongoApp;
import com.example.demo.PlaceDeParking;
import com.example.demo.PlateauRepas;
import com.example.demo.Reservation;
import com.example.demo.SalleDeReunion;
import com.example.demo.SalleDeReunionForm;
import com.mongodb.client.MongoClients;

@Controller
public class storeController {
	private static final Log log = LogFactory.getLog(MongoApp.class);
	MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	
	ArrayList<Reservation> listeReservationDispo = new ArrayList<Reservation>();
	ArrayList<Reservation> listeRBureauDispo = new ArrayList<Reservation>();
	ArrayList<Reservation> listeRSalleDispo = new ArrayList<Reservation>();
	ArrayList<Reservation> listeRPlaceDispo = new ArrayList<Reservation>();
	ArrayList<Reservation> listeRRepasDispo = new ArrayList<Reservation>();
	ArrayList<Reservation> listeRBadgeDispo = new ArrayList<Reservation>();
	
	
	
	@RequestMapping(value = {"/store"}, method = RequestMethod.GET)
	public String store(Model model) {
		
		Query queryReservation = new Query();
		queryReservation.addCriteria(Criteria.where("proprietaire").is(GloCompte.userNameGlo));
    	
		listeReservationDispo.removeAll(listeReservationDispo);
		listeRBureauDispo.removeAll(listeRBureauDispo);
		listeRSalleDispo.removeAll(listeRSalleDispo);
		listeRPlaceDispo.removeAll(listeRPlaceDispo);
		listeRRepasDispo.removeAll(listeRRepasDispo);
		listeRBadgeDispo.removeAll(listeRBadgeDispo);
    	
    	if (mongoOps.exists(queryReservation, Reservation.class)) {
    		listeReservationDispo =  (ArrayList<Reservation>) mongoOps.find(queryReservation, Reservation.class);
    		System.out.println(listeReservationDispo);
    		System.out.println(listeReservationDispo.get(0).getBureau().getClass());
    		
    		for (int i = 0; i < listeReservationDispo.size(); i++) {
    			if (listeReservationDispo.get(i).getBureau() != null) {
    				listeRBureauDispo.add(listeReservationDispo.get(i));
    				System.out.println(listeReservationDispo.get(i).getBureau());
    				System.out.println(listeReservationDispo.get(i).getBureau().getNom());
    				System.out.println(listeRBureauDispo);
    				System.out.println(listeReservationDispo.get(i).getIdReservation());
    			}
    			  			
    			else if (listeReservationDispo.get(i).getSalleReunion() != null) {
    				listeRSalleDispo.add(listeReservationDispo.get(i));
    				System.out.println(listeReservationDispo.get(i).getSalleReunion());
    				System.out.println(listeReservationDispo.get(i).getSalleReunion().getNom());
    			}
    			
    			else if (listeReservationDispo.get(i).getPlaceDeParking() != null) {
    				listeRPlaceDispo.add(listeReservationDispo.get(i));
    				System.out.println(listeReservationDispo.get(i).getPlaceDeParking());
    				System.out.println(listeReservationDispo.get(i).getPlaceDeParking().getNumero());
    			}
    			
    			else if (listeReservationDispo.get(i).getPlateauRepas() != null) {
    				listeRRepasDispo.add(listeReservationDispo.get(i));
    				System.out.println(listeReservationDispo.get(i).getPlateauRepas());
    				System.out.println(listeReservationDispo.get(i).getPlateauRepas().getNom());
    			}
    			
    			else if (listeReservationDispo.get(i).getBadgeVisiteur() != null) {
    				listeRBadgeDispo.add(listeReservationDispo.get(i));
    				System.out.println(listeReservationDispo.get(i).getBadgeVisiteur());
    				System.out.println(listeReservationDispo.get(i).getBadgeVisiteur().getNomVisiteur());
    			}
    		}
    		
    	}
    	
    	else if (!mongoOps.exists(queryReservation, Reservation.class)){
    		System.out.println("Aucune réservation n'a été effectuée sur ce compte");
    	}
    	
		 
		model.addAttribute("listeRBureauDispo", listeRBureauDispo);
		model.addAttribute("listeRSalleDispo", listeRSalleDispo);
		model.addAttribute("listeRPlaceDispo", listeRPlaceDispo);
		model.addAttribute("listeRRepasDispo", listeRRepasDispo);
		model.addAttribute("listeRBadgeDispo", listeRBadgeDispo);
			
		return "store";
	}
	
	
	@RequestMapping(value = {"/RetrievesNameRBureau"}, method = RequestMethod.GET)
	public String RetrievesNameRBureau(Model model, HttpServletRequest request) {
		
		String idBureau = request.getParameter("getID");
		System.out.println(idBureau);
	    
		for (int i = 0; i < listeRBureauDispo.size(); i++) {
			
			if(listeRBureauDispo.get(i).getIdReservation().toString().equals(idBureau)) {
				
				System.out.println("Ma liste : "+listeRBureauDispo);
				Query queryReservation2 = new Query();
				queryReservation2.addCriteria(Criteria.where("_id").is(listeRBureauDispo.get(i).getIdReservation().toString()));
				if (mongoOps.exists(queryReservation2, Reservation.class)) {
					Reservation rBureau = mongoOps.findOne(queryReservation2, Reservation.class);
					rBureau.setEtat("Annulée");
					mongoOps.save(rBureau);
					System.out.println("Annulée");
				}
			}
		}
		return "store";
	}
	
	
	@RequestMapping(value = {"/RetrievesNameRSalleReservation"}, method = RequestMethod.GET)
	public String RetrievesNameRSalleReservation(Model model, HttpServletRequest request) {
		
		String idSalle = request.getParameter("getID");
		System.out.println(idSalle);
	    
		for (int i = 0; i < listeRSalleDispo.size(); i++) {
			
			if(listeRSalleDispo.get(i).getIdReservation().toString().equals(idSalle)) {
				
				System.out.println("Ma liste : "+listeRSalleDispo);
				Query queryReservation3 = new Query();
				queryReservation3.addCriteria(Criteria.where("_id").is(listeRSalleDispo.get(i).getIdReservation().toString()));
				if (mongoOps.exists(queryReservation3, Reservation.class)) {
					Reservation rSalle = mongoOps.findOne(queryReservation3, Reservation.class);
					rSalle.setEtat("Annulée");
					mongoOps.save(rSalle);
					System.out.println("Annulée");
				}
			}
		}
	    
		return "store";
	}
	
	
	@RequestMapping(value = {"/RetrievesNameRPlaceParkingReservation"}, method = RequestMethod.GET)
	public String RetrievesNameRPlaceParkingReservation(Model model, HttpServletRequest request) {
		
		String idPlace = request.getParameter("getID");
		System.out.println(idPlace);
	    
		for (int i = 0; i < listeRPlaceDispo.size(); i++) {
			
			if(listeRPlaceDispo.get(i).getIdReservation().toString().equals(idPlace)) {
				
				System.out.println("Ma liste : "+listeRPlaceDispo);
				Query queryReservation4 = new Query();
				queryReservation4.addCriteria(Criteria.where("_id").is(listeRPlaceDispo.get(i).getIdReservation().toString()));
				if (mongoOps.exists(queryReservation4, Reservation.class)) {
					Reservation rPlace = mongoOps.findOne(queryReservation4, Reservation.class);
					rPlace.setEtat("Annulée");
					mongoOps.save(rPlace);
					System.out.println("Annulée");
				}
			}
		}
		
		return "store";
	}
	
	
	@RequestMapping(value = {"/RetrievesNameRPlateauRepasReservation"}, method = RequestMethod.GET)
	public String RetrievesNameRPlateauRepasReservation(Model model, HttpServletRequest request) {
		
		String idRepas = request.getParameter("getID");
		System.out.println(idRepas);
	    
		for (int i = 0; i < listeRRepasDispo.size(); i++) {
			
			if(listeRRepasDispo.get(i).getIdReservation().toString().equals(idRepas)) {
				
				System.out.println("Ma liste : "+listeRRepasDispo);
				Query queryReservation5 = new Query();
				queryReservation5.addCriteria(Criteria.where("_id").is(listeRRepasDispo.get(i).getIdReservation().toString()));
				if (mongoOps.exists(queryReservation5, Reservation.class)) {
					Reservation rRepas = mongoOps.findOne(queryReservation5, Reservation.class);
					rRepas.setEtat("Annulée");
					mongoOps.save(rRepas);
					System.out.println("Annulée");
					
					Query ajoutStock = new Query();
					ajoutStock.addCriteria(Criteria.where("nom").is(rRepas.getPlateauRepas().getNom()));
					ajoutStock.addCriteria(Criteria.where("site").is(rRepas.getPlateauRepas().getSite()));
					ajoutStock.addCriteria(Criteria.where("type").is(rRepas.getPlateauRepas().getType()));
					

			    	if (mongoOps.exists(ajoutStock, PlateauRepas.class)) {
			    		//PlateauRepas b1 = mongoOps.findOne(query0, PlateauRepas.class);
			    		//System.out.println(b1);
				    	//listePlateauDispo.add(b1);
			    		PlateauRepas pAjout = mongoOps.findOne(ajoutStock, PlateauRepas.class);
						int stockPlateauRepasR = pAjout.getStock();
						System.out.println("Valeur STOCK"+stockPlateauRepasR);
						stockPlateauRepasR++;
			    		System.out.println("Valeur STOCK FINALE"+stockPlateauRepasR);
			    		pAjout.setStock(stockPlateauRepasR);
			    		mongoOps.save(pAjout);
			    	}
				}
			}
		}
		
		return "store";
	}
	
	
	@RequestMapping(value = {"/RetrievesNameRBadgeVisiteurReservation"}, method = RequestMethod.GET)
	public String RetrievesNameRBadgeVisiteurReservation(Model model, HttpServletRequest request) {
		
		String idVisiteur= request.getParameter("getID");
		System.out.println(idVisiteur);
	    
		for (int i = 0; i < listeRBadgeDispo.size(); i++) {
			
			if(listeRBadgeDispo.get(i).getIdReservation().toString().equals(idVisiteur)) {
				
				System.out.println("Ma liste : "+listeRBadgeDispo);
				Query queryReservation6 = new Query();
				queryReservation6.addCriteria(Criteria.where("_id").is(listeRBadgeDispo.get(i).getIdReservation().toString()));
				if (mongoOps.exists(queryReservation6, Reservation.class)) {
					Reservation rBadge = mongoOps.findOne(queryReservation6, Reservation.class);
					rBadge.setEtat("Annulée");
					mongoOps.save(rBadge);
					System.out.println("Annulée");
				}
			}
		}

		return "store";
	}

}
