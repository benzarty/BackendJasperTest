package com.caisse.projet.Model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
@Table(name = "client",
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "code"
				+ ""),
		@UniqueConstraint(columnNames = "email") 
	})

public class Client {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private int code;
	  @NotBlank
	  @Size(max = 60)
	  private String libelle;
	  private String adresse;
	  private String tel;
	  @NotBlank
	  @Size(max = 60)
	  @Email
	  private String email;
	  private String fax;
	  private String pwd;
	  private Date cree;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getCree() {
		return cree;
	}
	public void setCree(Date cree) {
		this.cree = cree;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", code=" + code + ", libelle=" + libelle + ", adresse=" + adresse + ", tel=" + tel
				+ ", email=" + email + ", fax=" + fax + ", pwd=" + pwd + ", cree=" + cree + "]";
	}
	public Client(long id, int code, @NotBlank @Size(max = 60) String libelle, String adresse, String tel,
			@NotBlank @Size(max = 60) @Email String email, String fax, String pwd, Date cree) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
		this.fax = fax;
		this.pwd = pwd;
		this.cree = cree;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
