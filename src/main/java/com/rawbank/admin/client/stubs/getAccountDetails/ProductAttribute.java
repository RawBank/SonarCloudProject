
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour productAttribute.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="productAttribute"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ACCOUNT PRODUCT"/&gt;
 *     &lt;enumeration value="FILE PRODUCT"/&gt;
 *     &lt;enumeration value="SERVICE PRODUCT"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "productAttribute")
@XmlEnum
public enum ProductAttribute {

    @XmlEnumValue("ACCOUNT PRODUCT")
    ACCOUNT_PRODUCT("ACCOUNT PRODUCT"),
    @XmlEnumValue("FILE PRODUCT")
    FILE_PRODUCT("FILE PRODUCT"),
    @XmlEnumValue("SERVICE PRODUCT")
    SERVICE_PRODUCT("SERVICE PRODUCT");
    private final String value;

    ProductAttribute(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProductAttribute fromValue(String v) {
        for (ProductAttribute c: ProductAttribute.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
