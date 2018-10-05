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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}regularResource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tkid" type="{http://www.onem2m.org/xml/protocols}tokenID"/&gt;
 *         &lt;element name="tkob" type="{http://www.onem2m.org/xml/protocols}dynAuthJWT"/&gt;
 *         &lt;element name="vr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tkis" type="{http://www.onem2m.org/xml/protocols}ID" minOccurs="0"/&gt;
 *         &lt;element name="tkhd" type="{http://www.onem2m.org/xml/protocols}ID" minOccurs="0"/&gt;
 *         &lt;element name="tknb" type="{http://www.onem2m.org/xml/protocols}timestamp"/&gt;
 *         &lt;element name="tkna" type="{http://www.onem2m.org/xml/protocols}timestamp" minOccurs="0"/&gt;
 *         &lt;element name="tknm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tkau" type="{http://www.onem2m.org/xml/protocols}listOfM2MID" minOccurs="0"/&gt;
 *         &lt;element name="tkps" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="pm" type="{http://www.onem2m.org/xml/protocols}tokenPermission"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="tkex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="ch" type="{http://www.onem2m.org/xml/protocols}childResourceRef" maxOccurs="unbounded"/&gt;
 *           &lt;choice maxOccurs="unbounded"&gt;
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}sub"/&gt;
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
    "tkid",
    "tkob",
    "vr",
    "tkis",
    "tkhd",
    "tknb",
    "tkna",
    "tknm",
    "tkau",
    "tkps",
    "tkex",
    "ch",
    "sub"
})
public class Tk
    extends RegularResource
{

    @XmlElement(required = true)
    protected String tkid;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String tkob;
    protected String vr;
    @XmlSchemaType(name = "anyURI")
    protected String tkis;
    @XmlSchemaType(name = "anyURI")
    protected String tkhd;
    @XmlElement(required = true)
    protected String tknb;
    protected String tkna;
    protected String tknm;
    @XmlList
    protected List<String> tkau;
    protected Tk.Tkps tkps;
    protected String tkex;
    protected List<ChildResourceRef> ch;
    @XmlElement(namespace = "http://www.onem2m.org/xml/protocols")
    protected List<Sub> sub;

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
     * Gets the value of the tkob property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTkob() {
        return tkob;
    }

    /**
     * Sets the value of the tkob property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTkob(String value) {
        this.tkob = value;
    }

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
     *     {@link Tk.Tkps }
     *     
     */
    public Tk.Tkps getTkps() {
        return tkps;
    }

    /**
     * Sets the value of the tkps property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tk.Tkps }
     *     
     */
    public void setTkps(Tk.Tkps value) {
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="pm" type="{http://www.onem2m.org/xml/protocols}tokenPermission"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pm"
    })
    public static class Tkps {

        @XmlElement(required = true)
        protected TokenPermission pm;

        /**
         * Gets the value of the pm property.
         * 
         * @return
         *     possible object is
         *     {@link TokenPermission }
         *     
         */
        public TokenPermission getPm() {
            return pm;
        }

        /**
         * Sets the value of the pm property.
         * 
         * @param value
         *     allowed object is
         *     {@link TokenPermission }
         *     
         */
        public void setPm(TokenPermission value) {
            this.pm = value;
        }

    }

}
