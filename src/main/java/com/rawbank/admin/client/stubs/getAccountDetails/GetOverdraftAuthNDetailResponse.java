
package com.rawbank.admin.client.stubs.getAccountDetails;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour getOverdraftAuthNDetailResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getOverdraftAuthNDetailResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="overdraftAuthNIdentifier" type="{http://soprabanking.com/amplitude}overdraftAuthNIdentifier" minOccurs="0"/&gt;
 *         &lt;element name="overallCharges" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="reference" type="{http://soprabanking.com/amplitude}charMax20" minOccurs="0"/&gt;
 *         &lt;element name="occasionalAuthorisation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="startDateOfOverdraftLimit" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="endDateOfOverdraftLimit" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="overdraftLimitValue" type="{http://soprabanking.com/amplitude}decimal12_0" minOccurs="0"/&gt;
 *         &lt;element name="amountOfChargesInForeignCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="amountOfChargesInLocalCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="taxRate" type="{http://soprabanking.com/amplitude}decimal15_7" minOccurs="0"/&gt;
 *         &lt;element name="taxAmountInForeignCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="taxAmountInLocalCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="operationNature" type="{http://soprabanking.com/amplitude}operationNature" minOccurs="0"/&gt;
 *         &lt;element name="operation" type="{http://soprabanking.com/amplitude}operation" minOccurs="0"/&gt;
 *         &lt;element name="userWhoInitiated" type="{http://soprabanking.com/amplitude}user" minOccurs="0"/&gt;
 *         &lt;element name="creationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="lastModificationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="overdraftLimitStatus" type="{http://soprabanking.com/amplitude}charMax2" minOccurs="0"/&gt;
 *         &lt;element name="userWhoRetrievedToOverride" type="{http://soprabanking.com/amplitude}user" minOccurs="0"/&gt;
 *         &lt;element name="userWhoAuthorized" type="{http://soprabanking.com/amplitude}user" minOccurs="0"/&gt;
 *         &lt;element name="overrideKey" type="{http://soprabanking.com/amplitude}decimal6_0" minOccurs="0"/&gt;
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="overdraftLimitSituation" type="{http://soprabanking.com/amplitude}overdraftLimitSituation" minOccurs="0"/&gt;
 *         &lt;element name="eventNumber" type="{http://soprabanking.com/amplitude}eventNumber" minOccurs="0"/&gt;
 *         &lt;element name="financingCommitmentAccount" type="{http://soprabanking.com/amplitude}internalFormatAccountOurBranch" minOccurs="0"/&gt;
 *         &lt;element name="managementCommissionRate" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="managementCommissionAmountInForeignCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="managementCommissionAmountInLocalCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="renewal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="overdraftAuthNType" type="{http://soprabanking.com/amplitude}overdraftAuthNType" minOccurs="0"/&gt;
 *         &lt;element name="anniversaryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="period" type="{http://soprabanking.com/amplitude}decimal6_0" minOccurs="0"/&gt;
 *         &lt;element name="offBalanceSheetCommitmentManagement" type="{http://soprabanking.com/amplitude}char1" minOccurs="0"/&gt;
 *         &lt;element name="initialCommissionAmountInForeignCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="initialCommissionAmountInLocalCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="amountOfFollowingYearsManagementCommissionInForeignCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="amountOfFollowingYearsManagementCommissionInLocalCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="rateOfFollowingYearsManagementCommission" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="terminationCommissionAmountInForeignCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="terminationCommissionAmountInLocalCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="resiliationCommissionRate" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="negotiationCommissionAmountInForeignCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="negotiationCommissionAmountInLocalCurrency" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="negotiationCommissionRate" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="expenseAccount" type="{http://soprabanking.com/amplitude}internalFormatAccountOurBranch" minOccurs="0"/&gt;
 *         &lt;element name="recoveryMode" type="{http://soprabanking.com/amplitude}recoveryMode" minOccurs="0"/&gt;
 *         &lt;element name="recoveryDuration" type="{http://soprabanking.com/amplitude}decimal5_0" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOverdraftAuthNDetailResponse", propOrder = {
    "overdraftAuthNIdentifier",
    "overallCharges",
    "reference",
    "occasionalAuthorisation",
    "startDateOfOverdraftLimit",
    "endDateOfOverdraftLimit",
    "expiryDate",
    "overdraftLimitValue",
    "amountOfChargesInForeignCurrency",
    "amountOfChargesInLocalCurrency",
    "taxRate",
    "taxAmountInForeignCurrency",
    "taxAmountInLocalCurrency",
    "operationNature",
    "operation",
    "userWhoInitiated",
    "creationDate",
    "lastModificationDate",
    "overdraftLimitStatus",
    "userWhoRetrievedToOverride",
    "userWhoAuthorized",
    "overrideKey",
    "date",
    "overdraftLimitSituation",
    "eventNumber",
    "financingCommitmentAccount",
    "managementCommissionRate",
    "managementCommissionAmountInForeignCurrency",
    "managementCommissionAmountInLocalCurrency",
    "renewal",
    "overdraftAuthNType",
    "anniversaryDate",
    "period",
    "offBalanceSheetCommitmentManagement",
    "initialCommissionAmountInForeignCurrency",
    "initialCommissionAmountInLocalCurrency",
    "amountOfFollowingYearsManagementCommissionInForeignCurrency",
    "amountOfFollowingYearsManagementCommissionInLocalCurrency",
    "rateOfFollowingYearsManagementCommission",
    "terminationCommissionAmountInForeignCurrency",
    "terminationCommissionAmountInLocalCurrency",
    "resiliationCommissionRate",
    "negotiationCommissionAmountInForeignCurrency",
    "negotiationCommissionAmountInLocalCurrency",
    "negotiationCommissionRate",
    "expenseAccount",
    "recoveryMode",
    "recoveryDuration"
})
public class GetOverdraftAuthNDetailResponse {

    protected OverdraftAuthNIdentifier overdraftAuthNIdentifier;
    protected Boolean overallCharges;
    protected String reference;
    protected Boolean occasionalAuthorisation;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDateOfOverdraftLimit;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDateOfOverdraftLimit;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expiryDate;
    protected BigDecimal overdraftLimitValue;
    protected BigDecimal amountOfChargesInForeignCurrency;
    protected BigDecimal amountOfChargesInLocalCurrency;
    protected BigDecimal taxRate;
    protected BigDecimal taxAmountInForeignCurrency;
    protected BigDecimal taxAmountInLocalCurrency;
    protected OperationNature operationNature;
    protected Operation operation;
    protected User userWhoInitiated;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar creationDate;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastModificationDate;
    protected String overdraftLimitStatus;
    protected User userWhoRetrievedToOverride;
    protected User userWhoAuthorized;
    protected BigDecimal overrideKey;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlSchemaType(name = "string")
    protected OverdraftLimitSituation overdraftLimitSituation;
    protected String eventNumber;
    protected InternalFormatAccountOurBranch financingCommitmentAccount;
    protected BigDecimal managementCommissionRate;
    protected BigDecimal managementCommissionAmountInForeignCurrency;
    protected BigDecimal managementCommissionAmountInLocalCurrency;
    protected Boolean renewal;
    protected OverdraftAuthNType overdraftAuthNType;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar anniversaryDate;
    protected BigDecimal period;
    protected String offBalanceSheetCommitmentManagement;
    protected BigDecimal initialCommissionAmountInForeignCurrency;
    protected BigDecimal initialCommissionAmountInLocalCurrency;
    protected BigDecimal amountOfFollowingYearsManagementCommissionInForeignCurrency;
    protected BigDecimal amountOfFollowingYearsManagementCommissionInLocalCurrency;
    protected BigDecimal rateOfFollowingYearsManagementCommission;
    protected BigDecimal terminationCommissionAmountInForeignCurrency;
    protected BigDecimal terminationCommissionAmountInLocalCurrency;
    protected BigDecimal resiliationCommissionRate;
    protected BigDecimal negotiationCommissionAmountInForeignCurrency;
    protected BigDecimal negotiationCommissionAmountInLocalCurrency;
    protected BigDecimal negotiationCommissionRate;
    protected InternalFormatAccountOurBranch expenseAccount;
    @XmlSchemaType(name = "string")
    protected RecoveryMode recoveryMode;
    protected BigDecimal recoveryDuration;

    /**
     * Obtient la valeur de la propriété overdraftAuthNIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link OverdraftAuthNIdentifier }
     *     
     */
    public OverdraftAuthNIdentifier getOverdraftAuthNIdentifier() {
        return overdraftAuthNIdentifier;
    }

    /**
     * Définit la valeur de la propriété overdraftAuthNIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link OverdraftAuthNIdentifier }
     *     
     */
    public void setOverdraftAuthNIdentifier(OverdraftAuthNIdentifier value) {
        this.overdraftAuthNIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété overallCharges.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOverallCharges() {
        return overallCharges;
    }

    /**
     * Définit la valeur de la propriété overallCharges.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOverallCharges(Boolean value) {
        this.overallCharges = value;
    }

    /**
     * Obtient la valeur de la propriété reference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Définit la valeur de la propriété reference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Obtient la valeur de la propriété occasionalAuthorisation.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOccasionalAuthorisation() {
        return occasionalAuthorisation;
    }

    /**
     * Définit la valeur de la propriété occasionalAuthorisation.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOccasionalAuthorisation(Boolean value) {
        this.occasionalAuthorisation = value;
    }

    /**
     * Obtient la valeur de la propriété startDateOfOverdraftLimit.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDateOfOverdraftLimit() {
        return startDateOfOverdraftLimit;
    }

    /**
     * Définit la valeur de la propriété startDateOfOverdraftLimit.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDateOfOverdraftLimit(XMLGregorianCalendar value) {
        this.startDateOfOverdraftLimit = value;
    }

    /**
     * Obtient la valeur de la propriété endDateOfOverdraftLimit.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDateOfOverdraftLimit() {
        return endDateOfOverdraftLimit;
    }

    /**
     * Définit la valeur de la propriété endDateOfOverdraftLimit.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDateOfOverdraftLimit(XMLGregorianCalendar value) {
        this.endDateOfOverdraftLimit = value;
    }

    /**
     * Obtient la valeur de la propriété expiryDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Définit la valeur de la propriété expiryDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiryDate(XMLGregorianCalendar value) {
        this.expiryDate = value;
    }

    /**
     * Obtient la valeur de la propriété overdraftLimitValue.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOverdraftLimitValue() {
        return overdraftLimitValue;
    }

    /**
     * Définit la valeur de la propriété overdraftLimitValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOverdraftLimitValue(BigDecimal value) {
        this.overdraftLimitValue = value;
    }

    /**
     * Obtient la valeur de la propriété amountOfChargesInForeignCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountOfChargesInForeignCurrency() {
        return amountOfChargesInForeignCurrency;
    }

    /**
     * Définit la valeur de la propriété amountOfChargesInForeignCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountOfChargesInForeignCurrency(BigDecimal value) {
        this.amountOfChargesInForeignCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété amountOfChargesInLocalCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountOfChargesInLocalCurrency() {
        return amountOfChargesInLocalCurrency;
    }

    /**
     * Définit la valeur de la propriété amountOfChargesInLocalCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountOfChargesInLocalCurrency(BigDecimal value) {
        this.amountOfChargesInLocalCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété taxRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * Définit la valeur de la propriété taxRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxRate(BigDecimal value) {
        this.taxRate = value;
    }

    /**
     * Obtient la valeur de la propriété taxAmountInForeignCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxAmountInForeignCurrency() {
        return taxAmountInForeignCurrency;
    }

    /**
     * Définit la valeur de la propriété taxAmountInForeignCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxAmountInForeignCurrency(BigDecimal value) {
        this.taxAmountInForeignCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété taxAmountInLocalCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxAmountInLocalCurrency() {
        return taxAmountInLocalCurrency;
    }

    /**
     * Définit la valeur de la propriété taxAmountInLocalCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxAmountInLocalCurrency(BigDecimal value) {
        this.taxAmountInLocalCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété operationNature.
     * 
     * @return
     *     possible object is
     *     {@link OperationNature }
     *     
     */
    public OperationNature getOperationNature() {
        return operationNature;
    }

    /**
     * Définit la valeur de la propriété operationNature.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationNature }
     *     
     */
    public void setOperationNature(OperationNature value) {
        this.operationNature = value;
    }

    /**
     * Obtient la valeur de la propriété operation.
     * 
     * @return
     *     possible object is
     *     {@link Operation }
     *     
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Définit la valeur de la propriété operation.
     * 
     * @param value
     *     allowed object is
     *     {@link Operation }
     *     
     */
    public void setOperation(Operation value) {
        this.operation = value;
    }

    /**
     * Obtient la valeur de la propriété userWhoInitiated.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUserWhoInitiated() {
        return userWhoInitiated;
    }

    /**
     * Définit la valeur de la propriété userWhoInitiated.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUserWhoInitiated(User value) {
        this.userWhoInitiated = value;
    }

    /**
     * Obtient la valeur de la propriété creationDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Définit la valeur de la propriété creationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Obtient la valeur de la propriété lastModificationDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModificationDate() {
        return lastModificationDate;
    }

    /**
     * Définit la valeur de la propriété lastModificationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModificationDate(XMLGregorianCalendar value) {
        this.lastModificationDate = value;
    }

    /**
     * Obtient la valeur de la propriété overdraftLimitStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverdraftLimitStatus() {
        return overdraftLimitStatus;
    }

    /**
     * Définit la valeur de la propriété overdraftLimitStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverdraftLimitStatus(String value) {
        this.overdraftLimitStatus = value;
    }

    /**
     * Obtient la valeur de la propriété userWhoRetrievedToOverride.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUserWhoRetrievedToOverride() {
        return userWhoRetrievedToOverride;
    }

    /**
     * Définit la valeur de la propriété userWhoRetrievedToOverride.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUserWhoRetrievedToOverride(User value) {
        this.userWhoRetrievedToOverride = value;
    }

    /**
     * Obtient la valeur de la propriété userWhoAuthorized.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUserWhoAuthorized() {
        return userWhoAuthorized;
    }

    /**
     * Définit la valeur de la propriété userWhoAuthorized.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUserWhoAuthorized(User value) {
        this.userWhoAuthorized = value;
    }

    /**
     * Obtient la valeur de la propriété overrideKey.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOverrideKey() {
        return overrideKey;
    }

    /**
     * Définit la valeur de la propriété overrideKey.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOverrideKey(BigDecimal value) {
        this.overrideKey = value;
    }

    /**
     * Obtient la valeur de la propriété date.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Obtient la valeur de la propriété overdraftLimitSituation.
     * 
     * @return
     *     possible object is
     *     {@link OverdraftLimitSituation }
     *     
     */
    public OverdraftLimitSituation getOverdraftLimitSituation() {
        return overdraftLimitSituation;
    }

    /**
     * Définit la valeur de la propriété overdraftLimitSituation.
     * 
     * @param value
     *     allowed object is
     *     {@link OverdraftLimitSituation }
     *     
     */
    public void setOverdraftLimitSituation(OverdraftLimitSituation value) {
        this.overdraftLimitSituation = value;
    }

    /**
     * Obtient la valeur de la propriété eventNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventNumber() {
        return eventNumber;
    }

    /**
     * Définit la valeur de la propriété eventNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventNumber(String value) {
        this.eventNumber = value;
    }

    /**
     * Obtient la valeur de la propriété financingCommitmentAccount.
     * 
     * @return
     *     possible object is
     *     {@link InternalFormatAccountOurBranch }
     *     
     */
    public InternalFormatAccountOurBranch getFinancingCommitmentAccount() {
        return financingCommitmentAccount;
    }

    /**
     * Définit la valeur de la propriété financingCommitmentAccount.
     * 
     * @param value
     *     allowed object is
     *     {@link InternalFormatAccountOurBranch }
     *     
     */
    public void setFinancingCommitmentAccount(InternalFormatAccountOurBranch value) {
        this.financingCommitmentAccount = value;
    }

    /**
     * Obtient la valeur de la propriété managementCommissionRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getManagementCommissionRate() {
        return managementCommissionRate;
    }

    /**
     * Définit la valeur de la propriété managementCommissionRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setManagementCommissionRate(BigDecimal value) {
        this.managementCommissionRate = value;
    }

    /**
     * Obtient la valeur de la propriété managementCommissionAmountInForeignCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getManagementCommissionAmountInForeignCurrency() {
        return managementCommissionAmountInForeignCurrency;
    }

    /**
     * Définit la valeur de la propriété managementCommissionAmountInForeignCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setManagementCommissionAmountInForeignCurrency(BigDecimal value) {
        this.managementCommissionAmountInForeignCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété managementCommissionAmountInLocalCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getManagementCommissionAmountInLocalCurrency() {
        return managementCommissionAmountInLocalCurrency;
    }

    /**
     * Définit la valeur de la propriété managementCommissionAmountInLocalCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setManagementCommissionAmountInLocalCurrency(BigDecimal value) {
        this.managementCommissionAmountInLocalCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété renewal.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRenewal() {
        return renewal;
    }

    /**
     * Définit la valeur de la propriété renewal.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRenewal(Boolean value) {
        this.renewal = value;
    }

    /**
     * Obtient la valeur de la propriété overdraftAuthNType.
     * 
     * @return
     *     possible object is
     *     {@link OverdraftAuthNType }
     *     
     */
    public OverdraftAuthNType getOverdraftAuthNType() {
        return overdraftAuthNType;
    }

    /**
     * Définit la valeur de la propriété overdraftAuthNType.
     * 
     * @param value
     *     allowed object is
     *     {@link OverdraftAuthNType }
     *     
     */
    public void setOverdraftAuthNType(OverdraftAuthNType value) {
        this.overdraftAuthNType = value;
    }

    /**
     * Obtient la valeur de la propriété anniversaryDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAnniversaryDate() {
        return anniversaryDate;
    }

    /**
     * Définit la valeur de la propriété anniversaryDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAnniversaryDate(XMLGregorianCalendar value) {
        this.anniversaryDate = value;
    }

    /**
     * Obtient la valeur de la propriété period.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPeriod() {
        return period;
    }

    /**
     * Définit la valeur de la propriété period.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPeriod(BigDecimal value) {
        this.period = value;
    }

    /**
     * Obtient la valeur de la propriété offBalanceSheetCommitmentManagement.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffBalanceSheetCommitmentManagement() {
        return offBalanceSheetCommitmentManagement;
    }

    /**
     * Définit la valeur de la propriété offBalanceSheetCommitmentManagement.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffBalanceSheetCommitmentManagement(String value) {
        this.offBalanceSheetCommitmentManagement = value;
    }

    /**
     * Obtient la valeur de la propriété initialCommissionAmountInForeignCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInitialCommissionAmountInForeignCurrency() {
        return initialCommissionAmountInForeignCurrency;
    }

    /**
     * Définit la valeur de la propriété initialCommissionAmountInForeignCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInitialCommissionAmountInForeignCurrency(BigDecimal value) {
        this.initialCommissionAmountInForeignCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété initialCommissionAmountInLocalCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInitialCommissionAmountInLocalCurrency() {
        return initialCommissionAmountInLocalCurrency;
    }

    /**
     * Définit la valeur de la propriété initialCommissionAmountInLocalCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInitialCommissionAmountInLocalCurrency(BigDecimal value) {
        this.initialCommissionAmountInLocalCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété amountOfFollowingYearsManagementCommissionInForeignCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountOfFollowingYearsManagementCommissionInForeignCurrency() {
        return amountOfFollowingYearsManagementCommissionInForeignCurrency;
    }

    /**
     * Définit la valeur de la propriété amountOfFollowingYearsManagementCommissionInForeignCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountOfFollowingYearsManagementCommissionInForeignCurrency(BigDecimal value) {
        this.amountOfFollowingYearsManagementCommissionInForeignCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété amountOfFollowingYearsManagementCommissionInLocalCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountOfFollowingYearsManagementCommissionInLocalCurrency() {
        return amountOfFollowingYearsManagementCommissionInLocalCurrency;
    }

    /**
     * Définit la valeur de la propriété amountOfFollowingYearsManagementCommissionInLocalCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountOfFollowingYearsManagementCommissionInLocalCurrency(BigDecimal value) {
        this.amountOfFollowingYearsManagementCommissionInLocalCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété rateOfFollowingYearsManagementCommission.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRateOfFollowingYearsManagementCommission() {
        return rateOfFollowingYearsManagementCommission;
    }

    /**
     * Définit la valeur de la propriété rateOfFollowingYearsManagementCommission.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRateOfFollowingYearsManagementCommission(BigDecimal value) {
        this.rateOfFollowingYearsManagementCommission = value;
    }

    /**
     * Obtient la valeur de la propriété terminationCommissionAmountInForeignCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTerminationCommissionAmountInForeignCurrency() {
        return terminationCommissionAmountInForeignCurrency;
    }

    /**
     * Définit la valeur de la propriété terminationCommissionAmountInForeignCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTerminationCommissionAmountInForeignCurrency(BigDecimal value) {
        this.terminationCommissionAmountInForeignCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété terminationCommissionAmountInLocalCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTerminationCommissionAmountInLocalCurrency() {
        return terminationCommissionAmountInLocalCurrency;
    }

    /**
     * Définit la valeur de la propriété terminationCommissionAmountInLocalCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTerminationCommissionAmountInLocalCurrency(BigDecimal value) {
        this.terminationCommissionAmountInLocalCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété resiliationCommissionRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getResiliationCommissionRate() {
        return resiliationCommissionRate;
    }

    /**
     * Définit la valeur de la propriété resiliationCommissionRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setResiliationCommissionRate(BigDecimal value) {
        this.resiliationCommissionRate = value;
    }

    /**
     * Obtient la valeur de la propriété negotiationCommissionAmountInForeignCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNegotiationCommissionAmountInForeignCurrency() {
        return negotiationCommissionAmountInForeignCurrency;
    }

    /**
     * Définit la valeur de la propriété negotiationCommissionAmountInForeignCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNegotiationCommissionAmountInForeignCurrency(BigDecimal value) {
        this.negotiationCommissionAmountInForeignCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété negotiationCommissionAmountInLocalCurrency.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNegotiationCommissionAmountInLocalCurrency() {
        return negotiationCommissionAmountInLocalCurrency;
    }

    /**
     * Définit la valeur de la propriété negotiationCommissionAmountInLocalCurrency.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNegotiationCommissionAmountInLocalCurrency(BigDecimal value) {
        this.negotiationCommissionAmountInLocalCurrency = value;
    }

    /**
     * Obtient la valeur de la propriété negotiationCommissionRate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNegotiationCommissionRate() {
        return negotiationCommissionRate;
    }

    /**
     * Définit la valeur de la propriété negotiationCommissionRate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNegotiationCommissionRate(BigDecimal value) {
        this.negotiationCommissionRate = value;
    }

    /**
     * Obtient la valeur de la propriété expenseAccount.
     * 
     * @return
     *     possible object is
     *     {@link InternalFormatAccountOurBranch }
     *     
     */
    public InternalFormatAccountOurBranch getExpenseAccount() {
        return expenseAccount;
    }

    /**
     * Définit la valeur de la propriété expenseAccount.
     * 
     * @param value
     *     allowed object is
     *     {@link InternalFormatAccountOurBranch }
     *     
     */
    public void setExpenseAccount(InternalFormatAccountOurBranch value) {
        this.expenseAccount = value;
    }

    /**
     * Obtient la valeur de la propriété recoveryMode.
     * 
     * @return
     *     possible object is
     *     {@link RecoveryMode }
     *     
     */
    public RecoveryMode getRecoveryMode() {
        return recoveryMode;
    }

    /**
     * Définit la valeur de la propriété recoveryMode.
     * 
     * @param value
     *     allowed object is
     *     {@link RecoveryMode }
     *     
     */
    public void setRecoveryMode(RecoveryMode value) {
        this.recoveryMode = value;
    }

    /**
     * Obtient la valeur de la propriété recoveryDuration.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRecoveryDuration() {
        return recoveryDuration;
    }

    /**
     * Définit la valeur de la propriété recoveryDuration.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRecoveryDuration(BigDecimal value) {
        this.recoveryDuration = value;
    }

}
