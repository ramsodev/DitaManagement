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
 * <p>Java class for scope-att.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="scope-att.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="local"/>
 *     &lt;enumeration value="peer"/>
 *     &lt;enumeration value="external"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "scope-att.class")
@XmlEnum
public enum ScopeAttClass {

    @XmlEnumValue("local")
    LOCAL("local"),
    @XmlEnumValue("peer")
    PEER("peer"),
    @XmlEnumValue("external")
    EXTERNAL("external"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    ScopeAttClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ScopeAttClass fromValue(String v) {
        for (ScopeAttClass c: ScopeAttClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
