
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for image-placement-att.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="image-placement-att.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="inline"/>
 *     &lt;enumeration value="break"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "image-placement-att.class")
@XmlEnum
public enum ImagePlacementAttClass {

    @XmlEnumValue("inline")
    INLINE("inline"),
    @XmlEnumValue("break")
    BREAK("break"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    ImagePlacementAttClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ImagePlacementAttClass fromValue(String v) {
        for (ImagePlacementAttClass c: ImagePlacementAttClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
