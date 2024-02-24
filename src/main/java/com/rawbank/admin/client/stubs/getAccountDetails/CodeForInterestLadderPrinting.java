
package com.rawbank.admin.client.stubs.getAccountDetails;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour codeForInterestLadderPrinting.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="codeForInterestLadderPrinting"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="D"/&gt;
 *     &lt;enumeration value="N"/&gt;
 *     &lt;enumeration value="S"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "codeForInterestLadderPrinting")
@XmlEnum
public enum CodeForInterestLadderPrinting {

    D,
    N,
    S;

    public String value() {
        return name();
    }

    public static CodeForInterestLadderPrinting fromValue(String v) {
        return valueOf(v);
    }

}
