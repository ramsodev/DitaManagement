
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for frame-att.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="frame-att.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="top"/>
 *     &lt;enumeration value="bottom"/>
 *     &lt;enumeration value="topbot"/>
 *     &lt;enumeration value="all"/>
 *     &lt;enumeration value="sides"/>
 *     &lt;enumeration value="none"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "frame-att.class")
@XmlEnum
public enum FrameAttClass {

    @XmlEnumValue("top")
    TOP("top"),
    @XmlEnumValue("bottom")
    BOTTOM("bottom"),
    @XmlEnumValue("topbot")
    TOPBOT("topbot"),
    @XmlEnumValue("all")
    ALL("all"),
    @XmlEnumValue("sides")
    SIDES("sides"),
    @XmlEnumValue("none")
    NONE("none"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    FrameAttClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FrameAttClass fromValue(String v) {
        for (FrameAttClass c: FrameAttClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
