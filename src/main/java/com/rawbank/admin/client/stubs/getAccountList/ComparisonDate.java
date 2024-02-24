
package com.rawbank.admin.client.stubs.getAccountList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour comparisonDate complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="comparisonDate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="date1" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="date2" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
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
@XmlType(name = "comparisonDate", propOrder = {
    "date1",
    "date2",
    "comparator"
})
public class ComparisonDate {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date1;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date2;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Comparator comparator;

    /**
     * Obtient la valeur de la propriété date1.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate1() {
        return date1;
    }

    /**
     * Définit la valeur de la propriété date1.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate1(XMLGregorianCalendar value) {
        this.date1 = value;
    }

    /**
     * Obtient la valeur de la propriété date2.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate2() {
        return date2;
    }

    /**
     * Définit la valeur de la propriété date2.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate2(XMLGregorianCalendar value) {
        this.date2 = value;
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
