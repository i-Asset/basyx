//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.16 at 09:48:33 AM CET 
//


package org.eclipse.basyx.onem2m.xml.protocols;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}announceableResource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="st" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *         &lt;element name="cr" type="{http://www.onem2m.org/xml/protocols}ID" minOccurs="0"/&gt;
 *         &lt;element name="mni" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="mbs" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="mia" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="cni" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *         &lt;element name="cbs" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *         &lt;element name="li" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="or" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/&gt;
 *         &lt;element name="disr" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="ch" type="{http://www.onem2m.org/xml/protocols}childResourceRef" maxOccurs="unbounded"/&gt;
 *           &lt;choice maxOccurs="unbounded"&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}cin"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}cnt"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}sub"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}smd"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}sg_flexContainerResource"/&gt;
 *           &lt;/choice&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "st",
    "cr",
    "mni",
    "mbs",
    "mia",
    "cni",
    "cbs",
    "li",
    "or",
    "disr",
    "ch",
    "sub",
    "smd",
    "sgFlexContainerResource",
    "cin",
    "cnt"
})
public class Cnt
    extends AnnounceableResource
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger st;
    @XmlSchemaType(name = "anyURI")
    protected String cr;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger mni;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger mbs;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger mia;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger cni;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger cbs;
    @XmlSchemaType(name = "anyURI")
    protected String li;
    @XmlSchemaType(name = "anyURI")
    protected String or;
    protected Boolean disr;
    protected List<ChildResourceRef> ch;
    @XmlElement(namespace = "http://www.onem2m.org/xml/protocols")
    protected List<Sub> sub;
    @XmlElement(namespace = "http://www.onem2m.org/xml/protocols")
    protected List<Smd> smd;
    @XmlElement(name = "sg_flexContainerResource", namespace = "http://www.onem2m.org/xml/protocols")
    protected List<FlexContainerResource> sgFlexContainerResource;
    @XmlElement(namespace = "http://www.onem2m.org/xml/protocols")
    protected List<Cin> cin;
    @XmlElement(namespace = "http://www.onem2m.org/xml/protocols")
    protected List<Cnt> cnt;
    
    protected String la; // NOTE mschoeffler: manually added, as this is part of the next release
    public String getLa() { return this.la; };
    public void setLa(String la) { this.la = la; };
    protected String ol; // NOTE mschoeffler: manually added, as this is part of the next release    
    public String getOl() { return this.ol; };
    public void setOl(String ol) { this.ol = ol; };


    /**
     * Gets the value of the st property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSt() {
        return st;
    }

    /**
     * Sets the value of the st property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSt(BigInteger value) {
        this.st = value;
    }

    /**
     * Gets the value of the cr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCr() {
        return cr;
    }

    /**
     * Sets the value of the cr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCr(String value) {
        this.cr = value;
    }

    /**
     * Gets the value of the mni property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMni() {
        return mni;
    }

    /**
     * Sets the value of the mni property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMni(BigInteger value) {
        this.mni = value;
    }

    /**
     * Gets the value of the mbs property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMbs() {
        return mbs;
    }

    /**
     * Sets the value of the mbs property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMbs(BigInteger value) {
        this.mbs = value;
    }

    /**
     * Gets the value of the mia property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMia() {
        return mia;
    }

    /**
     * Sets the value of the mia property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMia(BigInteger value) {
        this.mia = value;
    }

    /**
     * Gets the value of the cni property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCni() {
        return cni;
    }

    /**
     * Sets the value of the cni property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCni(BigInteger value) {
        this.cni = value;
    }

    /**
     * Gets the value of the cbs property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCbs() {
        return cbs;
    }

    /**
     * Sets the value of the cbs property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCbs(BigInteger value) {
        this.cbs = value;
    }

    /**
     * Gets the value of the li property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLi() {
        return li;
    }

    /**
     * Sets the value of the li property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLi(String value) {
        this.li = value;
    }

    /**
     * Gets the value of the or property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOr() {
        return or;
    }

    /**
     * Sets the value of the or property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOr(String value) {
        this.or = value;
    }

    /**
     * Gets the value of the disr property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDisr() {
        return disr;
    }

    /**
     * Sets the value of the disr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisr(Boolean value) {
        this.disr = value;
    }

    /**
     * Gets the value of the ch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCh().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChildResourceRef }
     * 
     * 
     */
    public List<ChildResourceRef> getCh() {
        if (ch == null) {
            ch = new ArrayList<ChildResourceRef>();
        }
        return this.ch;
    }

    /**
     * Gets the value of the sub property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sub property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSub().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sub }
     * 
     * 
     */
    public List<Sub> getSub() {
        if (sub == null) {
            sub = new ArrayList<Sub>();
        }
        return this.sub;
    }

    /**
     * Gets the value of the smd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the smd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSmd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Smd }
     * 
     * 
     */
    public List<Smd> getSmd() {
        if (smd == null) {
            smd = new ArrayList<Smd>();
        }
        return this.smd;
    }

    /**
     * Gets the value of the sgFlexContainerResource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sgFlexContainerResource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSgFlexContainerResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlexContainerResource }
     * 
     * 
     */
    public List<FlexContainerResource> getSgFlexContainerResource() {
        if (sgFlexContainerResource == null) {
            sgFlexContainerResource = new ArrayList<FlexContainerResource>();
        }
        return this.sgFlexContainerResource;
    }

    /**
     * Gets the value of the cin property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cin property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCin().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cin }
     * 
     * 
     */
    public List<Cin> getCin() {
        if (cin == null) {
            cin = new ArrayList<Cin>();
        }
        return this.cin;
    }

    /**
     * Gets the value of the cnt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cnt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCnt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cnt }
     * 
     * 
     */
    public List<Cnt> getCnt() {
        if (cnt == null) {
            cnt = new ArrayList<Cnt>();
        }
        return this.cnt;
    }

}
