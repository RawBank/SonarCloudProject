
package com.rawbank.admin.client.stubs.getAccountDetails;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour overdraftAuthNIdentifier complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="overdraftAuthNIdentifier"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="branch" type="{http://soprabanking.com/amplitude}branchCode"/&gt;
 *         &lt;element name="currency" type="{http://soprabanking.com/amplitude}currencyCode"/&gt;
 *         &lt;element name="accountNumber" type="{http://soprabanking.com/amplitude}accountNumber"/&gt;
 *         &lt;element name="accountSuffix" type="{http://soprabanking.com/amplitude}accountSuffix"/&gt;
 *         &lt;element name="authorizationNumber" type="{http://soprabanking.com/amplitude}charMax9"/&gt;
 *         &lt;element name="orderNumber" type="{http://soprabanking.com/amplitude}decimal2_0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "overdraftAuthNIdentifier", propOrder = {
    "branch",
    "currency",
    "accountNumber",
    "accountSuffix",
    "authorizationNumber",
    "orderNumber"
})
public class OverdraftAuthNIdentifier {

    @XmlElement(required = true)
    protected String branch;
    @XmlElement(required = true)
    protected String currency;
    @XmlElement(required = true)
    protected String accountNumber;
    @XmlElement(required = true)
    protected String accountSuffix;
    @XmlElement(required = true)
    protected String authorizationNumber;
    @XmlElement(required = true)
    protected BigDecimal orderNumber;

    /**
     * Obtient la valeur de la propriété branch.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Définit la valeur de la propriété branch.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranch(String value) {
        this.branch = value;
    }

    /**
     * Obtient la valeur de la propriété currency.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Définit la valeur de la propriété currency.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Obtient la valeur de la propriété accountNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Définit la valeur de la propriété accountNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Obtient la valeur de la propriété accountSuffix.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountSuffix() {
        return accountSuffix;
    }

    /**
     * Définit la valeur de la propriété accountSuffix.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountSuffix(String value) {
        this.accountSuffix = value;
    }

    /**
     * Obtient la valeur de la propriété authorizationNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorizationNumber() {
        return authorizationNumber;
    }

    /**
     * Définit la valeur de la propriété authorizationNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorizationNumber(String value) {
        this.authorizationNumber = value;
    }

    /**
     * Obtient la valeur de la propriété orderNumber.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOrderNumber() {
        return orderNumber;
    }

    /**
     * Définit la valeur de la propriété orderNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOrderNumber(BigDecimal value) {
        this.orderNumber = value;
    }

}
