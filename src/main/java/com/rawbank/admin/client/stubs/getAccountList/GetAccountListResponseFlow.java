
package com.rawbank.admin.client.stubs.getAccountList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getAccountListResponseFlow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getAccountListResponseFlow"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="responseHeader" type="{http://soprabanking.com/amplitude}responseHeader"/&gt;
 *         &lt;element name="responseStatus" type="{http://soprabanking.com/amplitude}responseStatus"/&gt;
 *         &lt;element name="getAccountListResponse" type="{http://soprabanking.com/amplitude}getAccountListResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAccountListResponseFlow", propOrder = {
    "responseHeader",
    "responseStatus",
    "getAccountListResponse"
})
public class GetAccountListResponseFlow {

    @XmlElement(required = true)
    protected ResponseHeader responseHeader;
    @XmlElement(required = true)
    protected ResponseStatus responseStatus;
    protected GetAccountListResponse getAccountListResponse;

    /**
     * Obtient la valeur de la propriété responseHeader.
     * 
     * @return
     *     possible object is
     *     {@link ResponseHeader }
     *     
     */
    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    /**
     * Définit la valeur de la propriété responseHeader.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseHeader }
     *     
     */
    public void setResponseHeader(ResponseHeader value) {
        this.responseHeader = value;
    }

    /**
     * Obtient la valeur de la propriété responseStatus.
     * 
     * @return
     *     possible object is
     *     {@link ResponseStatus }
     *     
     */
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    /**
     * Définit la valeur de la propriété responseStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseStatus }
     *     
     */
    public void setResponseStatus(ResponseStatus value) {
        this.responseStatus = value;
    }

    /**
     * Obtient la valeur de la propriété getAccountListResponse.
     * 
     * @return
     *     possible object is
     *     {@link GetAccountListResponse }
     *     
     */
    public GetAccountListResponse getGetAccountListResponse() {
        return getAccountListResponse;
    }

    /**
     * Définit la valeur de la propriété getAccountListResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link GetAccountListResponse }
     *     
     */
    public void setGetAccountListResponse(GetAccountListResponse value) {
        this.getAccountListResponse = value;
    }

}
