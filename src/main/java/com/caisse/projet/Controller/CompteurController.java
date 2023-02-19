package com.caisse.projet.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.caisse.projet.Model.Compteur;
import com.caisse.projet.Service.CompteurService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api")
public class CompteurController {
	 @Autowired
	    private CompteurService compteurService;
	 @GetMapping("/compteurs/{annee}")
	 public  ResponseEntity<Compteur> nbre(@PathVariable int annee) {
		 System.out.println("Get Numbers...");
		 
		 int x = compteurService.nbre(annee);
		
		 if (x == 0)
		 {
			 compteurService.create(annee);
		 }
		 Optional<Compteur> cpt = compteurService.findByAnnee(annee);
	        return cpt.map(ResponseEntity::ok)
	                   .orElseGet(() -> ResponseEntity.notFound()
                                         .build());
		
	 }
	 
	
	 

}
