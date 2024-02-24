
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getAccountDetailRequestFlow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getAccountDetailRequestFlow"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="requestHeader" type="{http://soprabanking.com/amplitude}requestHeader"/&gt;
 *         &lt;element name="getAccountDetailRequest" type="{http://soprabanking.com/amplitude}getAccountDetailRequest"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAccountDetailRequestFlow", propOrder = {
    "requestHeader",
    "getAccountDetailRequest"
})
public class GetAccountDetailRequestFlow {

    @XmlElement(required = true)
    protected RequestHeader requestHeader;
    @XmlElement(required = true)
    protected GetAccountDetailRequest getAccountDetailRequest;

    /**
     * Obtient la valeur de la propriété requestHeader.
     * 
     * @return
     *     possible object is
     *     {@link RequestHeader }
     *     
     */
    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    /**
     * Définit la valeur de la propriété requestHeader.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestHeader }
     *     
     */
    public void setRequestHeader(RequestHeader value) {
        this.requestHeader = value;
    }

    /**
     * Obtient la valeur de la propriété getAccountDetailRequest.
     * 
     * @return
     *     possible object is
     *     {@link GetAccountDetailRequest }
     *     
     */
    public GetAccountDetailRequest getGetAccountDetailRequest() {
        return getAccountDetailRequest;
    }

    /**
     * Définit la valeur de la propriété getAccountDetailRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link GetAccountDetailRequest }
     *     
     */
    public void setGetAccountDetailRequest(GetAccountDetailRequest value) {
        this.getAccountDetailRequest = value;
    }

}
