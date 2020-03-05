package com.example.demo;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.client.MongoClients;


public class MongoApp {
	private static final Log log = LogFactory.getLog(MongoApp.class);

	  public static void main(String[] args) throws Exception {

	    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	    
	    CompteEmploye u1 = new CompteEmploye("Gui", "Dragon");
	    mongoOps.insert(u1);
	    mongoOps.save(u1);
	    
	    CompteAdministrateur a1 = new CompteAdministrateur("Admin", "pick");
	    mongoOps.insert(a1);
	    mongoOps.save(a1);

	    log.info(mongoOps.findOne(new Query(where("userName").is("Bonnet")), CompteEmploye.class));
	    log.info("Insert: " + u1);
	    
	    u1 = mongoOps.findById(u1.getId(), CompteEmploye.class);
	    log.info("Found: " + u1);
	    
	    u1 = mongoOps.findOne(query(where("userName").is("Bonnet")), CompteEmploye.class);
	    log.info("Updated: " + u1);
	    
	    //mongoOps.updateFirst(query(where("userName").is("Bonnet")), update("userName", "Patrick"), CompteEmploye.class);
	    
	    u1 = mongoOps.findOne(query(where("userName").is("Bonnet")), CompteEmploye.class);
	    log.info("Updated: " + u1);
	    
	   // u1 = mongoOps.findOne(query(where("userName").is("Patrick")), User.class);
	   // log.info("Updated: " + u1);
	    
	    log.info(mongoOps.findOne(new Query(where("userName").is("Bonnet")), CompteEmploye.class));
	    log.info("Insert: " + a1);
	    
	    a1 = mongoOps.findById(a1.getId(), CompteAdministrateur.class);
	    log.info("Found: " + a1);
	    
	    a1 = mongoOps.findOne(query(where("userName").is("Bonnet")), CompteAdministrateur.class);
	    log.info("Updated: " + a1);
	    
	    //mongoOps.updateFirst(query(where("userName").is("Bonnet")), update("userName", "Patrick"), CompteEmploye.class);
	    
	    a1 = mongoOps.findOne(query(where("userName").is("Bonnet")), CompteAdministrateur.class);
	    log.info("Updated: " + a1);
	    
	   // a1 = mongoOps.findOne(query(where("userName").is("Patrick")), CompteAdministrateur.class);
	   // log.info("Updated: " + u1);
	    
	    List<CompteEmploye> people =  mongoOps.findAll(CompteEmploye.class);
	    System.out.println("Number of people = : " + people.size());
	    
	    List<CompteAdministrateur> peopleA =  mongoOps.findAll(CompteAdministrateur.class);
	    System.out.println("Number of people = : " + peopleA.size());
	    
	    //mongoOps.remove(mongoOps.findOne(query(where("userName").is("Oh")), CompteAdministrateur.class));
	    //mongoOps.remove(mongoOps.findById("5e60d08153f2d77005e81166", CompteAdministrateur.class));
	    
	    people =  mongoOps.findAll(CompteEmploye.class);
	    peopleA =  mongoOps.findAll(CompteAdministrateur.class);
	    System.out.println("Number of people = : " + people.size());
	    System.out.println("Number of people = : " + peopleA.size());
	    
	    
	    Query query = new Query();
	    query.addCriteria(Criteria.where("userName").is("Admin"));
	    query.addCriteria(Criteria.where("password").is("De2lm"));
	    
	    mongoOps.findOne(query, CompteAdministrateur.class);
	    log.info("Updated: " + query);
	  }
}
