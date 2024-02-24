
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour accountIdentifierOurBranch complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="accountIdentifierOurBranch"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="internalFormatAccountOurBranch" type="{http://soprabanking.com/amplitude}internalFormatAccountOurBranch" minOccurs="0"/&gt;
 *         &lt;element name="ibanFormatAccount" type="{http://soprabanking.com/amplitude}ibanFormatAccount" minOccurs="0"/&gt;
 *         &lt;element name="externalFormatAccount" type="{http://soprabanking.com/amplitude}externalFormatAccount" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountIdentifierOurBranch", propOrder = {
    "internalFormatAccountOurBranch",
    "ibanFormatAccount",
    "externalFormatAccount"
})
public class AccountIdentifierOurBranch {

    protected InternalFormatAccountOurBranch internalFormatAccountOurBranch;
    protected IbanFormatAccount ibanFormatAccount;
    protected ExternalFormatAccount externalFormatAccount;

    /**
     * Obtient la valeur de la propriété internalFormatAccountOurBranch.
     * 
     * @return
     *     possible object is
     *     {@link InternalFormatAccountOurBranch }
     *     
     */
    public InternalFormatAccountOurBranch getInternalFormatAccountOurBranch() {
        return internalFormatAccountOurBranch;
    }

    /**
     * Définit la valeur de la propriété internalFormatAccountOurBranch.
     * 
     * @param value
     *     allowed object is
     *     {@link InternalFormatAccountOurBranch }
     *     
     */
    public void setInternalFormatAccountOurBranch(InternalFormatAccountOurBranch value) {
        this.internalFormatAccountOurBranch = value;
    }

    /**
     * Obtient la valeur de la propriété ibanFormatAccount.
     * 
     * @return
     *     possible object is
     *     {@link IbanFormatAccount }
     *     
     */
    public IbanFormatAccount getIbanFormatAccount() {
        return ibanFormatAccount;
    }

    /**
     * Définit la valeur de la propriété ibanFormatAccount.
     * 
     * @param value
     *     allowed object is
     *     {@link IbanFormatAccount }
     *     
     */
    public void setIbanFormatAccount(IbanFormatAccount value) {
        this.ibanFormatAccount = value;
    }

    /**
     * Obtient la valeur de la propriété externalFormatAccount.
     * 
     * @return
     *     possible object is
     *     {@link ExternalFormatAccount }
     *     
     */
    public ExternalFormatAccount getExternalFormatAccount() {
        return externalFormatAccount;
    }

    /**
     * Définit la valeur de la propriété externalFormatAccount.
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalFormatAccount }
     *     
     */
    public void setExternalFormatAccount(ExternalFormatAccount value) {
        this.externalFormatAccount = value;
    }

}
