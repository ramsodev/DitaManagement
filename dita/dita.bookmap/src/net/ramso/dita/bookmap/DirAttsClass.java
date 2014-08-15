
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dir-atts.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="dir-atts.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ltr"/>
 *     &lt;enumeration value="rtl"/>
 *     &lt;enumeration value="lro"/>
 *     &lt;enumeration value="rlo"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "dir-atts.class")
@XmlEnum
public enum DirAttsClass  {

    @XmlEnumValue("ltr")
    LTR("ltr"),
    @XmlEnumValue("rtl")
    RTL("rtl"),
    @XmlEnumValue("lro")
    LRO("lro"),
    @XmlEnumValue("rlo")
    RLO("rlo"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    DirAttsClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DirAttsClass fromValue(String v) {
        for (DirAttsClass c: DirAttsClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
