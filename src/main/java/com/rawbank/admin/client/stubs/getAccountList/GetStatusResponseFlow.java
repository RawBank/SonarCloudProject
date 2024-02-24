
package com.rawbank.admin.client.stubs.getAccountList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getStatusResponseFlow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getStatusResponseFlow"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getStatusResponse" type="{http://soprabanking.com/amplitude}getStatusResponse"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStatusResponseFlow", propOrder = {
    "getStatusResponse"
})
public class GetStatusResponseFlow {

    @XmlElement(required = true)
    protected GetStatusResponse getStatusResponse;

    /**
     * Obtient la valeur de la propriété getStatusResponse.
     * 
     * @return
     *     possible object is
     *     {@link GetStatusResponse }
     *     
     */
    public GetStatusResponse getGetStatusResponse() {
        return getStatusResponse;
    }

    /**
     * Définit la valeur de la propriété getStatusResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link GetStatusResponse }
     *     
     */
    public void setGetStatusResponse(GetStatusResponse value) {
        this.getStatusResponse = value;
    }

}
