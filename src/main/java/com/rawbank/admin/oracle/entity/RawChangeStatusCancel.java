package com.rawbank.admin.oracle.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CSC_ADMIN_CHANGE_STATUS_CANCEL_CM")
public class RawChangeStatusCancel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	
	@Column(name = "CARD_NUMBER")
	private String cardNumber;

	@Column(name = "CARD_NEW_STATUS")
	private String cardnewStatus;

	@Column(name = "INSTITUTION_NUMBER")
	private String instiutionNumber;

	@Column(name = "INITIATOR")
	private String initiator;

	@Column(name = "DATE_INITIATOR")
	private String dateInitiator;

	@Column(name = "VALIDATOR")
	private String validator;

	@Column(name = "DATE_VALIDATION")
	private String dateValidation;
	
	@Column(name = "STATUS_OP_CSC")
	private String statusOpCsc;

	@Column(name = "COMPTE_CREDIT_A")  
	private String compteCreditA;
	
	@Column(name = "COMPTE_DEBIT_A")
	private String compteDebitA;
	
	@Column(name = "MOTIF_ANNULATION")
	private String motifAnnulation;

	@Column(name = "BANK_REF_CODE_A")  
	private String txnReferenceCodeBankA;
	
	@Column(name = "BANK_REF_CODE_B")  
	private String txnReferenceCodeBankB;
	
	@Column(name = "STATUS_OP_AMPLITUDE_A")
	private String statusOpAmplitudeA;
	
	@Column(name = "STATUT_OP_AMPLITUDE_B")
	private String statusOpAmplitudeB;
	
	@Column(name = "MONTANT_TXN_A")
	private double txnAmountA;

	@Column(name = "DEVISE_TXN")
	private String txnDevise;
	
	@Column(name = "CUST_NAME")
	private String customerName;

	@Column(name = "CANCEL_COMMENT")  
	private String cancelComment;
	
	@Column(name = "CANCEL_EVENT_STATUS")
	private String cancelEventStatus;
	
	@Column(name = "NUMERO_CLIENT")
	private String customerNumber;
	
	@Column(name = "REQ_CANCEL_ID")
	private String reqCancelId;
	
	@Column(name = "CARD_OLD_STATUS")
	private String cardOldStatus;
	
	@Column(name = "COMPTE_DEBIT_B")
	private String compteDebitB;
	
	@Column(name = "COMPTE_CREDIT_B")
	private String compteCreditB;
	
	@Column(name = "TYPE_ANNULATION_CARTE")
	private String typeAnnulationCarte;
	
	@Column(name = "MONTANT_TXN_B")
	private double txnAmountB;
	
	@Column(name = "AGE_CMPT_CREDIT_A")
	private String agenceCompteCreditA;
	
	@Column(name = "AGE_CMPT_DEBIT_A")
	private String agenceCompteDebitA;
	
	@Column(name = "AGE_CMPT_CREDIT_B")
	private String agenceCompteCreditB;
	
	@Column(name = "AGE_CMPT_DEBIT_B")
	private String agenceCompteDebitB;
	
	
	public RawChangeStatusCancel() {
		super();
	}


	public RawChangeStatusCancel(Long id, String cardNumber, String cardnewStatus, String instiutionNumber,
			String initiator, String dateInitiator, String validator, String dateValidation, String statusOpCsc,
			String compteCreditA, String compteDebitA, String motifAnnulation, String txnReferenceCodeBankA,
			String txnReferenceCodeBankB, String statusOpAmplitudeA, String statusOpAmplitudeB, double txnAmountA,
			String txnDevise, String customerName, String cancelComment, String cancelEventStatus,
			String customerNumber, String reqCancelId, String cardOldStatus, String compteDebitB, String compteCreditB,
			String typeAnnulationCarte, double txnAmountB, String agenceCompteCreditA, String agenceCompteDebitA,
			String agenceCompteCreditB, String agenceCompteDebitB) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.cardnewStatus = cardnewStatus;
		this.instiutionNumber = instiutionNumber;
		this.initiator = initiator;
		this.dateInitiator = dateInitiator;
		this.validator = validator;
		this.dateValidation = dateValidation;
		this.statusOpCsc = statusOpCsc;
		this.compteCreditA = compteCreditA;
		this.compteDebitA = compteDebitA;
		this.motifAnnulation = motifAnnulation;
		this.txnReferenceCodeBankA = txnReferenceCodeBankA;
		this.txnReferenceCodeBankB = txnReferenceCodeBankB;
		this.statusOpAmplitudeA = statusOpAmplitudeA;
		this.statusOpAmplitudeB = statusOpAmplitudeB;
		this.txnAmountA = txnAmountA;
		this.txnDevise = txnDevise;
		this.customerName = customerName;
		this.cancelComment = cancelComment;
		this.cancelEventStatus = cancelEventStatus;
		this.customerNumber = customerNumber;
		this.reqCancelId = reqCancelId;
		this.cardOldStatus = cardOldStatus;
		this.compteDebitB = compteDebitB;
		this.compteCreditB = compteCreditB;
		this.typeAnnulationCarte = typeAnnulationCarte;
		this.txnAmountB = txnAmountB;
		this.agenceCompteCreditA = agenceCompteCreditA;
		this.agenceCompteDebitA = agenceCompteDebitA;
		this.agenceCompteCreditB = agenceCompteCreditB;
		this.agenceCompteDebitB = agenceCompteDebitB;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getCardnewStatus() {
		return cardnewStatus;
	}


	public void setCardnewStatus(String cardnewStatus) {
		this.cardnewStatus = cardnewStatus;
	}


	public String getInstiutionNumber() {
		return instiutionNumber;
	}


	public void setInstiutionNumber(String instiutionNumber) {
		this.instiutionNumber = instiutionNumber;
	}


	public String getInitiator() {
		return initiator;
	}


	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}


	public String getDateInitiator() {
		return dateInitiator;
	}


	public void setDateInitiator(String dateInitiator) {
		this.dateInitiator = dateInitiator;
	}


	public String getValidator() {
		return validator;
	}


	public void setValidator(String validator) {
		this.validator = validator;
	}


	public String getDateValidation() {
		return dateValidation;
	}


	public void setDateValidation(String dateValidation) {
		this.dateValidation = dateValidation;
	}


	public String getStatusOpCsc() {
		return statusOpCsc;
	}


	public void setStatusOpCsc(String statusOpCsc) {
		this.statusOpCsc = statusOpCsc;
	}


	public String getCompteCreditA() {
		return compteCreditA;
	}


	public void setCompteCreditA(String compteCreditA) {
		this.compteCreditA = compteCreditA;
	}


	public String getCompteDebitA() {
		return compteDebitA;
	}


	public void setCompteDebitA(String compteDebitA) {
		this.compteDebitA = compteDebitA;
	}


	public String getMotifAnnulation() {
		return motifAnnulation;
	}


	public void setMotifAnnulation(String motifAnnulation) {
		this.motifAnnulation = motifAnnulation;
	}


	public String getTxnReferenceCodeBankA() {
		return txnReferenceCodeBankA;
	}


	public void setTxnReferenceCodeBankA(String txnReferenceCodeBankA) {
		this.txnReferenceCodeBankA = txnReferenceCodeBankA;
	}


	public String getTxnReferenceCodeBankB() {
		return txnReferenceCodeBankB;
	}


	public void setTxnReferenceCodeBankB(String txnReferenceCodeBankB) {
		this.txnReferenceCodeBankB = txnReferenceCodeBankB;
	}


	public String getStatusOpAmplitudeA() {
		return statusOpAmplitudeA;
	}


	public void setStatusOpAmplitudeA(String statusOpAmplitudeA) {
		this.statusOpAmplitudeA = statusOpAmplitudeA;
	}


	public String getStatusOpAmplitudeB() {
		return statusOpAmplitudeB;
	}


	public void setStatusOpAmplitudeB(String statusOpAmplitudeB) {
		this.statusOpAmplitudeB = statusOpAmplitudeB;
	}


	public double getTxnAmountA() {
		return txnAmountA;
	}


	public void setTxnAmountA(double txnAmountA) {
		this.txnAmountA = txnAmountA;
	}


	public String getTxnDevise() {
		return txnDevise;
	}


	public void setTxnDevise(String txnDevise) {
		this.txnDevise = txnDevise;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCancelComment() {
		return cancelComment;
	}


	public void setCancelComment(String cancelComment) {
		this.cancelComment = cancelComment;
	}


	public String getCancelEventStatus() {
		return cancelEventStatus;
	}


	public void setCancelEventStatus(String cancelEventStatus) {
		this.cancelEventStatus = cancelEventStatus;
	}


	public String getCustomerNumber() {
		return customerNumber;
	}


	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}


	public String getReqCancelId() {
		return reqCancelId;
	}


	public void setReqCancelId(String reqCancelId) {
		this.reqCancelId = reqCancelId;
	}


	public String getCardOldStatus() {
		return cardOldStatus;
	}


	public void setCardOldStatus(String cardOldStatus) {
		this.cardOldStatus = cardOldStatus;
	}


	public String getCompteDebitB() {
		return compteDebitB;
	}


	public void setCompteDebitB(String compteDebitB) {
		this.compteDebitB = compteDebitB;
	}


	public String getCompteCreditB() {
		return compteCreditB;
	}


	public void setCompteCreditB(String compteCreditB) {
		this.compteCreditB = compteCreditB;
	}


	public String getTypeAnnulationCarte() {
		return typeAnnulationCarte;
	}


	public void setTypeAnnulationCarte(String typeAnnulationCarte) {
		this.typeAnnulationCarte = typeAnnulationCarte;
	}


	public double getTxnAmountB() {
		return txnAmountB;
	}


	public void setTxnAmountB(double txnAmountB) {
		this.txnAmountB = txnAmountB;
	}


	public String getAgenceCompteCreditA() {
		return agenceCompteCreditA;
	}


	public void setAgenceCompteCreditA(String agenceCompteCreditA) {
		this.agenceCompteCreditA = agenceCompteCreditA;
	}


	public String getAgenceCompteDebitA() {
		return agenceCompteDebitA;
	}


	public void setAgenceCompteDebitA(String agenceCompteDebitA) {
		this.agenceCompteDebitA = agenceCompteDebitA;
	}


	public String getAgenceCompteCreditB() {
		return agenceCompteCreditB;
	}


	public void setAgenceCompteCreditB(String agenceCompteCreditB) {
		this.agenceCompteCreditB = agenceCompteCreditB;
	}


	public String getAgenceCompteDebitB() {
		return agenceCompteDebitB;
	}


	public void setAgenceCompteDebitB(String agenceCompteDebitB) {
		this.agenceCompteDebitB = agenceCompteDebitB;
	}


	@Override
	public String toString() {
		return "RawChangeStatusCancel [id=" + id + ", cardNumber=" + cardNumber + ", cardnewStatus=" + cardnewStatus
				+ ", instiutionNumber=" + instiutionNumber + ", initiator=" + initiator + ", dateInitiator="
				+ dateInitiator + ", validator=" + validator + ", dateValidation=" + dateValidation + ", statusOpCsc="
				+ statusOpCsc + ", compteCreditA=" + compteCreditA + ", compteDebitA=" + compteDebitA
				+ ", motifAnnulation=" + motifAnnulation + ", txnReferenceCodeBankA=" + txnReferenceCodeBankA
				+ ", txnReferenceCodeBankB=" + txnReferenceCodeBankB + ", statusOpAmplitudeA=" + statusOpAmplitudeA
				+ ", statusOpAmplitudeB=" + statusOpAmplitudeB + ", txnAmountA=" + txnAmountA + ", txnDevise="
				+ txnDevise + ", customerName=" + customerName + ", cancelComment=" + cancelComment
				+ ", cancelEventStatus=" + cancelEventStatus + ", customerNumber=" + customerNumber + ", reqCancelId="
				+ reqCancelId + ", cardOldStatus=" + cardOldStatus + ", compteDebitB=" + compteDebitB
				+ ", compteCreditB=" + compteCreditB + ", typeAnnulationCarte=" + typeAnnulationCarte + ", txnAmountB="
				+ txnAmountB + ", agenceCompteCreditA=" + agenceCompteCreditA + ", agenceCompteDebitA="
				+ agenceCompteDebitA + ", agenceCompteCreditB=" + agenceCompteCreditB + ", agenceCompteDebitB="
				+ agenceCompteDebitB + "]";
	}


	
	
}
