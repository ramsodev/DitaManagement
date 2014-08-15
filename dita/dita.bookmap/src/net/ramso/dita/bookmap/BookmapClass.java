
package net.ramso.dita.bookmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

import net.ramso.dita.utils.GenericData;


/**
 * <p>Java class for bookmap.class complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bookmap.class">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{}bookmap.content"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{}bookmap.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bookmap.class", propOrder = {
    "title",
    "booktitle",
    "bookmeta",
    "frontmatter",
    "chapter",
    "part",
    "appendices",
    "appendix",
    "backmatter",
    "reltable"
})
@XmlSeeAlso({
    Bookmap.class
})
public class BookmapClass extends GenericData{

    protected Title title;
    protected Booktitle booktitle;
    protected Bookmeta bookmeta;
    protected Frontmatter frontmatter;
    protected List<Chapter> chapter;
    protected List<Part> part;
    protected Appendices appendices;
    protected List<Appendix> appendix;
    protected Backmatter backmatter;
    protected List<Reltable> reltable;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "anchorref")
    protected String anchorref;
    @XmlAttribute(name = "outputclass")
    protected String outputclass;
    @XmlAttribute(name = "DITAArchVersion", namespace = "http://dita.oasis-open.org/architecture/2005/")
    protected String ditaArchVersion;
    @XmlAttribute(name = "collection-type")
    protected CollectionTypeClass collectionType;
    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "processing-role")
    protected ProcessingRoleAttClass processingRole;
    @XmlAttribute(name = "scope")
    protected ScopeAttClass scope;
    @XmlAttribute(name = "locktitle")
    protected YesnoAttClass locktitle;
    @XmlAttribute(name = "format")
    protected String format;
    @XmlAttribute(name = "linking")
    protected LinkingtypesClass linking;
    @XmlAttribute(name = "toc")
    protected YesnoAttClass toc;
    @XmlAttribute(name = "print")
    protected PrintAttClass print;
    @XmlAttribute(name = "search")
    protected YesnoAttClass search;
    @XmlAttribute(name = "chunk")
    protected String chunk;
    @XmlAttribute(name = "translate")
    protected YesnoAttClass translate;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    protected String lang;
    @XmlAttribute(name = "dir")
    protected DirAttsClass dir;
    @XmlAttribute(name = "domains")
    protected String domains;
    @XmlAttribute(name = "xtrc")
    protected String xtrc;
    @XmlAttribute(name = "xtrf")
    protected String xtrf;
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
    @XmlAttribute(name = "conref")
    protected String conref;
    @XmlAttribute(name = "conrefend")
    protected String conrefend;
    @XmlAttribute(name = "conaction")
    protected ConactionAttClass conaction;
    @XmlAttribute(name = "conkeyref")
    protected String conkeyref;
    
   
    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link Title }
     *     
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link Title }
     *     
     */
    public void setTitle(Title value) {
        this.title = value;
    }

    /**
     * Gets the value of the booktitle property.
     * 
     * @return
     *     possible object is
     *     {@link Booktitle }
     *     
     */
    public Booktitle getBooktitle() {
        return booktitle;
    }

    /**
     * Sets the value of the booktitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Booktitle }
     *     
     */
    public void setBooktitle(Booktitle value) {
        this.booktitle = value;
    }

    /**
     * Gets the value of the bookmeta property.
     * 
     * @return
     *     possible object is
     *     {@link Bookmeta }
     *     
     */
    public Bookmeta getBookmeta() {
        return bookmeta;
    }

    /**
     * Sets the value of the bookmeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bookmeta }
     *     
     */
    public void setBookmeta(Bookmeta value) {
        this.bookmeta = value;
    }

    /**
     * Gets the value of the frontmatter property.
     * 
     * @return
     *     possible object is
     *     {@link Frontmatter }
     *     
     */
    public Frontmatter getFrontmatter() {
        return frontmatter;
    }

    /**
     * Sets the value of the frontmatter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Frontmatter }
     *     
     */
    public void setFrontmatter(Frontmatter value) {
        this.frontmatter = value;
    }

    /**
     * Gets the value of the chapter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chapter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChapter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Chapter }
     * 
     * 
     */
    public List<Chapter> getChapter() {
        if (chapter == null) {
            chapter = new ArrayList<Chapter>();
        }
        return this.chapter;
    }

    /**
     * Gets the value of the part property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the part property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPart().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Part }
     * 
     * 
     */
    public List<Part> getPart() {
        if (part == null) {
            part = new ArrayList<Part>();
        }
        return this.part;
    }

    /**
     * Gets the value of the appendices property.
     * 
     * @return
     *     possible object is
     *     {@link Appendices }
     *     
     */
    public Appendices getAppendices() {
        return appendices;
    }

    /**
     * Sets the value of the appendices property.
     * 
     * @param value
     *     allowed object is
     *     {@link Appendices }
     *     
     */
    public void setAppendices(Appendices value) {
        this.appendices = value;
    }

    /**
     * Gets the value of the appendix property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the appendix property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppendix().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Appendix }
     * 
     * 
     */
    public List<Appendix> getAppendix() {
        if (appendix == null) {
            appendix = new ArrayList<Appendix>();
        }
        return this.appendix;
    }

    /**
     * Gets the value of the backmatter property.
     * 
     * @return
     *     possible object is
     *     {@link Backmatter }
     *     
     */
    public Backmatter getBackmatter() {
        return backmatter;
    }

    /**
     * Sets the value of the backmatter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Backmatter }
     *     
     */
    public void setBackmatter(Backmatter value) {
        this.backmatter = value;
    }

    /**
     * Gets the value of the reltable property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reltable property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReltable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reltable }
     * 
     * 
     */
    public List<Reltable> getReltable() {
        if (reltable == null) {
            reltable = new ArrayList<Reltable>();
        }
        return this.reltable;
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
     * Gets the value of the anchorref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchorref() {
        return anchorref;
    }

    /**
     * Sets the value of the anchorref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchorref(String value) {
        this.anchorref = value;
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
     * Gets the value of the ditaArchVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDITAArchVersion() {
        if (ditaArchVersion == null) {
            return "1.2";
        } else {
            return ditaArchVersion;
        }
    }

    /**
     * Sets the value of the ditaArchVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDITAArchVersion(String value) {
        this.ditaArchVersion = value;
    }

    /**
     * Gets the value of the collectionType property.
     * 
     * @return
     *     possible object is
     *     {@link CollectionTypeClass }
     *     
     */
    public CollectionTypeClass getCollectionType() {
        return collectionType;
    }

    /**
     * Sets the value of the collectionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionTypeClass }
     *     
     */
    public void setCollectionType(CollectionTypeClass value) {
        this.collectionType = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the processingRole property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessingRoleAttClass }
     *     
     */
    public ProcessingRoleAttClass getProcessingRole() {
        return processingRole;
    }

    /**
     * Sets the value of the processingRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessingRoleAttClass }
     *     
     */
    public void setProcessingRole(ProcessingRoleAttClass value) {
        this.processingRole = value;
    }

    /**
     * Gets the value of the scope property.
     * 
     * @return
     *     possible object is
     *     {@link ScopeAttClass }
     *     
     */
    public ScopeAttClass getScope() {
        return scope;
    }

    /**
     * Sets the value of the scope property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScopeAttClass }
     *     
     */
    public void setScope(ScopeAttClass value) {
        this.scope = value;
    }

    /**
     * Gets the value of the locktitle property.
     * 
     * @return
     *     possible object is
     *     {@link YesnoAttClass }
     *     
     */
    public YesnoAttClass getLocktitle() {
        return locktitle;
    }

    /**
     * Sets the value of the locktitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link YesnoAttClass }
     *     
     */
    public void setLocktitle(YesnoAttClass value) {
        this.locktitle = value;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

    /**
     * Gets the value of the linking property.
     * 
     * @return
     *     possible object is
     *     {@link LinkingtypesClass }
     *     
     */
    public LinkingtypesClass getLinking() {
        return linking;
    }

    /**
     * Sets the value of the linking property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinkingtypesClass }
     *     
     */
    public void setLinking(LinkingtypesClass value) {
        this.linking = value;
    }

    /**
     * Gets the value of the toc property.
     * 
     * @return
     *     possible object is
     *     {@link YesnoAttClass }
     *     
     */
    public YesnoAttClass getToc() {
        return toc;
    }

    /**
     * Sets the value of the toc property.
     * 
     * @param value
     *     allowed object is
     *     {@link YesnoAttClass }
     *     
     */
    public void setToc(YesnoAttClass value) {
        this.toc = value;
    }

    /**
     * Gets the value of the print property.
     * 
     * @return
     *     possible object is
     *     {@link PrintAttClass }
     *     
     */
    public PrintAttClass getPrint() {
        return print;
    }

    /**
     * Sets the value of the print property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrintAttClass }
     *     
     */
    public void setPrint(PrintAttClass value) {
        this.print = value;
    }

    /**
     * Gets the value of the search property.
     * 
     * @return
     *     possible object is
     *     {@link YesnoAttClass }
     *     
     */
    public YesnoAttClass getSearch() {
        return search;
    }

    /**
     * Sets the value of the search property.
     * 
     * @param value
     *     allowed object is
     *     {@link YesnoAttClass }
     *     
     */
    public void setSearch(YesnoAttClass value) {
        this.search = value;
    }

    /**
     * Gets the value of the chunk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChunk() {
        return chunk;
    }

    /**
     * Sets the value of the chunk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChunk(String value) {
        this.chunk = value;
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
     * Gets the value of the domains property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomains() {
        if (domains == null) {
            return "(map mapgroup-d) (topic indexing-d)  (topic xnal-d) (topic delay-d) (map bookmap)";
        } else {
            return domains;
        }
    }

    /**
     * Sets the value of the domains property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomains(String value) {
        this.domains = value;
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

}
