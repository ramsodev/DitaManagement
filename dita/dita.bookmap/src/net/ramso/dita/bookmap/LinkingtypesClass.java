
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for linkingtypes.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="linkingtypes.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="targetonly"/>
 *     &lt;enumeration value="sourceonly"/>
 *     &lt;enumeration value="normal"/>
 *     &lt;enumeration value="none"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "linkingtypes.class")
@XmlEnum
public enum LinkingtypesClass {

    @XmlEnumValue("targetonly")
    TARGETONLY("targetonly"),
    @XmlEnumValue("sourceonly")
    SOURCEONLY("sourceonly"),
    @XmlEnumValue("normal")
    NORMAL("normal"),
    @XmlEnumValue("none")
    NONE("none"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    LinkingtypesClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LinkingtypesClass fromValue(String v) {
        for (LinkingtypesClass c: LinkingtypesClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
