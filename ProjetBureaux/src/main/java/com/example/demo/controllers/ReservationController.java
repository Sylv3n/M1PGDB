package com.example.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;

import com.example.demo.Bureau;
import com.example.demo.Compte;
import com.example.demo.MongoApp;
import com.example.demo.Reservation;
import com.mongodb.client.MongoClients;

@Controller
public class ReservationController {
	private static final Log log = LogFactory.getLog(MongoApp.class);

	
	 public static void main(String[] args) throws Exception {
		 MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
		 
			Bureau a101= new Bureau("Site A", "Disponible", "A101", "1");
			Bureau a102= new Bureau("Site A", "Disponible", "A102", "1");
			Bureau a10T= new Bureau("Site A", "Indisponible", "A10T", "2");
		   
		    
		    Date date = null;
		    Date dateA = null;
		    Date dateB = null;
		    Date dateC= null;
		    Date dateD = null;
		    Date dateE = null;
		    Date dateF = null;
		    Date dateG= null;
		    
		    
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		    //simpleDateFormat.setTimeZone (TimeZone.getTimeZone ("UTC+02:00"));
		    
		    SimpleDateFormat simpleDateFormatt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

		    String date1 = "2020-04-03T17:00";
		    String date2 = "2020-04-03T18:10";
		    String date3 = "2020-04-04T06:00";
		    String date4 = "2020-04-05T19:00";
		    String date5 = "2020-05-01T08:00";
		    String date6 = "2020-05-01T16:00";
		    String date7 = "2020-05-03T07:00";
		    String date8 = "2020-05-05T18:00";
		    

		    try {

				 
				 
				Date dateDebut = simpleDateFormat.parse(date1);
				String dat = simpleDateFormatt.format(dateDebut);
			    date = simpleDateFormatt.parse(dat);
			    System.out.println("ohé "+date);
			    
			    /*dateA = simpleDateFormat.parse(date2);
			    System.out.println(dateA);
			    dateB = simpleDateFormat.parse(date3);
			    System.out.println(dateB);
			    dateC = simpleDateFormat.parse(date4);
			    System.out.println(dateC);
			    dateD = simpleDateFormat.parse(date5);
			    System.out.println(dateD);
			    dateE = simpleDateFormat.parse(date6);
			    System.out.println(dateE);
			    dateF = simpleDateFormat.parse(date7);
			    System.out.println(dateF);
			    dateG = simpleDateFormat.parse(date8);
			    System.out.println(dateG);*/
			    
			    Date dateDebut1 = simpleDateFormat.parse(date2);
				String dat1 = simpleDateFormatt.format(dateDebut1);
			    dateA = simpleDateFormatt.parse(dat1);
			    System.out.println(dateA);
			    
			    Date dateDebut2 = simpleDateFormat.parse(date3);
				String dat2 = simpleDateFormatt.format(dateDebut2);
			    dateB = simpleDateFormatt.parse(dat2);
			    System.out.println(dateB);
			    
			    Date dateDebut3 = simpleDateFormat.parse(date4);
				String dat3 = simpleDateFormatt.format(dateDebut3);
			    dateC = simpleDateFormatt.parse(dat3);
			    System.out.println(dateC);
			    
			    Date dateDebut4 = simpleDateFormat.parse(date5);
				String dat4 = simpleDateFormatt.format(dateDebut4);
			    dateD = simpleDateFormatt.parse(dat4);
			    System.out.println(dateD);
			    
			    Date dateDebut5 = simpleDateFormat.parse(date6);
				String dat5 = simpleDateFormatt.format(dateDebut5);
			    dateE = simpleDateFormatt.parse(dat5);
			    System.out.println(dateE);
			    
			    Date dateDebut6 = simpleDateFormat.parse(date7);
				String dat6 = simpleDateFormatt.format(dateDebut6);
			    dateF = simpleDateFormatt.parse(dat6);
			    System.out.println(dateF);
			    
			    Date dateDebut7 = simpleDateFormat.parse(date8);
				String dat7 = simpleDateFormatt.format(dateDebut7);
			    dateG = simpleDateFormatt.parse(dat7);
			    System.out.println(dateG);
			    
		    } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    
		    
		    Reservation r1 = new Reservation(a101, "Test", date, dateA, "Effectuée");
		    //mongoOps.insert(b1);
		    mongoOps.save(r1);
		    
		    Reservation r2 = new Reservation(a101, "Test", dateB, dateC, "Effectuée");
		    //mongoOps.insert(b2);
		    mongoOps.save(r2);
		    
		    Reservation r3 = new Reservation(a102, "Test", dateD, dateE, "Effectuée");
		    //mongoOps.insert(b3);
		    mongoOps.save(r3);
		    
		    Reservation r4 = new Reservation(a10T, "Test", dateF, dateG, "En attente");
		    //mongoOps.insert(t);
		    mongoOps.save(r4);
		    

		    /*log.info(mongoOps.findOne(new Query(where("userName").is("Bonnet")), CompteEmploye.class));
		    log.info("Insert: " + u1);
		    
		    u1 = mongoOps.findById(u1.getId(), CompteEmploye.class);
		    log.info("Found: " + u1);
		    
		    u1 = mongoOps.findOne(query(where("userName").is("Bonnet")), CompteEmploye.class);
		    log.info("Updated: " + u1);
		    
		    //mongoOps.updateFirst(query(where("userName").is("Bonnet")), update("userName", "Patrick"), CompteEmploye.class);    
		   // a1 = mongoOps.findOne(query(where("userName").is("Patrick")), CompteAdministrateur.class);
		   // log.info("Updated: " + u1);*/
		    
		   /* List<Reservation> people =  mongoOps.findAll(Reservation.class);
		    System.out.println("Number of people = : " + people.size());
		    
		    
		    //mongoOps.remove(mongoOps.findOne(query(where("userName").is("Oh")), CompteAdministrateur.class));
		    //mongoOps.remove(mongoOps.findById("5e60d08153f2d77005e81166", CompteAdministrateur.class));
		    
		    people =  mongoOps.findAll(Reservation.class);
		    System.out.println("Number of people = : " + people.size());

		    
		    Query query = new Query();
		    Bureau b = mongoOps.findOne(query, Bureau.class);
		    System.out.println(b.getSite()+"\n"+b.getIdService()+"\n"+b.getNom()+"\n"+b.getCapacite());
		    query.addCriteria(Criteria.where("service").is(b.getNom()));
		    
		    //query.addCriteria(Criteria.where("site").is("Admin"));
		    //query.addCriteria(Criteria.where("etat").is("pick1"));;
		   /* Reservation r = mongoOps.findOne(query, Reservation.class);
		    System.out.println(r.getDateDebut());*/
		    
		    
		    
		    
		    //r1 = mongoOps.findOne(query, Reservation.class);
		    //log.info(r1);*/
		  }
}
