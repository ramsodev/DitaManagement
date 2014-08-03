//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.02 at 08:00:15 PM CEST 
//


package net.ramso.dita.topic;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for bodydiv.class complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bodydiv.class">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{}bodydiv.content"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{}bodydiv.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bodydiv.class", propOrder = {
    "pOrLqOrNote"
})
@XmlSeeAlso({
    Bodydiv.class
})
public class BodydivClass {

    @XmlElements({
        @XmlElement(name = "p", type = P.class),
        @XmlElement(name = "lq", type = Lq.class),
        @XmlElement(name = "note", type = Note.class),
        @XmlElement(name = "hazardstatement", type = Hazardstatement.class),
        @XmlElement(name = "dl", type = Dl.class),
        @XmlElement(name = "parml", type = Parml.class),
        @XmlElement(name = "ul", type = Ul.class),
        @XmlElement(name = "ol", type = Ol.class),
        @XmlElement(name = "sl", type = Sl.class),
        @XmlElement(name = "pre", type = Pre.class),
        @XmlElement(name = "codeblock", type = Codeblock.class),
        @XmlElement(name = "screen", type = Screen.class),
        @XmlElement(name = "msgblock", type = Msgblock.class),
        @XmlElement(name = "lines", type = Lines.class),
        @XmlElement(name = "fig", type = Fig.class),
        @XmlElement(name = "syntaxdiagram", type = Syntaxdiagram.class),
        @XmlElement(name = "imagemap", type = Imagemap.class),
        @XmlElement(name = "image", type = Image.class),
        @XmlElement(name = "object", type = net.ramso.dita.topic.Object.class),
        @XmlElement(name = "table", type = Table.class),
        @XmlElement(name = "simpletable", type = Simpletable.class),
        @XmlElement(name = "ph", type = Ph.class),
        @XmlElement(name = "codeph", type = Codeph.class),
        @XmlElement(name = "synph", type = Synph.class),
        @XmlElement(name = "uicontrol", type = Uicontrol.class),
        @XmlElement(name = "menucascade", type = Menucascade.class),
        @XmlElement(name = "sup", type = Sup.class),
        @XmlElement(name = "sub", type = Sub.class),
        @XmlElement(name = "tt", type = Tt.class),
        @XmlElement(name = "b", type = B.class),
        @XmlElement(name = "u", type = U.class),
        @XmlElement(name = "i", type = I.class),
        @XmlElement(name = "msgph", type = Msgph.class),
        @XmlElement(name = "filepath", type = Filepath.class),
        @XmlElement(name = "userinput", type = Userinput.class),
        @XmlElement(name = "systemoutput", type = Systemoutput.class),
        @XmlElement(name = "term", type = Term.class),
        @XmlElement(name = "abbreviated-form", type = AbbreviatedForm.class),
        @XmlElement(name = "xref", type = Xref.class),
        @XmlElement(name = "cite", type = Cite.class),
        @XmlElement(name = "q", type = Q.class),
        @XmlElement(name = "state", type = State.class),
        @XmlElement(name = "boolean", type = Boolean.class),
        @XmlElement(name = "keyword", type = Keyword.class),
        @XmlElement(name = "option", type = Option.class),
        @XmlElement(name = "parmname", type = Parmname.class),
        @XmlElement(name = "apiname", type = Apiname.class),
        @XmlElement(name = "wintitle", type = Wintitle.class),
        @XmlElement(name = "msgnum", type = Msgnum.class),
        @XmlElement(name = "varname", type = Varname.class),
        @XmlElement(name = "cmdname", type = Cmdname.class),
        @XmlElement(name = "tm", type = Tm.class),
        @XmlElement(name = "data", type = Data.class),
        @XmlElement(name = "data-about", type = DataAbout.class),
        @XmlElement(name = "foreign", type = Foreign.class),
        @XmlElement(name = "unknown", type = Unknown.class),
        @XmlElement(name = "draft-comment", type = DraftComment.class),
        @XmlElement(name = "required-cleanup", type = RequiredCleanup.class),
        @XmlElement(name = "fn", type = Fn.class),
        @XmlElement(name = "indextermref", type = Indextermref.class),
        @XmlElement(name = "indexterm", type = Indexterm.class),
        @XmlElement(name = "bodydiv", type = Bodydiv.class),
        @XmlElement(name = "section", type = Section.class)
    })
    protected List<java.lang.Object> pOrLqOrNote;
    @XmlAttribute(name = "outputclass")
    protected String outputclass;
    @XmlAttribute(name = "base")
    protected String base;
    @XmlAttribute(name = "rev")
    protected String rev;
    @XmlAttribute(name = "importance")
    protected ImportanceAttsClass importance;
    @XmlAttribute(name = "status")
    protected StatusAttsClass status;
    @XmlAttribute(name = "props")
    protected String props;
    @XmlAttribute(name = "platform")
    protected String platform;
    @XmlAttribute(name = "product")
    protected String product;
    @XmlAttribute(name = "audience")
    protected String audienceMod;
    @XmlAttribute(name = "otherprops")
    protected String otherprops;
    @XmlAttribute(name = "translate")
    protected YesnoAttClass translate;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    protected String lang;
    @XmlAttribute(name = "dir")
    protected DirAttsClass dir;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String id;
    @XmlAttribute(name = "conref")
    protected String conref;
    @XmlAttribute(name = "conrefend")
    protected String conrefend;
    @XmlAttribute(name = "conaction")
    protected ConactionAttClass conaction;
    @XmlAttribute(name = "conkeyref")
    protected String conkeyref;
    @XmlAttribute(name = "xtrc")
    protected String xtrc;
    @XmlAttribute(name = "xtrf")
    protected String xtrf;

