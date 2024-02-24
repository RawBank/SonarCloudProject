
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour accountFreeAttributeInfo complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="accountFreeAttributeInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="code" type="{http://soprabanking.com/amplitude}charMax10" minOccurs="0"/&gt;
 *         &lt;element name="designation" type="{http://soprabanking.com/amplitude}charMax30" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://soprabanking.com/amplitude}accountFreeAttributeType" minOccurs="0"/&gt;
 *         &lt;element name="dataFormat" type="{http://soprabanking.com/amplitude}freeAttributeFormat" minOccurs="0"/&gt;
 *         &lt;element name="mandatoryField" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="managementMethod" type="{http://soprabanking.com/amplitude}additionalDataManagementMethod" minOccurs="0"/&gt;
 *         &lt;element name="maxLength" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="numberOfDecimals" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="separator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="existenceControl" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="displayOrder" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountFreeAttributeInfo", propOrder = {
    "code",
    "designation",
    "type",
    "dataFormat",
    "mandatoryField",
    "managementMethod",
    "maxLength",
    "numberOfDecimals",
    "separator",
    "existenceControl",
    "displayOrder"
})
public class AccountFreeAttributeInfo {

    protected String code;
    protected String designation;
    @XmlSchemaType(name = "string")
    protected AccountFreeAttributeType type;
    @XmlSchemaType(name = "string")
    protected FreeAttributeFormat dataFormat;
    protected Boolean mandatoryField;
    protected String managementMethod;
    protected Integer maxLength;
    protected Integer numberOfDecimals;
    protected Boolean separator;
    protected Integer existenceControl;
    protected Integer displayOrder;

    /**
     * Obtient la valeur de la propriété code.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Définit la valeur de la propriété code.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Obtient la valeur de la propriété designation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Définit la valeur de la propriété designation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesignation(String value) {
        this.designation = value;
    }

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link AccountFreeAttributeType }
     *     
     */
    public AccountFreeAttributeType getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountFreeAttributeType }
     *     
     */
    public void setType(AccountFreeAttributeType value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété dataFormat.
     * 
     * @return
     *     possible object is
     *     {@link FreeAttributeFormat }
     *     
     */
    public FreeAttributeFormat getDataFormat() {
        return dataFormat;
    }

    /**
     * Définit la valeur de la propriété dataFormat.
     * 
     * @param value
     *     allowed object is
     *     {@link FreeAttributeFormat }
     *     
     */
    public void setDataFormat(FreeAttributeFormat value) {
        this.dataFormat = value;
    }

    /**
     * Obtient la valeur de la propriété mandatoryField.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMandatoryField() {
        return mandatoryField;
    }

    /**
     * Définit la valeur de la propriété mandatoryField.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMandatoryField(Boolean value) {
        this.mandatoryField = value;
    }

    /**
     * Obtient la valeur de la propriété managementMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManagementMethod() {
        return managementMethod;
    }

    /**
     * Définit la valeur de la propriété managementMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManagementMethod(String value) {
        this.managementMethod = value;
    }

    /**
     * Obtient la valeur de la propriété maxLength.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxLength() {
        return maxLength;
    }

    /**
     * Définit la valeur de la propriété maxLength.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxLength(Integer value) {
        this.maxLength = value;
    }

    /**
     * Obtient la valeur de la propriété numberOfDecimals.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfDecimals() {
        return numberOfDecimals;
    }

    /**
     * Définit la valeur de la propriété numberOfDecimals.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfDecimals(Integer value) {
        this.numberOfDecimals = value;
    }

    /**
     * Obtient la valeur de la propriété separator.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSeparator() {
        return separator;
    }

    /**
     * Définit la valeur de la propriété separator.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSeparator(Boolean value) {
        this.separator = value;
    }

    /**
     * Obtient la valeur de la propriété existenceControl.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExistenceControl() {
        return existenceControl;
    }

    /**
     * Définit la valeur de la propriété existenceControl.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExistenceControl(Integer value) {
        this.existenceControl = value;
    }

    /**
     * Obtient la valeur de la propriété displayOrder.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * Définit la valeur de la propriété displayOrder.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDisplayOrder(Integer value) {
        this.displayOrder = value;
    }

}
