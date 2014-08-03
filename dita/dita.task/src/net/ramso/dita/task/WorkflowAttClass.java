//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.02 at 08:08:55 PM CEST 
//


package net.ramso.dita.task;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for workflow-att.class.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="workflow-att.class">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="author"/>
 *     &lt;enumeration value="editor"/>
 *     &lt;enumeration value="reviewer"/>
 *     &lt;enumeration value="publisher"/>
 *     &lt;enumeration value="-dita-use-conref-target"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "workflow-att.class")
@XmlEnum
public enum WorkflowAttClass {

    @XmlEnumValue("author")
    AUTHOR("author"),
    @XmlEnumValue("editor")
    EDITOR("editor"),
    @XmlEnumValue("reviewer")
    REVIEWER("reviewer"),
    @XmlEnumValue("publisher")
    PUBLISHER("publisher"),
    @XmlEnumValue("-dita-use-conref-target")
    DITA_USE_CONREF_TARGET("-dita-use-conref-target");
    private final String value;

    WorkflowAttClass(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WorkflowAttClass fromValue(String v) {
        for (WorkflowAttClass c: WorkflowAttClass.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
