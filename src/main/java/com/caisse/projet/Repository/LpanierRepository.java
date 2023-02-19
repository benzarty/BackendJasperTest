package com.caisse.projet.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.caisse.projet.Model.Lpanier;
@Repository
public interface LpanierRepository extends JpaRepository<Lpanier, Long>{

}
