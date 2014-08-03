
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for processing-role-att.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="processing-role-att.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="normal"/>
 *     &lt;enumeration value="resource-only"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "processing-role-att.class")
@XmlEnum
public enum ProcessingRoleAttClass {

    @XmlEnumValue("normal")
    NORMAL("normal"),
    @XmlEnumValue("resource-only")
    RESOURCE_ONLY("resource-only"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    ProcessingRoleAttClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProcessingRoleAttClass fromValue(String v) {
        for (ProcessingRoleAttClass c: ProcessingRoleAttClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
