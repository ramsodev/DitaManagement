
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for role-att.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="role-att.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="parent"/>
 *     &lt;enumeration value="child"/>
 *     &lt;enumeration value="sibling"/>
 *     &lt;enumeration value="friend"/>
 *     &lt;enumeration value="next"/>
 *     &lt;enumeration value="previous"/>
 *     &lt;enumeration value="cousin"/>
 *     &lt;enumeration value="ancestor"/>
 *     &lt;enumeration value="descendant"/>
 *     &lt;enumeration value="sample"/>
 *     &lt;enumeration value="external"/>
 *     &lt;enumeration value="other"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "role-att.class")
@XmlEnum
public enum RoleAttClass {

    @XmlEnumValue("parent")
    PARENT("parent"),
    @XmlEnumValue("child")
    CHILD("child"),
    @XmlEnumValue("sibling")
    SIBLING("sibling"),
    @XmlEnumValue("friend")
    FRIEND("friend"),
    @XmlEnumValue("next")
    NEXT("next"),
    @XmlEnumValue("previous")
    PREVIOUS("previous"),
    @XmlEnumValue("cousin")
    COUSIN("cousin"),
    @XmlEnumValue("ancestor")
    ANCESTOR("ancestor"),
    @XmlEnumValue("descendant")
    DESCENDANT("descendant"),
    @XmlEnumValue("sample")
    SAMPLE("sample"),
    @XmlEnumValue("external")
    EXTERNAL("external"),
    @XmlEnumValue("other")
    OTHER("other"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    RoleAttClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RoleAttClass fromValue(String v) {
        for (RoleAttClass c: RoleAttClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
