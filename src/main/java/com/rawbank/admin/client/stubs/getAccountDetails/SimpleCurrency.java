
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour simpleCurrency complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="simpleCurrency"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="alphaCode" type="{http://soprabanking.com/amplitude}char3" minOccurs="0"/&gt;
 *         &lt;element name="numericCode" type="{http://soprabanking.com/amplitude}char3" minOccurs="0"/&gt;
 *         &lt;element name="designation" type="{http://soprabanking.com/amplitude}charMax30" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "simpleCurrency", propOrder = {
    "alphaCode",
    "numericCode",
    "designation"
})
public class SimpleCurrency {

    protected String alphaCode;
    protected String numericCode;
    protected String designation;

    /**
     * Obtient la valeur de la propriété alphaCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlphaCode() {
        return alphaCode;
    }

    /**
     * Définit la valeur de la propriété alphaCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlphaCode(String value) {
        this.alphaCode = value;
    }

    /**
     * Obtient la valeur de la propriété numericCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumericCode() {
        return numericCode;
    }

    /**
     * Définit la valeur de la propriété numericCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumericCode(String value) {
        this.numericCode = value;
    }

    /**
     * Obtient la valeur de la propriété designation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Définit la valeur de la propriété designation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesignation(String value) {
        this.designation = value;
    }

}
