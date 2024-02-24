
package com.rawbank.admin.client.stubs.getAccountList;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour comparisonAmount complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="comparisonAmount"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="amount1" type="{http://soprabanking.com/amplitude}decimal19_4"/&gt;
 *         &lt;element name="amount2" type="{http://soprabanking.com/amplitude}decimal19_4" minOccurs="0"/&gt;
 *         &lt;element name="currency" type="{http://soprabanking.com/amplitude}amountCurrency" minOccurs="0"/&gt;
 *         &lt;element name="comparator" type="{http://soprabanking.com/amplitude}comparator"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comparisonAmount", propOrder = {
    "amount1",
    "amount2",
    "currency",
    "comparator"
})
public class ComparisonAmount {

    @XmlElement(required = true)
    protected BigDecimal amount1;
    protected BigDecimal amount2;
    protected AmountCurrency currency;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Comparator comparator;

    /**
     * Obtient la valeur de la propriété amount1.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount1() {
        return amount1;
    }

    /**
     * Définit la valeur de la propriété amount1.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount1(BigDecimal value) {
        this.amount1 = value;
    }

    /**
     * Obtient la valeur de la propriété amount2.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount2() {
        return amount2;
    }

    /**
     * Définit la valeur de la propriété amount2.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount2(BigDecimal value) {
        this.amount2 = value;
    }

    /**
     * Obtient la valeur de la propriété currency.
     * 
     * @return
     *     possible object is
     *     {@link AmountCurrency }
     *     
     */
    public AmountCurrency getCurrency() {
        return currency;
    }

    /**
     * Définit la valeur de la propriété currency.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountCurrency }
     *     
     */
    public void setCurrency(AmountCurrency value) {
        this.currency = value;
    }

    /**
     * Obtient la valeur de la propriété comparator.
     * 
     * @return
     *     possible object is
     *     {@link Comparator }
     *     
     */
    public Comparator getComparator() {
        return comparator;
    }

    /**
     * Définit la valeur de la propriété comparator.
     * 
     * @param value
     *     allowed object is
     *     {@link Comparator }
     *     
     */
    public void setComparator(Comparator value) {
        this.comparator = value;
    }

}
