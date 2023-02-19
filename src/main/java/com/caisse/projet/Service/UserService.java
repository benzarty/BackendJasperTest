package com.caisse.projet.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.caisse.projet.Model.User;
import com.caisse.projet.Repository.UserRepository;
@Service
@Transactional
public class UserService {
	 @Autowired
		UserRepository repository;
	 
		public List<User> getAll() {
			System.out.println("Get all Users 11111...");
	    	return repository.findAll(Sort.by("username").ascending());	    	
	    }
		
	
		
		
		public List<User> getAllByEmail(String email) {
			System.out.println("Get all Users 11111...");
	    	return repository.findAllByEmail(email);	    	
	    }
		
		
	    public Optional<User> findById(long id) {
	        return repository.findById(id);
	    }
	    
	    public long save(User User) {
	    	System.out.println("save  all Users 11111...");
	       User.setActive(true);
	        return repository.save(User)
	                             .getId();
	    }
	    public void update(long id, User User) {
	        Optional<User> userr = repository.findById(id);
	        if (userr.isPresent()) {
	            User user = userr.get();
	            user.setUsername(User.getUsername());
		        user.setEmail(User.getEmail());
		        user.setPassword(User.getPassword());
		        user.setRole(User.getRole());
		        user.setActive(User.isActive());
	            repository.save(user);
	        }
	    }
	  
	
	    public Optional<User> login(String name) {
	        return repository.findByUsername(name);
	    }

	    public void delete(long id) {
	        Optional<User> user = repository.findById(id);
	        user.ifPresent(repository::delete);
	    }
		

}
