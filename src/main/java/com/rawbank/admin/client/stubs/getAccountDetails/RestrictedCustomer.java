
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour restrictedCustomer complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="restrictedCustomer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="customerNumber" type="{http://soprabanking.com/amplitude}charMax15" minOccurs="0"/&gt;
 *         &lt;element name="displayedName" type="{http://soprabanking.com/amplitude}charMax67" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "restrictedCustomer", propOrder = {
    "customerNumber",
    "displayedName"
})
public class RestrictedCustomer {

    protected String customerNumber;
    protected String displayedName;

    /**
     * Obtient la valeur de la propriété customerNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerNumber() {
        return customerNumber;
    }

    /**
     * Définit la valeur de la propriété customerNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerNumber(String value) {
        this.customerNumber = value;
    }

    /**
     * Obtient la valeur de la propriété displayedName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayedName() {
        return displayedName;
    }

    /**
     * Définit la valeur de la propriété displayedName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayedName(String value) {
        this.displayedName = value;
    }

}