    /**
     * Gets the value of the pOrLqOrNote property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pOrLqOrNote property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPOrLqOrNote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link P }
     * {@link Lq }
     * {@link Note }
     * {@link Hazardstatement }
     * {@link Dl }
     * {@link Parml }
     * {@link Ul }
     * {@link Ol }
     * {@link Sl }
     * {@link Pre }
     * {@link Codeblock }
     * {@link Screen }
     * {@link Msgblock }
     * {@link Lines }
     * {@link Fig }
     * {@link Syntaxdiagram }
     * {@link Imagemap }
     * {@link Image }
     * {@link net.ramso.dita.topic.Object }
     * {@link Table }
     * {@link Simpletable }
     * {@link Ph }
     * {@link Codeph }
     * {@link Synph }
     * {@link Uicontrol }
     * {@link Menucascade }
     * {@link Sup }
     * {@link Sub }
     * {@link Tt }
     * {@link B }
     * {@link U }
     * {@link I }
     * {@link Msgph }
     * {@link Filepath }
     * {@link Userinput }
     * {@link Systemoutput }
     * {@link Term }
     * {@link AbbreviatedForm }
     * {@link Xref }
     * {@link Cite }
     * {@link Q }
     * {@link State }
     * {@link Boolean }
     * {@link Keyword }
     * {@link Option }
     * {@link Parmname }
     * {@link Apiname }
     * {@link Wintitle }
     * {@link Msgnum }
     * {@link Varname }
     * {@link Cmdname }
     * {@link Tm }
     * {@link Data }
     * {@link DataAbout }
     * {@link Foreign }
     * {@link Unknown }
     * {@link DraftComment }
     * {@link RequiredCleanup }
     * {@link Fn }
     * {@link Indextermref }
     * {@link Indexterm }
     * {@link Bodydiv }
     * {@link Section }
     * 
     * 
     */
    public List<java.lang.Object> getPOrLqOrNote() {
        if (pOrLqOrNote == null) {
            pOrLqOrNote = new ArrayList<java.lang.Object>();
        }
        return this.pOrLqOrNote;
    }

