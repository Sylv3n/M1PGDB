package com.example.demo;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.client.MongoClients;


public class MongoApp {
	private static final Log log = LogFactory.getLog(MongoApp.class);

	  public static void main(String[] args) throws Exception {

	    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	    
	    User u1 = new User("Dupuis", "Dragon");
	    mongoOps.insert(u1);
	    mongoOps.save(u1);

	    log.info(mongoOps.findOne(new Query(where("userName").is("Bonnet")), User.class));
	    log.info("Insert: " + u1);
	    
	    u1 = mongoOps.findById(u1.getId(), User.class);
	    log.info("Found: " + u1);
	    
	    u1 = mongoOps.findOne(query(where("userName").is("Bonnet")), User.class);
	    log.info("Updated: " + u1);
	    
	    mongoOps.updateFirst(query(where("userName").is("Bonnet")), update("userName", "Patrick"), User.class);
	    
	    u1 = mongoOps.findOne(query(where("userName").is("Bonnet")), User.class);
	    log.info("Updated: " + u1);
	    
	   // u1 = mongoOps.findOne(query(where("userName").is("Patrick")), User.class);
	    log.info("Updated: " + u1);
	    
	    List<User> people =  mongoOps.findAll(User.class);
	    System.out.println("Number of people = : " + people.size());
	    
	    mongoOps.remove(mongoOps.findById("5e5ea25566f21b6a6c1eae83", User.class));

	    people =  mongoOps.findAll(User.class);
	    System.out.println("Number of people = : " + people.size());
	  }
}
