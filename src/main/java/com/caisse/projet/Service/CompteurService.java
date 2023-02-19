package com.caisse.projet.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caisse.projet.Model.Compteur;
import com.caisse.projet.Repository.CompteurRepository;
@Service
@Transactional
public class CompteurService {
	@Autowired
	CompteurRepository repository;
	public CompteurService() {
		// TODO Auto-generated constructor stub
	}

	public int nbre(int annee) {
		return repository.nbre(annee);
	}

	public void create(int annee) {
		 Compteur cpt = new Compteur();
	        cpt.setAnnee(annee);
	        cpt.setNumpanier(1);
	        repository.save(cpt);
		
	}

	public Optional<Compteur> findByAnnee(int annee) {
		// TODO Auto-generated method stub
		return repository.findByAnnee(annee);
	}

}
