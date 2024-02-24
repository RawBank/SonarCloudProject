
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getAccountDetailRequest complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getAccountDetailRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accountIdentifier" type="{http://soprabanking.com/amplitude}internalFormatSimpleAccountOurBranch"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAccountDetailRequest", propOrder = {
    "accountIdentifier"
})
public class GetAccountDetailRequest {

    @XmlElement(required = true)
    protected InternalFormatSimpleAccountOurBranch accountIdentifier;

    /**
     * Obtient la valeur de la propriété accountIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link InternalFormatSimpleAccountOurBranch }
     *     
     */
    public InternalFormatSimpleAccountOurBranch getAccountIdentifier() {
        return accountIdentifier;
    }

    /**
     * Définit la valeur de la propriété accountIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link InternalFormatSimpleAccountOurBranch }
     *     
     */
    public void setAccountIdentifier(InternalFormatSimpleAccountOurBranch value) {
        this.accountIdentifier = value;
    }

}
