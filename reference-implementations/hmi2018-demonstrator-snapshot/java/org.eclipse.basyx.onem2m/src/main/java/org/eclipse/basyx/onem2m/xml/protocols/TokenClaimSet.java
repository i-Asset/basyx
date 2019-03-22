//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.16 at 09:48:33 AM CET 
//


package org.eclipse.basyx.onem2m.xml.protocols;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tokenClaimSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tokenClaimSet"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vr" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tkid" type="{http://www.onem2m.org/xml/protocols}tokenID"/&gt;
 *         &lt;element name="tkhd" type="{http://www.onem2m.org/xml/protocols}ID"/&gt;
 *         &lt;element name="tkis" type="{http://www.onem2m.org/xml/protocols}ID"/&gt;
 *         &lt;element name="tknb" type="{http://www.onem2m.org/xml/protocols}timestamp"/&gt;
 *         &lt;element name="tkna" type="{http://www.onem2m.org/xml/protocols}timestamp"/&gt;
 *         &lt;element name="tknm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tkau" type="{http://www.onem2m.org/xml/protocols}listOfM2MID" minOccurs="0"/&gt;
 *         &lt;element name="tkps" type="{http://www.onem2m.org/xml/protocols}tokenPermissions" minOccurs="0"/&gt;
 *         &lt;element name="tkex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tokenClaimSet", propOrder = {
    "vr",
    "tkid",
    "tkhd",
    "tkis",
    "tknb",
    "tkna",
    "tknm",
    "tkau",
    "tkps",
    "tkex"
})
public class TokenClaimSet {

    @XmlElement(required = true)
    protected String vr;
    @XmlElement(required = true)
    protected String tkid;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String tkhd;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String tkis;
    @XmlElement(required = true)
    protected String tknb;
    @XmlElement(required = true)
    protected String tkna;
    protected String tknm;
    @XmlList
    protected List<String> tkau;
    protected TokenPermissions tkps;
    protected String tkex;

    /**
     * Gets the value of the vr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVr() {
        return vr;
    }

    /**
     * Sets the value of the vr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVr(String value) {
        this.vr = value;
    }

    /**
     * Gets the value of the tkid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTkid() {
        return tkid;
    }

    /**
     * Sets the value of the tkid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTkid(String value) {
        this.tkid = value;
    }

    /**
     * Gets the value of the tkhd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTkhd() {
        return tkhd;
    }

    /**
     * Sets the value of the tkhd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTkhd(String value) {
        this.tkhd = value;
    }

    /**
     * Gets the value of the tkis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTkis() {
        return tkis;
    }

    /**
     * Sets the value of the tkis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTkis(String value) {
        this.tkis = value;
    }

    /**
     * Gets the value of the tknb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTknb() {
        return tknb;
    }

    /**
     * Sets the value of the tknb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTknb(String value) {
        this.tknb = value;
    }

    /**
     * Gets the value of the tkna property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTkna() {
        return tkna;
    }

    /**
     * Sets the value of the tkna property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTkna(String value) {
        this.tkna = value;
    }

    /**
     * Gets the value of the tknm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTknm() {
        return tknm;
    }

    /**
     * Sets the value of the tknm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTknm(String value) {
        this.tknm = value;
    }

    /**
     * Gets the value of the tkau property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tkau property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTkau().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTkau() {
        if (tkau == null) {
            tkau = new ArrayList<String>();
        }
        return this.tkau;
    }

    /**
     * Gets the value of the tkps property.
     * 
     * @return
     *     possible object is
     *     {@link TokenPermissions }
     *     
     */
    public TokenPermissions getTkps() {
        return tkps;
    }

    /**
     * Sets the value of the tkps property.
     * 
     * @param value
     *     allowed object is
     *     {@link TokenPermissions }
     *     
     */
    public void setTkps(TokenPermissions value) {
        this.tkps = value;
    }

    /**
     * Gets the value of the tkex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTkex() {
        return tkex;
    }

    /**
     * Sets the value of the tkex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTkex(String value) {
        this.tkex = value;
    }

}
