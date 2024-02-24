
package com.rawbank.admin.client.stubs.getAccountList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getAccountListRequestFlow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getAccountListRequestFlow"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="requestHeader" type="{http://soprabanking.com/amplitude}requestHeader"/&gt;
 *         &lt;element name="getAccountListRequest" type="{http://soprabanking.com/amplitude}getAccountListRequest"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAccountListRequestFlow", propOrder = {
    "requestHeader",
    "getAccountListRequest"
})
public class GetAccountListRequestFlow {

    @XmlElement(required = true)
    protected RequestHeader requestHeader;
    @XmlElement(required = true)
    protected GetAccountListRequest getAccountListRequest;

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
     * Obtient la valeur de la propriété getAccountListRequest.
     * 
     * @return
     *     possible object is
     *     {@link GetAccountListRequest }
     *     
     */
    public GetAccountListRequest getGetAccountListRequest() {
        return getAccountListRequest;
    }

    /**
     * Définit la valeur de la propriété getAccountListRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link GetAccountListRequest }
     *     
     */
    public void setGetAccountListRequest(GetAccountListRequest value) {
        this.getAccountListRequest = value;
    }

}
