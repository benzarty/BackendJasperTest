package com.caisse.projet.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.caisse.projet.Model.Categorie;
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

	Optional<Categorie> findByCode(String code);

	List<Categorie> findAllByLibelleContaining(String libelle);
	@Query(value = "SELECT count(code) FROM Categorie")
	public int nbre();

	@Query(value = "SELECT max(code) FROM Categorie")
	public int max();
	

} 
