
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour comparisonAdditionalDataValue complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="comparisonAdditionalDataValue"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="alphanumeric" type="{http://soprabanking.com/amplitude}designation" minOccurs="0"/&gt;
 *         &lt;element name="amountOrRate" type="{http://soprabanking.com/amplitude}comparisonDecimal" minOccurs="0"/&gt;
 *         &lt;element name="date" type="{http://soprabanking.com/amplitude}comparisonDate" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comparisonAdditionalDataValue", propOrder = {
    "alphanumeric",
    "amountOrRate",
    "date"
})
public class ComparisonAdditionalDataValue {

    protected Designation alphanumeric;
    protected ComparisonDecimal amountOrRate;
    protected ComparisonDate date;

    /**
     * Obtient la valeur de la propriété alphanumeric.
     * 
     * @return
     *     possible object is
     *     {@link Designation }
     *     
     */
    public Designation getAlphanumeric() {
        return alphanumeric;
    }

    /**
     * Définit la valeur de la propriété alphanumeric.
     * 
     * @param value
     *     allowed object is
     *     {@link Designation }
     *     
     */
    public void setAlphanumeric(Designation value) {
        this.alphanumeric = value;
    }

    /**
     * Obtient la valeur de la propriété amountOrRate.
     * 
     * @return
     *     possible object is
     *     {@link ComparisonDecimal }
     *     
     */
    public ComparisonDecimal getAmountOrRate() {
        return amountOrRate;
    }

    /**
     * Définit la valeur de la propriété amountOrRate.
     * 
     * @param value
     *     allowed object is
     *     {@link ComparisonDecimal }
     *     
     */
    public void setAmountOrRate(ComparisonDecimal value) {
        this.amountOrRate = value;
    }

    /**
     * Obtient la valeur de la propriété date.
     * 
     * @return
     *     possible object is
     *     {@link ComparisonDate }
     *     
     */
    public ComparisonDate getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     * 
     * @param value
     *     allowed object is
     *     {@link ComparisonDate }
     *     
     */
    public void setDate(ComparisonDate value) {
        this.date = value;
    }

}
