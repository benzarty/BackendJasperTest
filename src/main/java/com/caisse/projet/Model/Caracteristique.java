package com.caisse.projet.Model;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "caracteristique")
public class Caracteristique {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private String code;
	  private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Caracteristique [id=" + id + ", code=" + code + ", description=" + description + "]";
	}
	public Caracteristique(long id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}
	public Caracteristique() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
