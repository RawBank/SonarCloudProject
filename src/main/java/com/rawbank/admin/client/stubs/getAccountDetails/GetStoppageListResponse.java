
package com.rawbank.admin.client.stubs.getAccountDetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getStoppageListResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getStoppageListResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="stoppage" type="{http://soprabanking.com/amplitude}getStoppageResponse" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStoppageListResponse", propOrder = {
    "stoppage"
})
public class GetStoppageListResponse {

    protected List<GetStoppageResponse> stoppage;

    /**
     * Gets the value of the stoppage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stoppage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStoppage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetStoppageResponse }
     * 
     * 
     */
    public List<GetStoppageResponse> getStoppage() {
        if (stoppage == null) {
            stoppage = new ArrayList<GetStoppageResponse>();
        }
        return this.stoppage;
    }

}
