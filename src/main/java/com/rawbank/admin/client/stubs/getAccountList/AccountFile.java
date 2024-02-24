
package com.rawbank.admin.client.stubs.getAccountList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour accountFile complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="accountFile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accountNumber" type="{http://soprabanking.com/amplitude}accountIdentifierOurBranch" minOccurs="0"/&gt;
 *         &lt;element name="accountClass" type="{http://soprabanking.com/amplitude}designation" minOccurs="0"/&gt;
 *         &lt;element name="customer" type="{http://soprabanking.com/amplitude}populationFile" minOccurs="0"/&gt;
 *         &lt;element name="additionalInformation" type="{http://soprabanking.com/amplitude}accountComplementaryInformation" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountFile", propOrder = {
    "accountNumber",
    "accountClass",
    "customer",
    "additionalInformation"
})
public class AccountFile {

    protected AccountIdentifierOurBranch accountNumber;
    protected Designation accountClass;
    protected PopulationFile customer;
    protected AccountComplementaryInformation additionalInformation;

    /**
     * Obtient la valeur de la propriété accountNumber.
     * 
     * @return
     *     possible object is
     *     {@link AccountIdentifierOurBranch }
     *     
     */
    public AccountIdentifierOurBranch getAccountNumber() {
        return accountNumber;
    }

    /**
     * Définit la valeur de la propriété accountNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountIdentifierOurBranch }
     *     
     */
    public void setAccountNumber(AccountIdentifierOurBranch value) {
        this.accountNumber = value;
    }

    /**
     * Obtient la valeur de la propriété accountClass.
     * 
     * @return
     *     possible object is
     *     {@link Designation }
     *     
     */
    public Designation getAccountClass() {
        return accountClass;
    }

    /**
     * Définit la valeur de la propriété accountClass.
     * 
     * @param value
     *     allowed object is
     *     {@link Designation }
     *     
     */
    public void setAccountClass(Designation value) {
        this.accountClass = value;
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
     * Obtient la valeur de la propriété additionalInformation.
     * 
     * @return
     *     possible object is
     *     {@link AccountComplementaryInformation }
     *     
     */
    public AccountComplementaryInformation getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * Définit la valeur de la propriété additionalInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountComplementaryInformation }
     *     
     */
    public void setAdditionalInformation(AccountComplementaryInformation value) {
        this.additionalInformation = value;
    }

}
