
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getAccountDetailResponseFlow complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getAccountDetailResponseFlow"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="responseHeader" type="{http://soprabanking.com/amplitude}responseHeader"/&gt;
 *         &lt;element name="responseStatus" type="{http://soprabanking.com/amplitude}responseStatus"/&gt;
 *         &lt;element name="getAccountDetailResponse" type="{http://soprabanking.com/amplitude}getAccountDetailResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAccountDetailResponseFlow", propOrder = {
    "responseHeader",
    "responseStatus",
    "getAccountDetailResponse"
})
public class GetAccountDetailResponseFlow {

    @XmlElement(required = true)
    protected ResponseHeader responseHeader;
    @XmlElement(required = true)
    protected ResponseStatus responseStatus;
    protected GetAccountDetailResponse getAccountDetailResponse;

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
     * Obtient la valeur de la propriété getAccountDetailResponse.
     * 
     * @return
     *     possible object is
     *     {@link GetAccountDetailResponse }
     *     
     */
    public GetAccountDetailResponse getGetAccountDetailResponse() {
        return getAccountDetailResponse;
    }

    /**
     * Définit la valeur de la propriété getAccountDetailResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link GetAccountDetailResponse }
     *     
     */
    public void setGetAccountDetailResponse(GetAccountDetailResponse value) {
        this.getAccountDetailResponse = value;
    }

}
