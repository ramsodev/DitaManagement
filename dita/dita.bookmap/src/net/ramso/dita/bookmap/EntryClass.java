
package net.ramso.dita.bookmap;

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
 * <p>Java class for entry.class complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="entry.class">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{}entry.content"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{}entry.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "entry.class", propOrder = {
    "content"
})
@XmlSeeAlso({
    Entry.class
})
public class EntryClass {

    @XmlElementRefs({
        @XmlElementRef(name = "ph", type = Ph.class, required = false),
        @XmlElementRef(name = "lines", type = Lines.class, required = false),
        @XmlElementRef(name = "p", type = P.class, required = false),
        @XmlElementRef(name = "image", type = Image.class, required = false),
        @XmlElementRef(name = "draft-comment", type = DraftComment.class, required = false),
        @XmlElementRef(name = "required-cleanup", type = RequiredCleanup.class, required = false),
        @XmlElementRef(name = "boolean", type = Boolean.class, required = false),
        @XmlElementRef(name = "q", type = Q.class, required = false),
        @XmlElementRef(name = "note", type = Note.class, required = false),
        @XmlElementRef(name = "data", type = Data.class, required = false),
        @XmlElementRef(name = "cite", type = Cite.class, required = false),
        @XmlElementRef(name = "term", type = Term.class, required = false),
        @XmlElementRef(name = "ul", type = Ul.class, required = false),
        @XmlElementRef(name = "pre", type = Pre.class, required = false),
        @XmlElementRef(name = "unknown", type = Unknown.class, required = false),
        @XmlElementRef(name = "sl", type = Sl.class, required = false),
        @XmlElementRef(name = "fig", type = Fig.class, required = false),
        @XmlElementRef(name = "object", type = net.ramso.dita.bookmap.Object.class, required = false),
        @XmlElementRef(name = "foreign", type = Foreign.class, required = false),
        @XmlElementRef(name = "indexterm", type = Indexterm.class, required = false),
        @XmlElementRef(name = "tm", type = Tm.class, required = false),
        @XmlElementRef(name = "data-about", type = DataAbout.class, required = false),
        @XmlElementRef(name = "keyword", type = Keyword.class, required = false),
        @XmlElementRef(name = "ol", type = Ol.class, required = false),
        @XmlElementRef(name = "dl", type = Dl.class, required = false),
        @XmlElementRef(name = "fn", type = Fn.class, required = false),
        @XmlElementRef(name = "xref", type = Xref.class, required = false),
        @XmlElementRef(name = "indextermref", type = Indextermref.class, required = false),
        @XmlElementRef(name = "lq", type = Lq.class, required = false),
        @XmlElementRef(name = "state", type = State.class, required = false)
    })
    @XmlMixed
    protected List<java.lang.Object> content;
    @XmlAttribute(name = "namest")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String namest;
    @XmlAttribute(name = "nameend")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String nameend;
    @XmlAttribute(name = "colname")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String colname;
    @XmlAttribute(name = "morerows")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String morerows;
    @XmlAttribute(name = "char")
    protected String _char;
    @XmlAttribute(name = "charoff")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String charoff;
    @XmlAttribute(name = "align")
    protected AlignAttClass align;
    @XmlAttribute(name = "valign")
    protected ValignAttClass valign;
    @XmlAttribute(name = "rev")
    protected String rev;
    @XmlAttribute(name = "outputclass")
    protected String outputclass;
    @XmlAttribute(name = "base")
    protected String base;
    @XmlAttribute(name = "colsep")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String colsep;
    @XmlAttribute(name = "rowsep")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String rowsep;
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
    @XmlAttribute(name = "translate")
    protected YesnoAttClass translate;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    protected String lang;
    @XmlAttribute(name = "dir")
    protected DirAttsClass dir;

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
     * {@link Ph }
     * {@link Lines }
     * {@link String }
     * {@link P }
     * {@link Image }
     * {@link DraftComment }
     * {@link RequiredCleanup }
     * {@link Boolean }
     * {@link Q }
     * {@link Note }
     * {@link Data }
     * {@link Cite }
     * {@link Term }
     * {@link Ul }
     * {@link Pre }
     * {@link Unknown }
     * {@link Sl }
     * {@link Fig }
     * {@link net.ramso.dita.bookmap.Object }
     * {@link Foreign }
     * {@link Indexterm }
     * {@link Tm }
     * {@link DataAbout }
     * {@link Keyword }
     * {@link Ol }
     * {@link Dl }
     * {@link Fn }
     * {@link Xref }
     * {@link Indextermref }
     * {@link Lq }
     * {@link State }
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
     * Gets the value of the namest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamest() {
        return namest;
    }

    /**
     * Sets the value of the namest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamest(String value) {
        this.namest = value;
    }

    /**
     * Gets the value of the nameend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameend() {
        return nameend;
    }

    /**
     * Sets the value of the nameend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameend(String value) {
        this.nameend = value;
    }

    /**
     * Gets the value of the colname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColname() {
        return colname;
    }

    /**
     * Sets the value of the colname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColname(String value) {
        this.colname = value;
    }

    /**
     * Gets the value of the morerows property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMorerows() {
        return morerows;
    }

    /**
     * Sets the value of the morerows property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMorerows(String value) {
        this.morerows = value;
    }

    /**
     * Gets the value of the char property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChar() {
        return _char;
    }

    /**
     * Sets the value of the char property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChar(String value) {
        this._char = value;
    }

    /**
     * Gets the value of the charoff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharoff() {
        return charoff;
    }

    /**
     * Sets the value of the charoff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharoff(String value) {
        this.charoff = value;
    }

    /**
     * Gets the value of the align property.
     * 
     * @return
     *     possible object is
     *     {@link AlignAttClass }
     *     
     */
    public AlignAttClass getAlign() {
        return align;
    }

    /**
     * Sets the value of the align property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlignAttClass }
     *     
     */
    public void setAlign(AlignAttClass value) {
        this.align = value;
    }

    /**
     * Gets the value of the valign property.
     * 
     * @return
     *     possible object is
     *     {@link ValignAttClass }
     *     
     */
    public ValignAttClass getValign() {
        return valign;
    }

    /**
     * Sets the value of the valign property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValignAttClass }
     *     
     */
    public void setValign(ValignAttClass value) {
        this.valign = value;
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
     * Gets the value of the colsep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColsep() {
        return colsep;
    }

    /**
     * Sets the value of the colsep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColsep(String value) {
        this.colsep = value;
    }

    /**
     * Gets the value of the rowsep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowsep() {
        return rowsep;
    }

    /**
     * Sets the value of the rowsep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowsep(String value) {
        this.rowsep = value;
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

}
