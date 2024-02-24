
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour accountFreeAttributeDetail complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="accountFreeAttributeDetail"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accountFreeAttributeIdentifier" type="{http://soprabanking.com/amplitude}accountFreeAttributeDetailIdentifer" minOccurs="0"/&gt;
 *         &lt;element name="additionalDataValue" type="{http://soprabanking.com/amplitude}additionalDataValue" minOccurs="0"/&gt;
 *         &lt;element name="accountFreeAttributeInfo" type="{http://soprabanking.com/amplitude}accountFreeAttributeInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountFreeAttributeDetail", propOrder = {
    "accountFreeAttributeIdentifier",
    "additionalDataValue",
    "accountFreeAttributeInfo"
})
public class AccountFreeAttributeDetail {

    protected AccountFreeAttributeDetailIdentifer accountFreeAttributeIdentifier;
    protected AdditionalDataValue additionalDataValue;
    protected AccountFreeAttributeInfo accountFreeAttributeInfo;

    /**
     * Obtient la valeur de la propriété accountFreeAttributeIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link AccountFreeAttributeDetailIdentifer }
     *     
     */
    public AccountFreeAttributeDetailIdentifer getAccountFreeAttributeIdentifier() {
        return accountFreeAttributeIdentifier;
    }

    /**
     * Définit la valeur de la propriété accountFreeAttributeIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountFreeAttributeDetailIdentifer }
     *     
     */
    public void setAccountFreeAttributeIdentifier(AccountFreeAttributeDetailIdentifer value) {
        this.accountFreeAttributeIdentifier = value;
    }

    /**
     * Obtient la valeur de la propriété additionalDataValue.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalDataValue }
     *     
     */
    public AdditionalDataValue getAdditionalDataValue() {
        return additionalDataValue;
    }

    /**
     * Définit la valeur de la propriété additionalDataValue.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalDataValue }
     *     
     */
    public void setAdditionalDataValue(AdditionalDataValue value) {
        this.additionalDataValue = value;
    }

    /**
     * Obtient la valeur de la propriété accountFreeAttributeInfo.
     * 
     * @return
     *     possible object is
     *     {@link AccountFreeAttributeInfo }
     *     
     */
    public AccountFreeAttributeInfo getAccountFreeAttributeInfo() {
        return accountFreeAttributeInfo;
    }

    /**
     * Définit la valeur de la propriété accountFreeAttributeInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountFreeAttributeInfo }
     *     
     */
    public void setAccountFreeAttributeInfo(AccountFreeAttributeInfo value) {
        this.accountFreeAttributeInfo = value;
    }

}
