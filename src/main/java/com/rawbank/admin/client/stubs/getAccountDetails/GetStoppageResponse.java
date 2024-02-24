
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getStoppageResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getStoppageResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="stoppageType" type="{http://soprabanking.com/amplitude}stoppageType" minOccurs="0"/&gt;
 *         &lt;element name="customer" type="{http://soprabanking.com/amplitude}populationFile" minOccurs="0"/&gt;
 *         &lt;element name="account" type="{http://soprabanking.com/amplitude}accountFile" minOccurs="0"/&gt;
 *         &lt;element name="stoppage" type="{http://soprabanking.com/amplitude}stoppage" minOccurs="0"/&gt;
 *         &lt;element name="startDate" type="{http://soprabanking.com/amplitude}comparisonDate" minOccurs="0"/&gt;
 *         &lt;element name="endDate" type="{http://soprabanking.com/amplitude}comparisonDate" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://soprabanking.com/amplitude}stoppageStatus" minOccurs="0"/&gt;
 *         &lt;element name="stoppageEndDate" type="{http://soprabanking.com/amplitude}comparisonDate" minOccurs="0"/&gt;
 *         &lt;element name="stoppageEndUser" type="{http://soprabanking.com/amplitude}user" minOccurs="0"/&gt;
 *         &lt;element name="creationUser" type="{http://soprabanking.com/amplitude}user" minOccurs="0"/&gt;
 *         &lt;element name="stoppageReason" type="{http://soprabanking.com/amplitude}charMax40" minOccurs="0"/&gt;
 *         &lt;element name="stoppageEndReason" type="{http://soprabanking.com/amplitude}charMax40" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStoppageResponse", propOrder = {
    "stoppageType",
    "customer",
    "account",
    "stoppage",
    "startDate",
    "endDate",
    "status",
    "stoppageEndDate",
    "stoppageEndUser",
    "creationUser",
    "stoppageReason",
    "stoppageEndReason"
})
public class GetStoppageResponse {

    protected String stoppageType;
    protected PopulationFile customer;
    protected AccountFile account;
    protected Stoppage stoppage;
    protected ComparisonDate startDate;
    protected ComparisonDate endDate;
    @XmlSchemaType(name = "string")
    protected StoppageStatus status;
    protected ComparisonDate stoppageEndDate;
    protected User stoppageEndUser;
    protected User creationUser;
    protected String stoppageReason;
    protected String stoppageEndReason;

    /**
     * Obtient la valeur de la propriété stoppageType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoppageType() {
        return stoppageType;
    }

    /**
     * Définit la valeur de la propriété stoppageType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoppageType(String value) {
        this.stoppageType = value;
    }

    /**
     * Obtient la valeur de la propriété customer.
     * 
     * @return
     *     possible object is
     *     {@link PopulationFile }
     *     
     */
    public PopulationFile getCustomer() {
        return customer;
    }

    /**
     * Définit la valeur de la propriété customer.
     * 
     * @param value
     *     allowed object is
     *     {@link PopulationFile }
     *     
     */
    public void setCustomer(PopulationFile value) {
        this.customer = value;
    }

    /**
     * Obtient la valeur de la propriété account.
     * 
     * @return
     *     possible object is
     *     {@link AccountFile }
     *     
     */
    public AccountFile getAccount() {
        return account;
    }

    /**
     * Définit la valeur de la propriété account.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountFile }
     *     
     */
    public void setAccount(AccountFile value) {
        this.account = value;
    }

    /**
     * Obtient la valeur de la propriété stoppage.
     * 
     * @return
     *     possible object is
     *     {@link Stoppage }
     *     
     */
    public Stoppage getStoppage() {
        return stoppage;
    }

    /**
     * Définit la valeur de la propriété stoppage.
     * 
     * @param value
     *     allowed object is
     *     {@link Stoppage }
     *     
     */
    public void setStoppage(Stoppage value) {
        this.stoppage = value;
    }

    /**
     * Obtient la valeur de la propriété startDate.
     * 
     * @return
     *     possible object is
     *     {@link ComparisonDate }
     *     
     */
    public ComparisonDate getStartDate() {
        return startDate;
    }

    /**
     * Définit la valeur de la propriété startDate.
     * 
     * @param value
     *     allowed object is
     *     {@link ComparisonDate }
     *     
     */
    public void setStartDate(ComparisonDate value) {
        this.startDate = value;
    }

    /**
     * Obtient la valeur de la propriété endDate.
     * 
     * @return
     *     possible object is
     *     {@link ComparisonDate }
     *     
     */
    public ComparisonDate getEndDate() {
        return endDate;
    }

    /**
     * Définit la valeur de la propriété endDate.
     * 
     * @param value
     *     allowed object is
     *     {@link ComparisonDate }
     *     
     */
    public void setEndDate(ComparisonDate value) {
        this.endDate = value;
    }

    /**
     * Obtient la valeur de la propriété status.
     * 
     * @return
     *     possible object is
     *     {@link StoppageStatus }
     *     
     */
    public StoppageStatus getStatus() {
        return status;
    }

    /**
     * Définit la valeur de la propriété status.
     * 
     * @param value
     *     allowed object is
     *     {@link StoppageStatus }
     *     
     */
    public void setStatus(StoppageStatus value) {
        this.status = value;
    }

    /**
     * Obtient la valeur de la propriété stoppageEndDate.
     * 
     * @return
     *     possible object is
     *     {@link ComparisonDate }
     *     
     */
    public ComparisonDate getStoppageEndDate() {
        return stoppageEndDate;
    }

    /**
     * Définit la valeur de la propriété stoppageEndDate.
     * 
     * @param value
     *     allowed object is
     *     {@link ComparisonDate }
     *     
     */
    public void setStoppageEndDate(ComparisonDate value) {
        this.stoppageEndDate = value;
    }

    /**
     * Obtient la valeur de la propriété stoppageEndUser.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getStoppageEndUser() {
        return stoppageEndUser;
    }

    /**
     * Définit la valeur de la propriété stoppageEndUser.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setStoppageEndUser(User value) {
        this.stoppageEndUser = value;
    }

    /**
     * Obtient la valeur de la propriété creationUser.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getCreationUser() {
        return creationUser;
    }

    /**
     * Définit la valeur de la propriété creationUser.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setCreationUser(User value) {
        this.creationUser = value;
    }

    /**
     * Obtient la valeur de la propriété stoppageReason.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoppageReason() {
        return stoppageReason;
    }

    /**
     * Définit la valeur de la propriété stoppageReason.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoppageReason(String value) {
        this.stoppageReason = value;
    }

    /**
     * Obtient la valeur de la propriété stoppageEndReason.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoppageEndReason() {
        return stoppageEndReason;
    }

    /**
     * Définit la valeur de la propriété stoppageEndReason.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoppageEndReason(String value) {
        this.stoppageEndReason = value;
    }

}
