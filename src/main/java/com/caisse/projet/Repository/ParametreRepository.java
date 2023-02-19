package com.caisse.projet.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caisse.projet.Model.Parametre;
@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long>{


}
