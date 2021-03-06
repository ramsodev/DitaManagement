
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for importance-atts.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="importance-atts.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="obsolete"/>
 *     &lt;enumeration value="deprecated"/>
 *     &lt;enumeration value="optional"/>
 *     &lt;enumeration value="default"/>
 *     &lt;enumeration value="low"/>
 *     &lt;enumeration value="normal"/>
 *     &lt;enumeration value="high"/>
 *     &lt;enumeration value="recommended"/>
 *     &lt;enumeration value="required"/>
 *     &lt;enumeration value="urgent"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "importance-atts.class")
@XmlEnum
public enum ImportanceAttsClass  {

    @XmlEnumValue("obsolete")
    OBSOLETE("obsolete"),
    @XmlEnumValue("deprecated")
    DEPRECATED("deprecated"),
    @XmlEnumValue("optional")
    OPTIONAL("optional"),
    @XmlEnumValue("default")
    DEFAULT("default"),
    @XmlEnumValue("low")
    LOW("low"),
    @XmlEnumValue("normal")
    NORMAL("normal"),
    @XmlEnumValue("high")
    HIGH("high"),
    @XmlEnumValue("recommended")
    RECOMMENDED("recommended"),
    @XmlEnumValue("required")
    REQUIRED("required"),
    @XmlEnumValue("urgent")
    URGENT("urgent"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    ImportanceAttsClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ImportanceAttsClass fromValue(String v) {
        for (ImportanceAttsClass c: ImportanceAttsClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
