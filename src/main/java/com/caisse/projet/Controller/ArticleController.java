package com.caisse.projet.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

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

import com.caisse.projet.Dto.ListArticle;
import com.caisse.projet.Dto.ListCategorie;
import com.caisse.projet.Model.Article;
import com.caisse.projet.Model.ArticleExcel;
import com.caisse.projet.Model.ScategorieExcel;
import com.caisse.projet.Service.ArticleService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ArticleController {
	@Autowired
    private ArticleService artService;
	@Autowired  ServletContext context;
	
	
	 @GetMapping("/articles/7/{code}")
	 public  int getCode(@PathVariable String code) {
		 System.out.println("Get Numbers...");
		 int  x = artService.nbre(code);
		 System.out.println(x);
		 if (x == 0)
		 {
			 return 0;
		 }
		 else
		 {
			 return artService.max(code);
		 }
	 }
	 
	
	 @GetMapping("/articles")
	 public List<ListArticle> list() {
		 System.out.println("Get all Articles...");
	             return artService.getAll();
	   }
	   

	 @GetMapping("/articles/f/{code}")
	 public List<ListArticle> listArtf(@PathVariable int code) {
		 System.out.println("Get all Articles...");
	             return artService.listArtf(code);
	   }	 
	
 @GetMapping("/articles/{id}")
 public ResponseEntity<Article> post(@PathVariable String id) {
        Optional<Article> cat = artService.findByCode(id);
        return cat.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound()
                                              .build());
    }
 
 
 @PostMapping("/articles")
 public long createArticle (@RequestParam("file") MultipartFile file,
		 @RequestParam("article") String article) throws JsonParseException , JsonMappingException , Exception
 {
	 System.out.println("Save Article.............");
    Article arti = new ObjectMapper().readValue(article, Article.class);
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
    arti.setFileName(newFileName);
    return artService.save(arti);
 }
 
 
  @PutMapping("/articles/{code}")
    public void update(@PathVariable String code, @RequestBody Article Article) {
        Optional<Article> cat = artService.findByCode(code);
        if (cat.isPresent()) {
            artService.update(code, Article);
    
        }
    }

    @DeleteMapping("/articles/{code}")
    public void delete(@PathVariable String code) {
        artService.delete(code);
    }
     
    @GetMapping(path="/Imgarticles/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		 Article Article   =artService.findById(id).get();
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/")+Article.getFileName()));
	 }


    @GetMapping("/articles/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
    	System.out.println("Export to Excel ...");
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=articles_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<ListArticle> listArticles = artService.getAll();
        ArticleExcel excel = new ArticleExcel(listArticles);
        excel.export(response);    
    }  
    
}
