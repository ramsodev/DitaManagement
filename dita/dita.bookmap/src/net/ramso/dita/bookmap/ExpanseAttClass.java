
package net.ramso.dita.bookmap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for expanse-att.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="expanse-att.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="page"/>
 *     &lt;enumeration value="column"/>
 *     &lt;enumeration value="textline"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "expanse-att.class")
@XmlEnum
public enum ExpanseAttClass {

    @XmlEnumValue("page")
    PAGE("page"),
    @XmlEnumValue("column")
    COLUMN("column"),
    @XmlEnumValue("textline")
    TEXTLINE("textline"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    ExpanseAttClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ExpanseAttClass fromValue(String v) {
        for (ExpanseAttClass c: ExpanseAttClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
