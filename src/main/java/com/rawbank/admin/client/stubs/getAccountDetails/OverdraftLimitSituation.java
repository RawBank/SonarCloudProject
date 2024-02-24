
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour overdraftLimitSituation.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="overdraftLimitSituation"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;length value="1"/&gt;
 *     &lt;enumeration value="A"/&gt;
 *     &lt;enumeration value="M"/&gt;
 *     &lt;enumeration value="O"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "overdraftLimitSituation")
@XmlEnum
public enum OverdraftLimitSituation {

    A,
    M,
    O;

    public String value() {
        return name();
    }

    public static OverdraftLimitSituation fromValue(String v) {
        return valueOf(v);
    }

}