    /**
     * Gets the value of the outputclass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputclass() {
        return outputclass;
    }

    /**
     * Sets the value of the outputclass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputclass(String value) {
        this.outputclass = value;
    }

    /**
     * Gets the value of the base property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets the value of the base property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBase(String value) {
        this.base = value;
    }

    /**
     * Gets the value of the rev property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRev() {
        return rev;
    }

    /**
     * Sets the value of the rev property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRev(String value) {
        this.rev = value;
    }

    /**
     * Gets the value of the importance property.
     * 
     * @return
     *     possible object is
     *     {@link ImportanceAttsClass }
     *     
     */
    public ImportanceAttsClass getImportance() {
        return importance;
    }

    /**
     * Sets the value of the importance property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImportanceAttsClass }
     *     
     */
    public void setImportance(ImportanceAttsClass value) {
        this.importance = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusAttsClass }
     *     
     */
    public StatusAttsClass getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusAttsClass }
     *     
     */
    public void setStatus(StatusAttsClass value) {
        this.status = value;
    }

    /**
     * Gets the value of the props property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProps() {
        return props;
    }

    /**
     * Sets the value of the props property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProps(String value) {
        this.props = value;
    }

    /**
     * Gets the value of the platform property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * Sets the value of the platform property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlatform(String value) {
        this.platform = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduct(String value) {
        this.product = value;
    }

    /**
     * Gets the value of the audienceMod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAudienceMod() {
        return audienceMod;
    }

    /**
     * Sets the value of the audienceMod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAudienceMod(String value) {
        this.audienceMod = value;
    }

    /**
     * Gets the value of the otherprops property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherprops() {
        return otherprops;
    }

    /**
     * Sets the value of the otherprops property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherprops(String value) {
        this.otherprops = value;
    }

    /**
     * Gets the value of the translate property.
     * 
     * @return
     *     possible object is
     *     {@link YesnoAttClass }
     *     
     */
    public YesnoAttClass getTranslate() {
        return translate;
    }

    /**
     * Sets the value of the translate property.
     * 
     * @param value
     *     allowed object is
     *     {@link YesnoAttClass }
     *     
     */
    public void setTranslate(YesnoAttClass value) {
        this.translate = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the value of the dir property.
     * 
     * @return
     *     possible object is
     *     {@link DirAttsClass }
     *     
     */
    public DirAttsClass getDir() {
        return dir;
    }

    /**
     * Sets the value of the dir property.
     * 
     * @param value
     *     allowed object is
     *     {@link DirAttsClass }
     *     
     */
    public void setDir(DirAttsClass value) {
        this.dir = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the conref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConref() {
        return conref;
    }

    /**
     * Sets the value of the conref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConref(String value) {
        this.conref = value;
    }

    /**
     * Gets the value of the conrefend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConrefend() {
        return conrefend;
    }

    /**
     * Sets the value of the conrefend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConrefend(String value) {
        this.conrefend = value;
    }

    /**
     * Gets the value of the conaction property.
     * 
     * @return
     *     possible object is
     *     {@link ConactionAttClass }
     *     
     */
    public ConactionAttClass getConaction() {
        return conaction;
    }

    /**
     * Sets the value of the conaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConactionAttClass }
     *     
     */
    public void setConaction(ConactionAttClass value) {
        this.conaction = value;
    }

    /**
     * Gets the value of the conkeyref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConkeyref() {
        return conkeyref;
    }

    /**
     * Sets the value of the conkeyref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConkeyref(String value) {
        this.conkeyref = value;
    }

    /**
     * Gets the value of the xtrc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXtrc() {
        return xtrc;
    }

    /**
     * Sets the value of the xtrc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXtrc(String value) {
        this.xtrc = value;
    }

    /**
     * Gets the value of the xtrf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXtrf() {
        return xtrf;
    }

    /**
     * Sets the value of the xtrf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXtrf(String value) {
        this.xtrf = value;
    }

}
