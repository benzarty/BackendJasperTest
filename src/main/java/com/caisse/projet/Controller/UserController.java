package com.caisse.projet.Controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.caisse.projet.Model.Article;
import com.caisse.projet.Model.User;
import com.caisse.projet.Service.UserService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
	 @Autowired
	    private UserService userService;
	 @Autowired  ServletContext context;
	 
	 @GetMapping("/users")
	    public List<User> list() {
		 System.out.println("Get all Users...");
	             return userService.getAll();
	   }
	 	 
	 @GetMapping("/users/{id}")
	 public ResponseEntity<User> post(@PathVariable long id) {
	        Optional<User> user = userService.findById(id);
	        return user.map(ResponseEntity::ok)
	                   .orElseGet(() -> ResponseEntity.notFound()
                                               .build());
	    }
	    
	 @GetMapping("/users/verif/{email}")
	    public List<User> listUser(@PathVariable String email) {
		 System.out.println("Get all Users...");
	             return userService.getAllByEmail(email);
	   }
	 
	 @GetMapping("/users/auth/{name}")
	 public ResponseEntity<User> login(@PathVariable String name) {
	        Optional<User> user = userService.login(name);
	        return user.map(ResponseEntity::ok)
	                   .orElseGet(() -> ResponseEntity.notFound()
                                               .build());
	    }
	    
	 @PostMapping("/users")
	 public long createUser (@RequestParam("file") MultipartFile file,
			 @RequestParam("user") String user) throws JsonParseException , JsonMappingException , Exception
	 {
		 System.out.println("Save Article.............");
	    User userr = new ObjectMapper().readValue(user, User.class);
	    boolean isExit = new File(context.getRealPath("/Imagess/")).exists();
	    if (!isExit)
	    {
	    	new File (context.getRealPath("/Imagess/")).mkdir();
	    	System.out.println("mk dir Imagess.............");
	    }
	    System.out.println("Save Article  22222.............");
	    String filename = file.getOriginalFilename();
	    String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
	    File serverFile = new File (context.getRealPath("/Imagess/"+File.separator+newFileName));
	    try
	    {
	    	System.out.println("Image");
	    	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
	    	 
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    System.out.println("Save Article 333333.............");
	    userr.setFileName(newFileName);
	    return userService.save(userr);
	 }
	 
	    public long save(@RequestBody User User) {
		 System.out.println("Save all Users...");
	        return userService.save(User);
	    }

	 @PutMapping("/users/{id}")
	    public void update(@PathVariable long id, @RequestBody User User) {
	        Optional<User> user = userService.findById(id);
	        if (user.isPresent()) {
	            userService.update(id, User);
	        } else {
	            userService.save(User);
	        }
	    }

	    @DeleteMapping("/users/{id}")
	    public void delete(@PathVariable long id) {
	        userService.delete(id);
	    }
	     
	    @GetMapping(path="/Imgusers/{id}")
		 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
			 User User   =userService.findById(id).get();
			 return Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/")+User.getFileName()));
		 }
}
