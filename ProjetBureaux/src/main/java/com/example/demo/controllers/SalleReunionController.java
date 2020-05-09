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

import com.example.demo.Bureau;
import com.example.demo.CompteAdministrateur;
import com.example.demo.CompteEmploye;
import com.example.demo.MongoApp;
import com.example.demo.SalleDeReunion;
import com.mongodb.client.MongoClients;

@Controller
public class SalleReunionController {

	private static final Log log = LogFactory.getLog(MongoApp.class);

	  public static void main(String[] args) throws Exception {

	    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	    
	    SalleDeReunion s1 = new SalleDeReunion("Site A", "Disponible", "S101", "6");
	    //mongoOps.insert(b1);
	    mongoOps.save(s1);
	    
	    SalleDeReunion s2 = new SalleDeReunion("Site A", "Disponible", "S102", "6");
	    //mongoOps.insert(b2);
	    mongoOps.save(s2);
	    
	    SalleDeReunion s3 = new SalleDeReunion("Site A", "Disponible", "S103", "8");
	    //mongoOps.insert(b3);
	    mongoOps.save(s3);
	    
	    SalleDeReunion t = new SalleDeReunion("Site A", "Disponible", "S10T", "10");
	    //mongoOps.insert(t);
	    mongoOps.save(t);
	    
	    SalleDeReunion s4 = new SalleDeReunion("Site B", "Disponible", "S104", "8");
	    //mongoOps.insert(b4);
	    mongoOps.save(s4);
	    
	    SalleDeReunion s5 = new SalleDeReunion("Site C", "Indisponible", "S105", "12");
	    //mongoOps.insert(b5);
	    mongoOps.save(s5);

	    /*log.info(mongoOps.findOne(new Query(where("userName").is("Bonnet")), CompteEmploye.class));
	    log.info("Insert: " + u1);
	    
	    u1 = mongoOps.findById(u1.getId(), CompteEmploye.class);
	    log.info("Found: " + u1);
	    
	    u1 = mongoOps.findOne(query(where("userName").is("Bonnet")), CompteEmploye.class);
	    log.info("Updated: " + u1);
	    
	    //mongoOps.updateFirst(query(where("userName").is("Bonnet")), update("userName", "Patrick"), CompteEmploye.class);    
	   // a1 = mongoOps.findOne(query(where("userName").is("Patrick")), CompteAdministrateur.class);
	   // log.info("Updated: " + u1);*/
	    
	    List<SalleDeReunion> people =  mongoOps.findAll(SalleDeReunion.class);
	    System.out.println("Number of people = : " + people.size());
	    
	    
	    //mongoOps.remove(mongoOps.findOne(query(where("userName").is("Oh")), CompteAdministrateur.class));
	    //mongoOps.remove(mongoOps.findById("5e60d08153f2d77005e81166", CompteAdministrateur.class));
	    
	    people =  mongoOps.findAll(SalleDeReunion.class);
	    System.out.println("Number of people = : " + people.size());

	    
	    //Query query = new Query();
	    //query.addCriteria(Criteria.where("site").is("Admin"));
	    //query.addCriteria(Criteria.where("etat").is("pick1"));
	    //query.addCriteria(Criteria.where("site").is("Admin"));
	    //query.addCriteria(Criteria.where("etat").is("pick1"));
	    
	    //b2 = mongoOps.findOne(query, Bureau.class);
	    //log.info("Updated: " + b2);
	  }
}
