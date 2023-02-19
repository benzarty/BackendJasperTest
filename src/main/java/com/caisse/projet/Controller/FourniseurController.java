package com.caisse.projet.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caisse.projet.Model.Fournisseur;
import com.caisse.projet.Service.FournisseurService;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api")
public class FourniseurController {
	 @Autowired
	    private FournisseurService fourService;
	
	 
	 @GetMapping("/fournisseurs")
	    public List<Fournisseur> list() {
		 System.out.println("Get all Fournisseurs...");
	             return fourService.getAll();
	   }
	 	 
	 @GetMapping("/fournisseurs/{id}")
	 public ResponseEntity<Fournisseur> post(@PathVariable int id) {
	        Optional<Fournisseur> four = fourService.findByCode(id);
	        return four.map(ResponseEntity::ok)
	                   .orElseGet(() -> ResponseEntity.notFound()
                                               .build());
	    }
	    
	 @PostMapping("/fournisseurs")
	    public long save(@RequestBody Fournisseur Fournisseur) {
		 
	        return fourService.save(Fournisseur);
	    }

	 @PutMapping("/fournisseurs/{code}")
	    public void update(@PathVariable int code, @RequestBody Fournisseur Fournisseur) {
	        Optional<Fournisseur> four = fourService.findByCode(code);
	        if (four.isPresent()) {
	            fourService.update(code, Fournisseur);
	        }
	    }

	    @DeleteMapping("/fournisseurs/{code}")
	    public void delete(@PathVariable int code) {
	        fourService.delete(code);
	    }

}
