package com.caisse.projet.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.caisse.projet.Model.Lpanier;
@Entity
@Table(name = "panier")
public class Panier {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int annee;
	  private int numero;
	  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	  private Date date_mvt;
	  private String nom;
	  private String adresse;
	  private String sadresse;
	  private String ville;
	  private String codep;
	  private String tel;
	  private String tel1;
	  private double totht;
	  private double tottva;
	  private double totttc;
	  private String modreg;
	  private String numcarte;
	  @JsonManagedReference
	  @JsonIgnore
	  @OneToMany(mappedBy = "panier", fetch=FetchType.EAGER)
    @Valid
	  private List<Lpanier> lpaniers = new ArrayList<>();
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
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getDate_mvt() {
		return date_mvt;
	}
	public void setDate_mvt(Date date_mvt) {
		this.date_mvt = date_mvt;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getSadresse() {
		return sadresse;
	}
	public void setSadresse(String sadresse) {
		this.sadresse = sadresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodep() {
		return codep;
	}
	public void setCodep(String codep) {
		this.codep = codep;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public double getTotht() {
		return totht;
	}
	public void setTotht(double totht) {
		this.totht = totht;
	}
	public double getTottva() {
		return tottva;
	}
	public void setTottva(double tottva) {
		this.tottva = tottva;
	}
	public double getTotttc() {
		return totttc;
	}
	public void setTotttc(double totttc) {
		this.totttc = totttc;
	}
	public String getModreg() {
		return modreg;
	}
	public void setModreg(String modreg) {
		this.modreg = modreg;
	}
	public String getNumcarte() {
		return numcarte;
	}
	public void setNumcarte(String numcarte) {
		this.numcarte = numcarte;
	}
	public List<Lpanier> getLpaniers() {
		return lpaniers;
	}
	public void setLpaniers(List<Lpanier> lpaniers) {
		this.lpaniers = lpaniers;
	}
	@Override
	public String toString() {
		return "Panier [id=" + id + ", annee=" + annee + ", numero=" + numero + ", date_mvt=" + date_mvt + ", nom="
				+ nom + ", adresse=" + adresse + ", sadresse=" + sadresse + ", ville=" + ville + ", codep=" + codep
				+ ", tel=" + tel + ", tel1=" + tel1 + ", totht=" + totht + ", tottva=" + tottva + ", totttc=" + totttc
				+ ", modreg=" + modreg + ", numcarte=" + numcarte + ", lpaniers=" + lpaniers + "]";
	}
	public Panier(long id, int annee, int numero, Date date_mvt, String nom, String adresse, String sadresse,
			String ville, String codep, String tel, String tel1, double totht, double tottva, double totttc,
			String modreg, String numcarte, @Valid List<com.caisse.projet.Model.Lpanier> lpaniers) {
		super();
		this.id = id;
		this.annee = annee;
		this.numero = numero;
		this.date_mvt = date_mvt;
		this.nom = nom;
		this.adresse = adresse;
		this.sadresse = sadresse;
		this.ville = ville;
		this.codep = codep;
		this.tel = tel;
		this.tel1 = tel1;
		this.totht = totht;
		this.tottva = tottva;
		this.totttc = totttc;
		this.modreg = modreg;
		this.numcarte = numcarte;
		this.lpaniers = lpaniers;
	}
	public Panier() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
}
