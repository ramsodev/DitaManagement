
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for align-att.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="align-att.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="left"/>
 *     &lt;enumeration value="right"/>
 *     &lt;enumeration value="center"/>
 *     &lt;enumeration value="justify"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "align-att.class")
@XmlEnum
public enum AlignAttClass {

    @XmlEnumValue("left")
    LEFT("left"),
    @XmlEnumValue("right")
    RIGHT("right"),
    @XmlEnumValue("center")
    CENTER("center"),
    @XmlEnumValue("justify")
    JUSTIFY("justify"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    AlignAttClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AlignAttClass fromValue(String v) {
        for (AlignAttClass c: AlignAttClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
