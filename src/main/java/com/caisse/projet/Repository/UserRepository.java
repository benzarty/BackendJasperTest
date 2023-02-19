package com.caisse.projet.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.caisse.projet.Model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String name);

	List<User> findAllByEmail(String email);


}
