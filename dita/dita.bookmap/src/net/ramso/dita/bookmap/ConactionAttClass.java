
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for conaction-att.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="conaction-att.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="mark"/>
 *     &lt;enumeration value="pushafter"/>
 *     &lt;enumeration value="pushbefore"/>
 *     &lt;enumeration value="pushreplace"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "conaction-att.class")
@XmlEnum
public enum ConactionAttClass  {

    @XmlEnumValue("mark")
    MARK("mark"),
    @XmlEnumValue("pushafter")
    PUSHAFTER("pushafter"),
    @XmlEnumValue("pushbefore")
    PUSHBEFORE("pushbefore"),
    @XmlEnumValue("pushreplace")
    PUSHREPLACE("pushreplace"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    ConactionAttClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConactionAttClass fromValue(String v) {
        for (ConactionAttClass c: ConactionAttClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
