
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour responseStatus complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="responseStatus"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="statusCode" type="{http://soprabanking.com/amplitude}statusCodeType"/&gt;
 *         &lt;element name="messages" type="{http://soprabanking.com/amplitude}responseStatusMessages" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseStatus", propOrder = {
    "statusCode",
    "messages"
})
public class ResponseStatus {

    @XmlElement(required = true)
    protected String statusCode;
    protected ResponseStatusMessages messages;

    /**
     * Obtient la valeur de la propriété statusCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Définit la valeur de la propriété statusCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Obtient la valeur de la propriété messages.
     * 
     * @return
     *     possible object is
     *     {@link ResponseStatusMessages }
     *     
     */
    public ResponseStatusMessages getMessages() {
        return messages;
    }

    /**
     * Définit la valeur de la propriété messages.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseStatusMessages }
     *     
     */
    public void setMessages(ResponseStatusMessages value) {
        this.messages = value;
    }

}
