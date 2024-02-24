
package com.rawbank.admin.client.stubs.getAccountList;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour responseStatusMessage complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="responseStatusMessage"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nature" type="{http://soprabanking.com/amplitude}responseMessageNature"/&gt;
 *         &lt;element name="code" type="{http://soprabanking.com/amplitude}char6"/&gt;
 *         &lt;element name="line" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="errorInformation" type="{http://soprabanking.com/amplitude}errorInformation" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseStatusMessage", propOrder = {
    "nature",
    "code",
    "line",
    "errorInformation"
})
public class ResponseStatusMessage {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ResponseMessageNature nature;
    @XmlElement(required = true)
    protected String code;
    protected List<String> line;
    protected ErrorInformation errorInformation;

    /**
     * Obtient la valeur de la propriété nature.
     * 
     * @return
     *     possible object is
     *     {@link ResponseMessageNature }
     *     
     */
    public ResponseMessageNature getNature() {
        return nature;
    }

    /**
     * Définit la valeur de la propriété nature.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseMessageNature }
     *     
     */
    public void setNature(ResponseMessageNature value) {
        this.nature = value;
    }

    /**
     * Obtient la valeur de la propriété code.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Définit la valeur de la propriété code.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the line property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the line property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLine() {
        if (line == null) {
            line = new ArrayList<String>();
        }
        return this.line;
    }

    /**
     * Obtient la valeur de la propriété errorInformation.
     * 
     * @return
     *     possible object is
     *     {@link ErrorInformation }
     *     
     */
    public ErrorInformation getErrorInformation() {
        return errorInformation;
    }

    /**
     * Définit la valeur de la propriété errorInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorInformation }
     *     
     */
    public void setErrorInformation(ErrorInformation value) {
        this.errorInformation = value;
    }

}
