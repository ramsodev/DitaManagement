//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.02 at 08:08:55 PM CEST 
//


package net.ramso.dita.task;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for choice.class complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="choice.class">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{}choice.content"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{}choice.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "choice.class", propOrder = {
    "content"
})
@XmlSeeAlso({
    Choice.class
})
public class ChoiceClass {

    @XmlElementRefs({
        @XmlElementRef(name = "q", type = Q.class, required = false),
        @XmlElementRef(name = "filepath", type = Filepath.class, required = false),
        @XmlElementRef(name = "dl", type = Dl.class, required = false),
        @XmlElementRef(name = "imagemap", type = Imagemap.class, required = false),
        @XmlElementRef(name = "data-about", type = DataAbout.class, required = false),
        @XmlElementRef(name = "xref", type = Xref.class, required = false),
        @XmlElementRef(name = "apiname", type = Apiname.class, required = false),
        @XmlElementRef(name = "fig", type = Fig.class, required = false),
        @XmlElementRef(name = "note", type = Note.class, required = false),
        @XmlElementRef(name = "userinput", type = Userinput.class, required = false),
        @XmlElementRef(name = "foreign", type = Foreign.class, required = false),
        @XmlElementRef(name = "msgph", type = Msgph.class, required = false),
        @XmlElementRef(name = "lq", type = Lq.class, required = false),
        @XmlElementRef(name = "itemgroup", type = Itemgroup.class, required = false),
        @XmlElementRef(name = "keyword", type = Keyword.class, required = false),
        @XmlElementRef(name = "p", type = P.class, required = false),
        @XmlElementRef(name = "fn", type = Fn.class, required = false),
        @XmlElementRef(name = "option", type = Option.class, required = false),
        @XmlElementRef(name = "table", type = Table.class, required = false),
        @XmlElementRef(name = "pre", type = Pre.class, required = false),
        @XmlElementRef(name = "boolean", type = Boolean.class, required = false),
        @XmlElementRef(name = "tt", type = Tt.class, required = false),
        @XmlElementRef(name = "systemoutput", type = Systemoutput.class, required = false),
        @XmlElementRef(name = "codeph", type = Codeph.class, required = false),
        @XmlElementRef(name = "parmname", type = Parmname.class, required = false),
        @XmlElementRef(name = "syntaxdiagram", type = Syntaxdiagram.class, required = false),
        @XmlElementRef(name = "parml", type = Parml.class, required = false),
        @XmlElementRef(name = "uicontrol", type = Uicontrol.class, required = false),
        @XmlElementRef(name = "cmdname", type = Cmdname.class, required = false),
        @XmlElementRef(name = "tm", type = Tm.class, required = false),
        @XmlElementRef(name = "term", type = Term.class, required = false),
        @XmlElementRef(name = "msgblock", type = Msgblock.class, required = false),
        @XmlElementRef(name = "unknown", type = Unknown.class, required = false),
        @XmlElementRef(name = "codeblock", type = Codeblock.class, required = false),
        @XmlElementRef(name = "synph", type = Synph.class, required = false),
        @XmlElementRef(name = "state", type = State.class, required = false),
        @XmlElementRef(name = "image", type = Image.class, required = false),
        @XmlElementRef(name = "data", type = Data.class, required = false),
        @XmlElementRef(name = "ph", type = Ph.class, required = false),
        @XmlElementRef(name = "i", type = I.class, required = false),
        @XmlElementRef(name = "screen", type = Screen.class, required = false),
        @XmlElementRef(name = "u", type = U.class, required = false),
        @XmlElementRef(name = "varname", type = Varname.class, required = false),
        @XmlElementRef(name = "sl", type = Sl.class, required = false),
        @XmlElementRef(name = "wintitle", type = Wintitle.class, required = false),
        @XmlElementRef(name = "menucascade", type = Menucascade.class, required = false),
        @XmlElementRef(name = "object", type = net.ramso.dita.task.Object.class, required = false),
        @XmlElementRef(name = "ul", type = Ul.class, required = false),
        @XmlElementRef(name = "b", type = B.class, required = false),
        @XmlElementRef(name = "required-cleanup", type = RequiredCleanup.class, required = false),
        @XmlElementRef(name = "hazardstatement", type = Hazardstatement.class, required = false),
        @XmlElementRef(name = "sub", type = Sub.class, required = false),
        @XmlElementRef(name = "indextermref", type = Indextermref.class, required = false),
        @XmlElementRef(name = "cite", type = Cite.class, required = false),
        @XmlElementRef(name = "simpletable", type = Simpletable.class, required = false),
        @XmlElementRef(name = "sup", type = Sup.class, required = false),
        @XmlElementRef(name = "indexterm", type = Indexterm.class, required = false),
        @XmlElementRef(name = "ol", type = Ol.class, required = false),
        @XmlElementRef(name = "draft-comment", type = DraftComment.class, required = false),
        @XmlElementRef(name = "msgnum", type = Msgnum.class, required = false),
        @XmlElementRef(name = "lines", type = Lines.class, required = false),
        @XmlElementRef(name = "abbreviated-form", type = AbbreviatedForm.class, required = false)
    })
    @XmlMixed
    protected List<java.lang.Object> content;
    @XmlAttribute(name = "outputclass")
    protected String outputclass;
    @XmlAttribute(name = "xtrc")
    protected String xtrc;
    @XmlAttribute(name = "xtrf")
    protected String xtrf;
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
    @XmlAttribute(name = "translate")
    protected YesnoAttClass translate;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    protected String lang;
    @XmlAttribute(name = "dir")
    protected DirAttsClass dir;
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

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Q }
     * {@link Filepath }
     * {@link Dl }
     * {@link Imagemap }
     * {@link DataAbout }
     * {@link Xref }
     * {@link Apiname }
     * {@link Fig }
     * {@link Note }
     * {@link Userinput }
     * {@link Foreign }
     * {@link Msgph }
     * {@link Lq }
     * {@link Itemgroup }
     * {@link Keyword }
     * {@link P }
     * {@link Fn }
     * {@link Option }
     * {@link Table }
     * {@link Pre }
     * {@link Boolean }
     * {@link Tt }
     * {@link Systemoutput }
     * {@link Codeph }
     * {@link Parmname }
     * {@link Syntaxdiagram }
     * {@link Parml }
     * {@link Uicontrol }
     * {@link Cmdname }
     * {@link Tm }
     * {@link Term }
     * {@link Msgblock }
     * {@link String }
     * {@link Unknown }
     * {@link Codeblock }
     * {@link Synph }
     * {@link State }
     * {@link Image }
     * {@link Data }
     * {@link Ph }
     * {@link I }
     * {@link Screen }
     * {@link U }
     * {@link Varname }
     * {@link Sl }
     * {@link Wintitle }
     * {@link Menucascade }
     * {@link net.ramso.dita.task.Object }
     * {@link Ul }
     * {@link B }
     * {@link RequiredCleanup }
     * {@link Hazardstatement }
     * {@link Sub }
     * {@link Indextermref }
     * {@link Cite }
     * {@link Sup }
     * {@link Simpletable }
     * {@link Indexterm }
     * {@link Ol }
     * {@link DraftComment }
     * {@link Msgnum }
     * {@link Lines }
     * {@link AbbreviatedForm }
     * 
     * 
     */
    public List<java.lang.Object> getContent() {
        if (content == null) {
            content = new ArrayList<java.lang.Object>();
        }
        return this.content;
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

}
