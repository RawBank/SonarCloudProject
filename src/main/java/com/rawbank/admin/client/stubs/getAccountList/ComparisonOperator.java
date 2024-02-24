
package com.rawbank.admin.client.stubs.getAccountList;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour comparisonOperator.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="comparisonOperator"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CONTAINS"/&gt;
 *     &lt;enumeration value="ENDS WITH"/&gt;
 *     &lt;enumeration value="EQUALS"/&gt;
 *     &lt;enumeration value="STARTS WITH"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "comparisonOperator")
@XmlEnum
public enum ComparisonOperator {

    CONTAINS("CONTAINS"),
    @XmlEnumValue("ENDS WITH")
    ENDS_WITH("ENDS WITH"),
    EQUALS("EQUALS"),
    @XmlEnumValue("STARTS WITH")
    STARTS_WITH("STARTS WITH");
    private final String value;

    ComparisonOperator(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ComparisonOperator fromValue(String v) {
        for (ComparisonOperator c: ComparisonOperator.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
