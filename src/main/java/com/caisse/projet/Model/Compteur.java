package com.caisse.projet.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "compteur")
public class Compteur {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	private int annee;
	private int numpanier;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getNumpanier() {
		return numpanier;
	}
	public void setNumpanier(int numpanier) {
		this.numpanier = numpanier;
	}
	@Override
	public String toString() {
		return "Compteur [id=" + id + ", annee=" + annee + ", numpanier=" + numpanier + "]";
	}
	public Compteur(long id, int annee, int numpanier) {
		super();
		this.id = id;
		this.annee = annee;
		this.numpanier = numpanier;
	}
	public Compteur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
