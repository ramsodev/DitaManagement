//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.02 at 08:10:17 PM CEST 
//


package net.ramso.dita.reference;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for importance-att-progdom.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="importance-att-progdom.class">
 *   &lt;restriction base="{}importance-atts.class">
 *     &lt;enumeration value="optional"/>
 *     &lt;enumeration value="required"/>
 *     &lt;enumeration value="default"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "importance-att-progdom.class")
@XmlEnum(ImportanceAttsClass.class)
public enum ImportanceAttProgdomClass {

    @XmlEnumValue("optional")
    OPTIONAL(ImportanceAttsClass.OPTIONAL),
    @XmlEnumValue("required")
    REQUIRED(ImportanceAttsClass.REQUIRED),
    @XmlEnumValue("default")
    DEFAULT(ImportanceAttsClass.DEFAULT),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET(ImportanceAttsClass.DITA_USE_CONREF_TARGET);
    private final ImportanceAttsClass value;

    ImportanceAttProgdomClass(ImportanceAttsClass v) {
        value = v;
    }

    public ImportanceAttsClass value() {
        return value;
    }

    public static ImportanceAttProgdomClass fromValue(ImportanceAttsClass v) {
        for (ImportanceAttProgdomClass c: ImportanceAttProgdomClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
