//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.02 at 08:10:00 PM CEST 
//


package net.ramso.dita.map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for type-note-atts.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="type-note-atts.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="attention"/>
 *     &lt;enumeration value="caution"/>
 *     &lt;enumeration value="danger"/>
 *     &lt;enumeration value="fastpath"/>
 *     &lt;enumeration value="important"/>
 *     &lt;enumeration value="note"/>
 *     &lt;enumeration value="notice"/>
 *     &lt;enumeration value="other"/>
 *     &lt;enumeration value="remember"/>
 *     &lt;enumeration value="restriction"/>
 *     &lt;enumeration value="tip"/>
 *     &lt;enumeration value="warning"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "type-note-atts.class")
@XmlEnum
public enum TypeNoteAttsClass {

    @XmlEnumValue("attention")
    ATTENTION("attention"),
    @XmlEnumValue("caution")
    CAUTION("caution"),
    @XmlEnumValue("danger")
    DANGER("danger"),
    @XmlEnumValue("fastpath")
    FASTPATH("fastpath"),
    @XmlEnumValue("important")
    IMPORTANT("important"),
    @XmlEnumValue("note")
    NOTE("note"),
    @XmlEnumValue("notice")
    NOTICE("notice"),
    @XmlEnumValue("other")
    OTHER("other"),
    @XmlEnumValue("remember")
    REMEMBER("remember"),
    @XmlEnumValue("restriction")
    RESTRICTION("restriction"),
    @XmlEnumValue("tip")
    TIP("tip"),
    @XmlEnumValue("warning")
    WARNING("warning"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    TypeNoteAttsClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeNoteAttsClass fromValue(String v) {
        for (TypeNoteAttsClass c: TypeNoteAttsClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
