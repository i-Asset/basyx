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
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}mgmtResource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ttn" type="{http://www.onem2m.org/xml/protocols}listOfM2MID"/&gt;
 *         &lt;element name="mrv" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *         &lt;element name="swt" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/&gt;
 *         &lt;element name="bop" type="{http://www.onem2m.org/xml/protocols}backOffParameters"/&gt;
 *         &lt;element name="ohc" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="cmlk" type="{http://www.onem2m.org/xml/protocols}mgmtLinkRef"/&gt;
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
    "ttn",
    "mrv",
    "swt",
    "bop",
    "ohc",
    "cmlk"
})
public class Cmwr
    extends MgmtResource
{

    @XmlList
    @XmlElement(required = true)
    protected List<String> ttn;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger mrv;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger swt;
    @XmlElement(required = true)
    protected BackOffParameters bop;
    @XmlElement(required = true)
    protected Object ohc;
    @XmlElement(required = true)
    protected MgmtLinkRef cmlk;

    /**
     * Gets the value of the ttn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ttn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTtn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTtn() {
        if (ttn == null) {
            ttn = new ArrayList<String>();
        }
        return this.ttn;
    }

    /**
     * Gets the value of the mrv property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMrv() {
        return mrv;
    }

    /**
     * Sets the value of the mrv property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMrv(BigInteger value) {
        this.mrv = value;
    }

    /**
     * Gets the value of the swt property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSwt() {
        return swt;
    }

    /**
     * Sets the value of the swt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSwt(BigInteger value) {
        this.swt = value;
    }

    /**
     * Gets the value of the bop property.
     * 
     * @return
     *     possible object is
     *     {@link BackOffParameters }
     *     
     */
    public BackOffParameters getBop() {
        return bop;
    }

    /**
     * Sets the value of the bop property.
     * 
     * @param value
     *     allowed object is
     *     {@link BackOffParameters }
     *     
     */
    public void setBop(BackOffParameters value) {
        this.bop = value;
    }

    /**
     * Gets the value of the ohc property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getOhc() {
        return ohc;
    }

    /**
     * Sets the value of the ohc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setOhc(Object value) {
        this.ohc = value;
    }

    /**
     * Gets the value of the cmlk property.
     * 
     * @return
     *     possible object is
     *     {@link MgmtLinkRef }
     *     
     */
    public MgmtLinkRef getCmlk() {
        return cmlk;
    }

    /**
     * Sets the value of the cmlk property.
     * 
     * @param value
     *     allowed object is
     *     {@link MgmtLinkRef }
     *     
     */
    public void setCmlk(MgmtLinkRef value) {
        this.cmlk = value;
    }

}
