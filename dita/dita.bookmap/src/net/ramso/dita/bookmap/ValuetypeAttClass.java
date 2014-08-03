
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for valuetype-att.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="valuetype-att.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="data"/>
 *     &lt;enumeration value="ref"/>
 *     &lt;enumeration value="object"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "valuetype-att.class")
@XmlEnum
public enum ValuetypeAttClass {

    @XmlEnumValue("data")
    DATA("data"),
    @XmlEnumValue("ref")
    REF("ref"),
    @XmlEnumValue("object")
    OBJECT("object"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    ValuetypeAttClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ValuetypeAttClass fromValue(String v) {
        for (ValuetypeAttClass c: ValuetypeAttClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
