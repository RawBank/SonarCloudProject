
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour populationFile complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="populationFile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="customer" type="{http://soprabanking.com/amplitude}restrictedCustomer" minOccurs="0"/&gt;
 *         &lt;element name="customerType" type="{http://soprabanking.com/amplitude}customerType" minOccurs="0"/&gt;
 *         &lt;element name="activeProfile" type="{http://soprabanking.com/amplitude}customerProfile" minOccurs="0"/&gt;
 *         &lt;element name="nationalIdentifier" type="{http://soprabanking.com/amplitude}charMax20" minOccurs="0"/&gt;
 *         &lt;element name="customerOfficer" type="{http://soprabanking.com/amplitude}customerOfficer" minOccurs="0"/&gt;
 *         &lt;element name="additionalInformation" type="{http://soprabanking.com/amplitude}customerComplementaryInformation" minOccurs="0"/&gt;
 *         &lt;element name="externalReference" type="{http://soprabanking.com/amplitude}charMax25" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "populationFile", propOrder = {
    "customer",
    "customerType",
    "activeProfile",
    "nationalIdentifier",
    "customerOfficer",
    "additionalInformation",
    "externalReference"
})
public class PopulationFile {

    protected RestrictedCustomer customer;
    protected String customerType;
    protected CustomerProfile activeProfile;
    protected String nationalIdentifier;
    protected CustomerOfficer customerOfficer;
    protected CustomerComplementaryInformation additionalInformation;
    protected String externalReference;

    /**
     * Obtient la valeur de la propriété customer.
     * 
     * @return
     *     possible object is
     *     {@link RestrictedCustomer }
     *     
     */
    public RestrictedCustomer getCustomer() {
        return customer;
    }

    /**
     * Définit la valeur de la propriété customer.
     * 
     * @param value
     *     allowed object is
     *     {@link RestrictedCustomer }
     *     
     */
    public void setCustomer(RestrictedCustomer value) {
        this.customer = value;
    }

    /**
     * Obtient la valeur de la propriété customerType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * Définit la valeur de la propriété customerType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerType(String value) {
        this.customerType = value;
    }

    /**
     * Obtient la valeur de la propriété activeProfile.
     * 
     * @return
     *     possible object is
     *     {@link CustomerProfile }
     *     
     */
    public CustomerProfile getActiveProfile() {
        return activeProfile;
    }

    /**
     * Définit la valeur de la propriété activeProfile.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerProfile }
     *     
     */
    public void setActiveProfile(CustomerProfile value) {
        this.activeProfile = value;
    }

    /**
     * Obtient la valeur de la propriété nationalIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalIdentifier() {
        return nationalIdentifier;
    }

    /**
     * Définit la valeur de la propriété nationalIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalIdentifier(String value) {
        this.nationalIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété customerOfficer.
     * 
     * @return
     *     possible object is
     *     {@link CustomerOfficer }
     *     
     */
    public CustomerOfficer getCustomerOfficer() {
        return customerOfficer;
    }

    /**
     * Définit la valeur de la propriété customerOfficer.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerOfficer }
     *     
     */
    public void setCustomerOfficer(CustomerOfficer value) {
        this.customerOfficer = value;
    }

    /**
     * Obtient la valeur de la propriété additionalInformation.
     * 
     * @return
     *     possible object is
     *     {@link CustomerComplementaryInformation }
     *     
     */
    public CustomerComplementaryInformation getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * Définit la valeur de la propriété additionalInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerComplementaryInformation }
     *     
     */
    public void setAdditionalInformation(CustomerComplementaryInformation value) {
        this.additionalInformation = value;
    }

    /**
     * Obtient la valeur de la propriété externalReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalReference() {
        return externalReference;
    }

    /**
     * Définit la valeur de la propriété externalReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalReference(String value) {
        this.externalReference = value;
    }

}
