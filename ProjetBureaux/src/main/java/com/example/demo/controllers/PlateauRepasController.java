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
import com.example.demo.PlateauRepas;
import com.mongodb.client.MongoClients;

@Controller
public class PlateauRepasController {
	private static final Log log = LogFactory.getLog(MongoApp.class);

	  public static void main(String[] args) throws Exception {

	    MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "pgdb");
	    
	    PlateauRepas repas1 = new PlateauRepas("Site A", "Disponible", "Menu 1", "Végétarien", 3);
	    //mongoOps.insert(b1);
	    mongoOps.save(repas1);
	    
	    PlateauRepas repas2 = new PlateauRepas("Site A", "Disponible", "Menu 2", "Carnivore", 1);
	    //mongoOps.insert(b2);
	    mongoOps.save(repas2);
	    
	    PlateauRepas repas3 = new PlateauRepas("Site A", "Disponible", "Menu 3", "Surprise du chef", 0);
	    //mongoOps.insert(b3);
	    mongoOps.save(repas3);

    
	    List<PlateauRepas> people =  mongoOps.findAll(PlateauRepas.class);
	    System.out.println("Number of people = : " + people.size());
	    
	    
	    people =  mongoOps.findAll(PlateauRepas.class);
	    System.out.println("Number of people = : " + people.size());
	    

	  }
}
