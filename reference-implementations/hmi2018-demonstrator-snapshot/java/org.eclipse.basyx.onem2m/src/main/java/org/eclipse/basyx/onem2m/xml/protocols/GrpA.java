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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlList;
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
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}announcedResource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mt" type="{http://www.onem2m.org/xml/protocols}memberType" minOccurs="0"/&gt;
 *         &lt;element name="cnm" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/&gt;
 *         &lt;element name="mnm" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="mid" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;list itemType="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="macp" type="{http://www.onem2m.org/xml/protocols}listOfURIs" minOccurs="0"/&gt;
 *         &lt;element name="mtv" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="csy" type="{http://www.onem2m.org/xml/protocols}consistencyStrategy" minOccurs="0"/&gt;
 *         &lt;element name="gn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="ch" type="{http://www.onem2m.org/xml/protocols}childResourceRef" maxOccurs="unbounded"/&gt;
 *           &lt;choice maxOccurs="unbounded"&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}sub"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}smd"/&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}smdA"/&gt;
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
    "mt",
    "cnm",
    "mnm",
    "mid",
    "macp",
    "mtv",
    "csy",
    "gn",
    "ch",
    "subOrSmdOrSmdA"
})
public class GrpA
    extends AnnouncedResource
{

    protected BigInteger mt;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger cnm;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger mnm;
    @XmlList
    protected List<String> mid;
    @XmlList
    protected List<String> macp;
    protected Boolean mtv;
    protected BigInteger csy;
    protected String gn;
    protected List<ChildResourceRef> ch;
    @XmlElements({
        @XmlElement(name = "sub", namespace = "http://www.onem2m.org/xml/protocols", type = Sub.class),
        @XmlElement(name = "smd", namespace = "http://www.onem2m.org/xml/protocols", type = Smd.class),
        @XmlElement(name = "smdA", namespace = "http://www.onem2m.org/xml/protocols", type = SmdA.class)
    })
    protected List<Resource> subOrSmdOrSmdA;

    /**
     * Gets the value of the mt property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMt() {
        return mt;
    }

    /**
     * Sets the value of the mt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMt(BigInteger value) {
        this.mt = value;
    }

    /**
     * Gets the value of the cnm property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCnm() {
        return cnm;
    }

    /**
     * Sets the value of the cnm property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCnm(BigInteger value) {
        this.cnm = value;
    }

    /**
     * Gets the value of the mnm property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMnm() {
        return mnm;
    }

    /**
     * Sets the value of the mnm property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMnm(BigInteger value) {
        this.mnm = value;
    }

    /**
     * Gets the value of the mid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMid().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMid() {
        if (mid == null) {
            mid = new ArrayList<String>();
        }
        return this.mid;
    }

    /**
     * Gets the value of the macp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the macp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMacp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMacp() {
        if (macp == null) {
            macp = new ArrayList<String>();
        }
        return this.macp;
    }

    /**
     * Gets the value of the mtv property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMtv() {
        return mtv;
    }

    /**
     * Sets the value of the mtv property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMtv(Boolean value) {
        this.mtv = value;
    }

    /**
     * Gets the value of the csy property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCsy() {
        return csy;
    }

    /**
     * Sets the value of the csy property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCsy(BigInteger value) {
        this.csy = value;
    }

    /**
     * Gets the value of the gn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGn() {
        return gn;
    }

    /**
     * Sets the value of the gn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGn(String value) {
        this.gn = value;
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
     * Gets the value of the subOrSmdOrSmdA property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subOrSmdOrSmdA property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubOrSmdOrSmdA().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sub }
     * {@link Smd }
     * {@link SmdA }
     * 
     * 
     */
    public List<Resource> getSubOrSmdOrSmdA() {
        if (subOrSmdOrSmdA == null) {
            subOrSmdOrSmdA = new ArrayList<Resource>();
        }
        return this.subOrSmdOrSmdA;
    }

}
