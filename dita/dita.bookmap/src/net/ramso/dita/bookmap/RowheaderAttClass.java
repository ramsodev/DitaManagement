
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rowheader-att.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="rowheader-att.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="firstcol"/>
 *     &lt;enumeration value="norowheader"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "rowheader-att.class")
@XmlEnum
public enum RowheaderAttClass  {

    @XmlEnumValue("firstcol")
    FIRSTCOL("firstcol"),
    @XmlEnumValue("norowheader")
    NOROWHEADER("norowheader"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    RowheaderAttClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RowheaderAttClass fromValue(String v) {
        for (RowheaderAttClass c: RowheaderAttClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
