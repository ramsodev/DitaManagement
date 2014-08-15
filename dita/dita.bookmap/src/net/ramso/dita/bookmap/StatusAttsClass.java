
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for status-atts.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="status-atts.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="new"/>
 *     &lt;enumeration value="changed"/>
 *     &lt;enumeration value="deleted"/>
 *     &lt;enumeration value="unchanged"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "status-atts.class")
@XmlEnum
public enum StatusAttsClass  {

    @XmlEnumValue("new")
    NEW("new"),
    @XmlEnumValue("changed")
    CHANGED("changed"),
    @XmlEnumValue("deleted")
    DELETED("deleted"),
    @XmlEnumValue("unchanged")
    UNCHANGED("unchanged"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    StatusAttsClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusAttsClass fromValue(String v) {
        for (StatusAttsClass c: StatusAttsClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
