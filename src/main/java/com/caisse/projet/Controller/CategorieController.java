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

import com.caisse.projet.Model.Categorie;

import com.caisse.projet.Service.CategorieService;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api")
public class CategorieController {
	 @Autowired
	    private CategorieService catService;
   	 @GetMapping("/categories/7")
	 public  int getCode() {
		 System.out.println("Get Numbers...");
		 int  x = catService.nbre();
		 System.out.println(x);
		 if (x == 0)
		 {
			 return 0;
		 }
		 else
		 {
			 return catService.max();
		 }
	    
	 }
	 
	 @GetMapping("/categories")
	    public List<Categorie> list() {
		 System.out.println("Get all Categories...");
	             return catService.getAll();
	   }
	 	 
	 @GetMapping("/categories/{id}")
	 public ResponseEntity<Categorie> post(@PathVariable String id) {
	        Optional<Categorie> cat = catService.findByCode(id);
	        return cat.map(ResponseEntity::ok)
	                   .orElseGet(() -> ResponseEntity.notFound()
                                                  .build());
	    }
	    
	 @PostMapping("/categories")
	    public long save(@RequestBody Categorie categorie) {
		 
	        return catService.save(categorie);
	    }

	 @PutMapping("/categories/{code}")
	    public void update(@PathVariable String code, @RequestBody Categorie categorie) {
	        Optional<Categorie> cat = catService.findByCode(code);
	        if (cat.isPresent()) {
	            catService.update(code, categorie);
	        } else {
	            catService.save(categorie);
	        }
	    }

	    @DeleteMapping("/categories/{code}")
	    public void delete(@PathVariable String code) {
	        catService.delete(code);
	    }
	     
	  
}

