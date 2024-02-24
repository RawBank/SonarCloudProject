package com.rawbank.admin.oracle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CSC_ADMIN_BULK_RECHARGE")
public class RawBulkRecharge {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NUM_CLIENT", nullable = true, updatable = true, insertable = true, length = 200)
	private String numClient;
	
	
	@Column(name = "MONTANT", nullable = true, updatable = true, insertable = true, length = 200)
	private String montant;
	
	
	@Column(name = "COMPTE_SRC", nullable = true, updatable = true, insertable = true, length = 200)
	private String comptesrc;
	
	@Column(name = "NOMFICHIER", nullable = true, updatable = true, insertable = true, length = 200)
	private String nomFichier;
	
	@Column(name = "INITIATEUR", nullable = true, updatable = true, insertable = true, length = 200)
	private String initiateur;
	
	@Column(name = "CURRENCY", nullable = true, updatable = true, insertable = true, length = 200)
	private String currency;
	
	@Column(name = "COMMENTS", nullable = true, updatable = true, insertable = true, length = 200)
	private String comments;
	
	@Column(name = "CARD_NUMBER", nullable = true, updatable = true, insertable = true, length = 200)
	private String cardNumber;
	
	
	
	public RawBulkRecharge() {

	  }



	public RawBulkRecharge(Long id, String numClient, String montant, String comptesrc, String nomFichier,
			String initiateur, String currency, String comments, String cardNumber) {
		super();
		this.id = id;
		this.numClient = numClient;
		this.montant = montant;
		this.comptesrc = comptesrc;
		this.nomFichier = nomFichier;
		this.initiateur = initiateur;
		this.currency = currency;
		this.comments = comments;
		this.cardNumber = cardNumber;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNumClient() {
		return numClient;
	}



	public void setNumClient(String numClient) {
		this.numClient = numClient;
	}



	public String getMontant() {
		return montant;
	}



	public void setMontant(String montant) {
		this.montant = montant;
	}



	public String getComptesrc() {
		return comptesrc;
	}



	public void setComptesrc(String comptesrc) {
		this.comptesrc = comptesrc;
	}



	public String getNomFichier() {
		return nomFichier;
	}



	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}



	public String getInitiateur() {
		return initiateur;
	}



	public void setInitiateur(String initiateur) {
		this.initiateur = initiateur;
	}



	public String getCurrency() {
		return currency;
	}



	public void setCurrency(String currency) {
		this.currency = currency;
	}



	public String getComments() {
		return comments;
	}



	public void setComments(String comments) {
		this.comments = comments;
	}



	public String getCardNumber() {
		return cardNumber;
	}



	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}



	@Override
	public String toString() {
		return "RawBulkRecharge [id=" + id + ", numClient=" + numClient + ", montant=" + montant + ", comptesrc="
				+ comptesrc + ", nomFichier=" + nomFichier + ", initiateur=" + initiateur + ", currency=" + currency
				+ ", comments=" + comments + ", cardNumber=" + cardNumber + "]";
	}


	

}
