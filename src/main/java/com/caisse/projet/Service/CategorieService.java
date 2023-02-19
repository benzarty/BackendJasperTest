package com.caisse.projet.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caisse.projet.Repository.CategorieRepository;
import com.caisse.projet.Model.Categorie;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
@Service
@Transactional
public class CategorieService {
	 @Autowired
		CategorieRepository repository;
	 
		public List<Categorie> getAll() {
			System.out.println("Get all Categories 11111...");
	    	return repository.findAll(Sort.by("libelle").ascending());	    	
	    }
		
		public int max() {
			return repository.max();
		}
		
		public int nbre() {
			return repository.nbre();
		}
		
	    public Optional<Categorie> findByCode(String code) {
	        return repository.findByCode(code);
	    }
	    
	    public long save(Categorie categorie) {
	    	System.out.println("save  all Categories 11111...");
	        Categorie cat = new Categorie();
	        cat.setCode(categorie.getCode());
	        cat.setLibelle(categorie.getLibelle());
	        cat.setLibelle(categorie.getLibelle());
	        return repository.save(cat)
	                             .getId();
	    }
	    public void update(String code, Categorie categorie) {
	        Optional<Categorie> categ = repository.findByCode(code);
	        if (categ.isPresent()) {
	            Categorie cat = categ.get();
	            cat.setLibelle(categorie.getLibelle());
	            repository.save(cat);
	        }
	    }
	  
	
	    public List<Categorie> findByLibelle(String libelle) {
	        return repository.findAllByLibelleContaining(libelle);
	    }

	    public void delete(String code) {
	        Optional<Categorie> cat = repository.findByCode(code);
	        cat.ifPresent(repository::delete);
	    }
		
}
