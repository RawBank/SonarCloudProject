
package com.rawbank.admin.client.stubs.getAccountDetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getAccountFreeAttributesDetailResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getAccountFreeAttributesDetailResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accountFreeAttributeDetail" type="{http://soprabanking.com/amplitude}accountFreeAttributeDetail" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAccountFreeAttributesDetailResponse", propOrder = {
    "accountFreeAttributeDetail"
})
public class GetAccountFreeAttributesDetailResponse {

    protected List<AccountFreeAttributeDetail> accountFreeAttributeDetail;

    /**
     * Gets the value of the accountFreeAttributeDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accountFreeAttributeDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccountFreeAttributeDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountFreeAttributeDetail }
     * 
     * 
     */
    public List<AccountFreeAttributeDetail> getAccountFreeAttributeDetail() {
        if (accountFreeAttributeDetail == null) {
            accountFreeAttributeDetail = new ArrayList<AccountFreeAttributeDetail>();
        }
        return this.accountFreeAttributeDetail;
    }

}
