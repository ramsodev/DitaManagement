
package net.ramso.dita.bookmap;

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

import net.ramso.dita.utils.GenericData;


/**
 * <p>Java class for topicmeta.class complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="topicmeta.class">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{}topicmeta.content"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{}topicmeta.attributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "topicmeta.class", propOrder = {
    "navtitle",
    "linktext",
    "searchtitle",
    "shortdesc",
    "author",
    "source",
    "publisher",
    "copyright",
    "critdates",
    "permissions",
    "metadata",
    "audience",
    "category",
    "keywords",
    "prodinfo",
    "othermeta",
    "resourceid",
    "dataOrDataAboutOrForeign"
})
@XmlSeeAlso({
    Topicmeta.class
})
public class TopicmetaClass extends GenericData {

    protected Navtitle navtitle;
    protected Linktext linktext;
    protected Searchtitle searchtitle;
    protected Shortdesc shortdesc;
    @XmlElements({
        @XmlElement(type = Author.class),
        @XmlElement(name = "authorinformation", type = Authorinformation.class)
    })
    protected List<java.lang.Object> author;
    protected Source source;
    protected Publisher publisher;
    protected List<Copyright> copyright;
    protected Critdates critdates;
    protected Permissions permissions;
    protected List<Metadata> metadata;
    protected List<Audience> audience;
    protected List<Category> category;
    @XmlElements({
        @XmlElement(type = Keywords.class),
        @XmlElement(name = "exportanchors", type = Exportanchors.class)
    })
    protected List<java.lang.Object> keywords;
    protected List<Prodinfo> prodinfo;
    protected List<Othermeta> othermeta;
    protected List<Resourceid> resourceid;
    @XmlElements({
        @XmlElement(name = "data", type = Data.class),
        @XmlElement(name = "data-about", type = DataAbout.class),
        @XmlElement(name = "foreign", type = Foreign.class),
        @XmlElement(name = "unknown", type = Unknown.class)
    })
    protected List<java.lang.Object> dataOrDataAboutOrForeign;
    @XmlAttribute(name = "lockmeta")
    protected YesnoAttClass lockmeta;
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
    @XmlAttribute(name = "xtrc")
    protected String xtrc;
    @XmlAttribute(name = "xtrf")
    protected String xtrf;

    /**
     * Gets the value of the navtitle property.
     * 
     * @return
     *     possible object is
     *     {@link Navtitle }
     *     
     */
    public Navtitle getNavtitle() {
        return navtitle;
    }

    /**
     * Sets the value of the navtitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Navtitle }
     *     
     */
    public void setNavtitle(Navtitle value) {
        this.navtitle = value;
    }

    /**
     * Gets the value of the linktext property.
     * 
     * @return
     *     possible object is
     *     {@link Linktext }
     *     
     */
    public Linktext getLinktext() {
        return linktext;
    }

    /**
     * Sets the value of the linktext property.
     * 
     * @param value
     *     allowed object is
     *     {@link Linktext }
     *     
     */
    public void setLinktext(Linktext value) {
        this.linktext = value;
    }

    /**
     * Gets the value of the searchtitle property.
     * 
     * @return
     *     possible object is
     *     {@link Searchtitle }
     *     
     */
    public Searchtitle getSearchtitle() {
        return searchtitle;
    }

    /**
     * Sets the value of the searchtitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Searchtitle }
     *     
     */
    public void setSearchtitle(Searchtitle value) {
        this.searchtitle = value;
    }

    /**
     * Gets the value of the shortdesc property.
     * 
     * @return
     *     possible object is
     *     {@link Shortdesc }
     *     
     */
    public Shortdesc getShortdesc() {
        return shortdesc;
    }

    /**
     * Sets the value of the shortdesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Shortdesc }
     *     
     */
    public void setShortdesc(Shortdesc value) {
        this.shortdesc = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the author property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Author }
     * {@link Authorinformation }
     * 
     * 
     */
    public List<java.lang.Object> getAuthor() {
        if (author == null) {
            author = new ArrayList<java.lang.Object>();
        }
        return this.author;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Source }
     *     
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Source }
     *     
     */
    public void setSource(Source value) {
        this.source = value;
    }

    /**
     * Gets the value of the publisher property.
     * 
     * @return
     *     possible object is
     *     {@link Publisher }
     *     
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * Sets the value of the publisher property.
     * 
     * @param value
     *     allowed object is
     *     {@link Publisher }
     *     
     */
    public void setPublisher(Publisher value) {
        this.publisher = value;
    }

    /**
     * Gets the value of the copyright property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the copyright property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCopyright().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Copyright }
     * 
     * 
     */
    public List<Copyright> getCopyright() {
        if (copyright == null) {
            copyright = new ArrayList<Copyright>();
        }
        return this.copyright;
    }

    /**
     * Gets the value of the critdates property.
     * 
     * @return
     *     possible object is
     *     {@link Critdates }
     *     
     */
    public Critdates getCritdates() {
        return critdates;
    }

    /**
     * Sets the value of the critdates property.
     * 
     * @param value
     *     allowed object is
     *     {@link Critdates }
     *     
     */
    public void setCritdates(Critdates value) {
        this.critdates = value;
    }

    /**
     * Gets the value of the permissions property.
     * 
     * @return
     *     possible object is
     *     {@link Permissions }
     *     
     */
    public Permissions getPermissions() {
        return permissions;
    }

    /**
     * Sets the value of the permissions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Permissions }
     *     
     */
    public void setPermissions(Permissions value) {
        this.permissions = value;
    }

    /**
     * Gets the value of the metadata property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadata property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadata().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Metadata }
     * 
     * 
     */
    public List<Metadata> getMetadata() {
        if (metadata == null) {
            metadata = new ArrayList<Metadata>();
        }
        return this.metadata;
    }

    /**
     * Gets the value of the audience property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the audience property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAudience().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Audience }
     * 
     * 
     */
    public List<Audience> getAudience() {
        if (audience == null) {
            audience = new ArrayList<Audience>();
        }
        return this.audience;
    }

    /**
     * Gets the value of the category property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the category property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Category }
     * 
     * 
     */
    public List<Category> getCategory() {
        if (category == null) {
            category = new ArrayList<Category>();
        }
        return this.category;
    }

    /**
     * Gets the value of the keywords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keywords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeywords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Keywords }
     * {@link Exportanchors }
     * 
     * 
     */
    public List<java.lang.Object> getKeywords() {
        if (keywords == null) {
            keywords = new ArrayList<java.lang.Object>();
        }
        return this.keywords;
    }

    /**
     * Gets the value of the prodinfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prodinfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProdinfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Prodinfo }
     * 
     * 
     */
    public List<Prodinfo> getProdinfo() {
        if (prodinfo == null) {
            prodinfo = new ArrayList<Prodinfo>();
        }
        return this.prodinfo;
    }

    /**
     * Gets the value of the othermeta property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the othermeta property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOthermeta().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Othermeta }
     * 
     * 
     */
    public List<Othermeta> getOthermeta() {
        if (othermeta == null) {
            othermeta = new ArrayList<Othermeta>();
        }
        return this.othermeta;
    }

    /**
     * Gets the value of the resourceid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceid().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Resourceid }
     * 
     * 
     */
    public List<Resourceid> getResourceid() {
        if (resourceid == null) {
            resourceid = new ArrayList<Resourceid>();
        }
        return this.resourceid;
    }

    /**
     * Gets the value of the dataOrDataAboutOrForeign property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataOrDataAboutOrForeign property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataOrDataAboutOrForeign().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Data }
     * {@link DataAbout }
     * {@link Foreign }
     * {@link Unknown }
     * 
     * 
     */
    public List<java.lang.Object> getDataOrDataAboutOrForeign() {
        if (dataOrDataAboutOrForeign == null) {
            dataOrDataAboutOrForeign = new ArrayList<java.lang.Object>();
        }
        return this.dataOrDataAboutOrForeign;
    }

    /**
     * Gets the value of the lockmeta property.
     * 
     * @return
     *     possible object is
     *     {@link YesnoAttClass }
     *     
     */
    public YesnoAttClass getLockmeta() {
        return lockmeta;
    }

    /**
     * Sets the value of the lockmeta property.
     * 
     * @param value
     *     allowed object is
     *     {@link YesnoAttClass }
     *     
     */
    public void setLockmeta(YesnoAttClass value) {
        this.lockmeta = value;
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
