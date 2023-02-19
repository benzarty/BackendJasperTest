package com.caisse.projet.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.caisse.projet.Model.Client;
import com.caisse.projet.Model.Fournisseur;
import com.caisse.projet.Model.Parametre;
import com.caisse.projet.Model.User;
import com.caisse.projet.Repository.FournisseurRepository;
import com.caisse.projet.Repository.ParametreRepository;
import com.caisse.projet.Repository.UserRepository;
@Service
@Transactional
public class FournisseurService {
	@Autowired
	FournisseurRepository repository;
	@Autowired 	ParametreRepository paramRepository;
	@Autowired 	UserRepository userRepository;
	public List<Fournisseur> getAll() {
		System.out.println("Get all Fournisseurs 11111...");
    	return repository.findAll(Sort.by("libelle").ascending());	    	
    }
	
	
    public Optional<Fournisseur> findByCode(int id) {
        return repository.findByCode(id);
    }
    
    public long save(Fournisseur Four) {
    	System.out.println("save  all Fournisseurs 11111...");
    	long id = 1;
		 Optional<Parametre> ParamInfo = paramRepository.findById(id);
	 	    if (ParamInfo.isPresent()) {
		    	Parametre param = ParamInfo.get();
		           param.setNumf(param.getNumf()+1);
		           param = paramRepository.save(param);
		    }
	 	   User user = new User();
	        user.setUsername(Four.getEmail());
	        user.setNom(Four.getLibelle());
	        user.setCode(Four.getCode());
	        user.setEmail(Four.getEmail());
	        user.setPassword(Four.getPwd());
	        user.setRole("FOUR");
	        user.setActive(true);
	        userRepository.save(user);
        return repository.save(Four)
                             .getId();
    }
    
    
    public void update(int code, Fournisseur Fournisseur) {
        Optional<Fournisseur> four = repository.findByCode(code);
        if (four.isPresent()) {
            Fournisseur fr = four.get();
            fr.setLibelle(Fournisseur.getLibelle());
            fr.setAdresse(Fournisseur.getAdresse());
            repository.save(fr);
        }
    }
    
    
    public List<Fournisseur> findByEmail(String email) {
        return repository.findAllByEmail(email);
    }
  

    public List<Fournisseur> findByLibelle(String libelle) {
        return repository.findAllByLibelleContaining(libelle);
    }

    public void delete(int code) {
        Optional<Fournisseur> cat = repository.findByCode(code);
        cat.ifPresent(repository::delete);
    }
	

}
