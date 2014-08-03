
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for type-tmtype-att.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="type-tmtype-att.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="tm"/>
 *     &lt;enumeration value="reg"/>
 *     &lt;enumeration value="service"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "type-tmtype-att.class")
@XmlEnum
public enum TypeTmtypeAttClass {

    @XmlEnumValue("tm")
    TM("tm"),
    @XmlEnumValue("reg")
    REG("reg"),
    @XmlEnumValue("service")
    SERVICE("service"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    TypeTmtypeAttClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeTmtypeAttClass fromValue(String v) {
        for (TypeTmtypeAttClass c: TypeTmtypeAttClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
