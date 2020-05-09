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
import com.mongodb.client.MongoClients;

@Controller
public class BureauController {
	private static final Log log = LogFactory.getLog(MongoApp.class);

	  public static void main(String[] args) throws Exception {

	    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	    
	    Bureau b1 = new Bureau("Site A", "Disponible", "A101", "1");
	    //mongoOps.insert(b1);
	    mongoOps.save(b1);
	    
	    Bureau b2 = new Bureau("Site A", "Disponible", "A102", "1");
	    //mongoOps.insert(b2);
	    mongoOps.save(b2);
	    
	    Bureau b3 = new Bureau("Site A", "Disponible", "A103", "2");
	    //mongoOps.insert(b3);
	    mongoOps.save(b3);
	    
	    Bureau t = new Bureau("Site A", "Disponible", "A10T", "2");
	    //mongoOps.insert(t);
	    mongoOps.save(t);
	    
	    Bureau b4 = new Bureau("Site B", "Disponible", "A104", "3");
	    //mongoOps.insert(b4);
	    mongoOps.save(b4);
	    
	    Bureau b5 = new Bureau("Site C", "Indisponible", "A105", "1");
	    //mongoOps.insert(b5);
	    mongoOps.save(b5);

	    /*log.info(mongoOps.findOne(new Query(where("userName").is("Bonnet")), CompteEmploye.class));
	    log.info("Insert: " + u1);
	    
	    u1 = mongoOps.findById(u1.getId(), CompteEmploye.class);
	    log.info("Found: " + u1);
	    
	    u1 = mongoOps.findOne(query(where("userName").is("Bonnet")), CompteEmploye.class);
	    log.info("Updated: " + u1);
	    
	    //mongoOps.updateFirst(query(where("userName").is("Bonnet")), update("userName", "Patrick"), CompteEmploye.class);    
	   // a1 = mongoOps.findOne(query(where("userName").is("Patrick")), CompteAdministrateur.class);
	   // log.info("Updated: " + u1);*/
	    
	    List<Bureau> people =  mongoOps.findAll(Bureau.class);
	    System.out.println("Number of people = : " + people.size());
	    
	    
	    //mongoOps.remove(mongoOps.findOne(query(where("userName").is("Oh")), CompteAdministrateur.class));
	    //mongoOps.remove(mongoOps.findById("5e60d08153f2d77005e81166", CompteAdministrateur.class));
	    
	    people =  mongoOps.findAll(Bureau.class);
	    System.out.println("Number of people = : " + people.size());

	    
	    Query query = new Query();
	    query.addCriteria(Criteria.where("site").is("Admin"));
	    query.addCriteria(Criteria.where("etat").is("pick1"));
	    //query.addCriteria(Criteria.where("site").is("Admin"));
	    //query.addCriteria(Criteria.where("etat").is("pick1"));
	    
	    b2 = mongoOps.findOne(query, Bureau.class);
	    log.info("Updated: " + b2);
	  }
}
