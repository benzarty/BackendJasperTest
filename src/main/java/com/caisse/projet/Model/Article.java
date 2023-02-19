package com.caisse.projet.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "article",
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "code"
				+ ""),
		@UniqueConstraint(columnNames = "libelle") 
	})
public class Article {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private String code;
  	  private String libelle;
	  private double pa;
	  private double pv;
	  private int tva;
	  private int stock;
	  private String ccateg;
	  private String cscateg;
	  private String fileName;
	  private int codef;
	  @JsonManagedReference
	  @JsonIgnore
	  @OneToMany(mappedBy = "article", fetch=FetchType.EAGER)
    @Valid
	  private List<Carticle> carticle =  new ArrayList<>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getPa() {
		return pa;
	}
	public void setPa(double pa) {
		this.pa = pa;
	}
	public double getPv() {
		return pv;
	}
	public void setPv(double pv) {
		this.pv = pv;
	}
	public int getTva() {
		return tva;
	}
	public void setTva(int tva) {
		this.tva = tva;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCcateg() {
		return ccateg;
	}
	public void setCcateg(String ccateg) {
		this.ccateg = ccateg;
	}
	public String getCscateg() {
		return cscateg;
	}
	public void setCscateg(String cscateg) {
		this.cscateg = cscateg;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getCodef() {
		return codef;
	}
	public void setCodef(int codef) {
		this.codef = codef;
	}
	public List<Carticle> getCarticle() {
		return carticle;
	}
	public void setCarticle(List<Carticle> carticle) {
		this.carticle = carticle;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", code=" + code + ", libelle=" + libelle + ", pa=" + pa + ", pv=" + pv + ", tva="
				+ tva + ", stock=" + stock + ", ccateg=" + ccateg + ", cscateg=" + cscateg + ", fileName=" + fileName
				+ ", codef=" + codef + ", carticle=" + carticle + "]";
	}
	public Article(long id, String code, String libelle, double pa, double pv, int tva, int stock, String ccateg,
			String cscateg, String fileName, int codef, @Valid List<Carticle> carticle) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.pa = pa;
		this.pv = pv;
		this.tva = tva;
		this.stock = stock;
		this.ccateg = ccateg;
		this.cscateg = cscateg;
		this.fileName = fileName;
		this.codef = codef;
		this.carticle = carticle;
	}
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
}
