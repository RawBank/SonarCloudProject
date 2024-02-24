
package com.rawbank.admin.client.stubs.getAccountList;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour amountCurrency complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="amountCurrency"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="currency" type="{http://soprabanking.com/amplitude}simpleCurrency"/&gt;
 *         &lt;element name="numberOfDecimals" type="{http://soprabanking.com/amplitude}integerBetween0And4" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "amountCurrency", propOrder = {
    "currency",
    "numberOfDecimals"
})
public class AmountCurrency {

    @XmlElement(required = true)
    protected SimpleCurrency currency;
    protected BigDecimal numberOfDecimals;

    /**
     * Obtient la valeur de la propriété currency.
     * 
     * @return
     *     possible object is
     *     {@link SimpleCurrency }
     *     
     */
    public SimpleCurrency getCurrency() {
        return currency;
    }

    /**
     * Définit la valeur de la propriété currency.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleCurrency }
     *     
     */
    public void setCurrency(SimpleCurrency value) {
        this.currency = value;
    }

    /**
     * Obtient la valeur de la propriété numberOfDecimals.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumberOfDecimals() {
        return numberOfDecimals;
    }

    /**
     * Définit la valeur de la propriété numberOfDecimals.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumberOfDecimals(BigDecimal value) {
        this.numberOfDecimals = value;
    }

}
